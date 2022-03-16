package com.yakzhanov.flatseeker.platform;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GumtreeAptPlatformTest {

    private static final String TEST_PAGE_OUTDATED = "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/4pok-82m2-ul-ceglarska-zakrzowek/10010610044941012103440609";
    private static final String TEST_PAGE_ACTUAL = "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/krakow-babinskiego-kliny-ruczaj-do-wynajecia-pokoj-w-nowym-domu-ogrodek-wlasciciel/10010607945791010879197509";

    @Autowired
    private GumtreeAptPlatform gumtreeAptPlatform;

    @Test
    public void test_outdated() throws IOException {
        var outdatedDoc = Jsoup.parse(new URL(TEST_PAGE_OUTDATED), gumtreeAptPlatform.readTimeoutMillis());
        var isOutdated = gumtreeAptPlatform.isOutdated(outdatedDoc);

        assertThat(isOutdated).isTrue();
    }

    @Test
    public void test_actual() throws IOException {
        var actual = Jsoup.parse(new URL(TEST_PAGE_ACTUAL), gumtreeAptPlatform.readTimeoutMillis());
        var isOutdated = gumtreeAptPlatform.isOutdated(actual);

        assertThat(isOutdated).isFalse();
    }



}
