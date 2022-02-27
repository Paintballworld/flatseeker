package com.yakzhanov.flatseeker.platform;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import com.yakzhanov.flatseeker.conf.AppParams;
import org.assertj.core.util.Strings;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

class OtodomAptPlatformTest {

    public static final String TEST_PAGE = "https://www.otodom.pl//pl/oferta/mieszkanie-4pok-86m2-z-widokiem-na-park-reduta-ID4eMgr";
    private final AptPlatform platform = new OtodomAptPlatform();
    private final AppParams testParams = new AppParams() {
        @Override
        public int priceCap() {
            return 5000;
        }

        @Override
        public int roomCount() {
            return 4;
        }
    };

    @Test
    public void testLoadLinkData() {
        var url = platform.resolveSearchPageUrl(1, testParams);
        System.out.println("URL: " + url);
    }

    @Test
    public void loadLinks() throws IOException {
        var document = Jsoup.parse(platform.resolveSearchPageUrl(1, testParams), 5000);
        var linkDataList = platform.extractSearchPageLinks(document);
        assertThat(linkDataList).asList().isNotEmpty();
        assertThat(linkDataList.stream().noneMatch(l -> Strings.isNullOrEmpty(l.getTitle()) || Objects.isNull(l.getUrl()))).isTrue();
        linkDataList.forEach(System.out::println);
    }

    @Test
    public void testParsePage_area() throws IOException {
        var document = Jsoup.parse(new URL(TEST_PAGE), 5000);
        var area = platform.extractArea(document);
        assertThat(area).isNotNull();
        assertThat(area).isLessThan(500);
        System.out.println("Area: " + area);
    }
}
