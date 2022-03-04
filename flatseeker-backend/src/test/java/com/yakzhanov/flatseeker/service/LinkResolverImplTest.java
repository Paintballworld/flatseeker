package com.yakzhanov.flatseeker.service;

import static com.yakzhanov.flatseeker.platform.GumtreeAptPlatform.GUMTREE;
import static com.yakzhanov.flatseeker.platform.OtodomAptPlatform.OTODOM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.util.List;
import com.yakzhanov.flatseeker.conf.AppParams;
import com.yakzhanov.flatseeker.exception.UnknownPlatformException;
import com.yakzhanov.flatseeker.model.LinkData;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkResolverImplTest {

    @Autowired
    private LinkResolver linkResolver;

    @Autowired
    @Qualifier(OTODOM)
    private AptPlatform otodom;

    @Autowired
    @Qualifier(GUMTREE)
    private AptPlatform gumtree;

    private AppParams appParams() {
        return new AppParams() {
            @Override
            public int priceCap() {
                return 5000;
            }

            @Override
            public int roomCount() {
                return 4;
            }
        };
    }

    @Test
    void testSuccessfulRead_Otodom() throws IOException {
        var linkToResolve = getFirstSearchLink(otodom).iterator().next().getLink();
        var resolved = linkResolver.resolve(linkToResolve);

        assertThat(resolved).isNotNull();
        assertThat(resolved).matches(r -> r.getLink() != null);
        assertThat(resolved).matches(r -> r.getLink().equals(linkToResolve));
        assertThat(resolved.getTitle()).isNotNull();
        assertThat(resolved.getTitle()).isNotEmpty();
    }

    @Test
    void testSuccessfulRead_Gumtree() throws IOException {
        var linkToResolve = getFirstSearchLink(gumtree).iterator().next().getLink();
        var resolved = linkResolver.resolve(linkToResolve);

        assertThat(resolved).isNotNull();
        assertThat(resolved).matches(r -> r.getLink() != null);
        assertThat(resolved).matches(r -> r.getLink().equals(linkToResolve));
        assertThat(resolved.getTitle()).isNotNull();
        assertThat(resolved.getTitle()).isNotEmpty();
    }

    @Test
    public void test_UnknownPlatformLink() throws IOException {
        var linkToResolve = "https://www.booking.com/hotel/pl/krk-rent-apartments.html?label=English_United_Arab_Emirates_EN_AE_29561940625-yuA7f9oaj8QqbY*I50GtqAS217242907572%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi10679101654%3Atiaud-297601666555%3Adsa-302083110424%3Alp1000013%3Ali%3Adec%3Adm&sid=4109a2b32b5529c7e27ecdfe8be780dc&gclid=CjwKCAiAjoeRBhAJEiwAYY3nDLgHNP7fmE9hBquRYbmpuCCxseQArL2ucgzdKCbbLd9iBMTKTlZ8PhoCtaAQAvD_BwE&aid=318615&ucfs=1&arphpl=1&dest_id=-510625&dest_type=city&group_adults=2&req_adults=2&no_rooms=1&group_children=0&req_children=0&hpos=1&hapos=1&sr_order=popularity&srpvid=32eb7d13f4c60141&srepoch=1646416040&from=searchresults#hotelTmpl";

        var exception = assertThrows(Exception.class, () -> linkResolver.resolve(linkToResolve));

        assertThat(exception).isNotNull();
        assertThat(exception).isExactlyInstanceOf(UnknownPlatformException.class);
    }

    private List<LinkData> getFirstSearchLink(AptPlatform platform) throws IOException {
        var searchPageUrl = platform.resolveSearchPageUrl(1, appParams());
        var searchPage = Jsoup.parse(searchPageUrl, 10_000);
        return platform.extractSearchPageLinks(searchPage);
    }
}
