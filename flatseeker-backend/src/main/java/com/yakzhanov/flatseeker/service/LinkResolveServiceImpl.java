package com.yakzhanov.flatseeker.service;

import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dto.LinkResolveRequest;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinkResolveServiceImpl implements LinkResolveService {

    private final LinkResolver linkResolver;
    private final ApartmentRecordRepository apartmentRecordRepository;

    @Override
    public void save(ApartmentRecord record) {
        log.info("Saving new resolved record");
        record.setId(UUID.randomUUID().toString());
        apartmentRecordRepository.save(record);
    }

    @Override
    public Optional<ApartmentRecord> tryToResolve(@NotNull LinkResolveRequest request) {
        return Optional.of(linkResolver.resolve(request.getUrlToResolve()));
    }
}
