package com.yakzhanov.flatseeker.rnd;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

public class UrlResolverTest {

    public static final String SOURCE_URL = "https://www.otodom.pl/pl/oferta/przestronne-3-pok-115m2-3-balkony-2m-post-ul-lea-ID4fFl7";
    public static final int TIMEOUT_MILLIS = 5000;

    @Test
    public void httpStatusTest() throws IOException {
        URL url = new URL(SOURCE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();

        assertThat(code).isEqualTo(302);
    }

    @Test
    public void extractSEOData() {
        var document = parseUrl();
        var title = document.title();
        var site = document.select("meta[property='og:site_name']").stream().findAny().map(e -> e.attr("content")).orElse(null);
        var description = document.select("meta[property='og:description']").stream().findAny().map(e -> e.attr("content")).orElse(null);
        var imageUrl = document.select("meta[property='og:image']").stream().findAny().map(e -> e.attr("content")).orElse(null);
        System.out.println("Extracted");
        System.out.println("Site: " + site);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("ImageUrl: " + imageUrl);
    }

    private Document parseUrl() {
        try {
            return Jsoup.parse(new URL(SOURCE_URL), TIMEOUT_MILLIS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
