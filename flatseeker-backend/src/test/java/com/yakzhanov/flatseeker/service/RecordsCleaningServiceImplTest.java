package com.yakzhanov.flatseeker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.util.Collections;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.platform.GumtreeAptPlatform;
import com.yakzhanov.flatseeker.platform.OtodomAptPlatform;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RecordsCleaningServiceImplTest {

    public static final String OUTDATED_LINK = "https://www.otodom.pl/pl/oferta/przestronne-3-pok-115m2-3-balkony-2m-post-ul-lea-ID4fFl7";
    public static final String TEST_RECORD_ID = "TEST_RECORD_ID";

    @Autowired
    private RecordsCleaningService recordsCleaningService;

    @Autowired
    @Qualifier(GumtreeAptPlatform.GUMTREE)
    private AptPlatform gumtreePlatform;

    @Autowired
    @Qualifier(OtodomAptPlatform.OTODOM)
    private AptPlatform otodomPlatform;

    @MockBean
    private ApartmentRecordRepository mockRepository;

    @Test
    public void testRemoved() {
        var record = ApartmentRecord.builder()
          .id(TEST_RECORD_ID)
          .link(OUTDATED_LINK)
          .platformName(otodomPlatform.name())
          .build();

        when(mockRepository.loadNotRemoved())
          .thenReturn(Collections.singletonList(record));

        //
        recordsCleaningService.removeOutdated();
        //

        var argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository, times(1)).updateRemoveFlag(argumentCaptor.capture());

        var idToRemove = argumentCaptor.getValue();
        assertThat(idToRemove).isNotNull();
        assertThat(idToRemove).isEqualTo(TEST_RECORD_ID);
    }
}
