package com.yakzhanov.flatseeker.platform;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.yakzhanov.flatseeker.conf.AppParams;
import com.yakzhanov.flatseeker.conf.Constants;
import com.yakzhanov.flatseeker.model.AnimalsStatus;
import com.yakzhanov.flatseeker.model.ApartmentType;
import com.yakzhanov.flatseeker.model.BathroomStatus;
import com.yakzhanov.flatseeker.model.LinkData;
import com.yakzhanov.flatseeker.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component(OtodomAptPlatform.OTODOM)
@RequiredArgsConstructor
public class OtodomAptPlatform implements AptPlatform {

    public static final String OTODOM = "Otodom";
    private static final Long TIMEOUT = 1000L;
    public static final Predicate<String> NOT_EMPTY = o -> !o.isEmpty() && !o.isBlank();
    private final AtomicLong nextCall = new AtomicLong(System.currentTimeMillis());

    @Override
    public String baseUrl() {
        return "https://www.otodom.pl";
    }

    @Override
    public boolean canSendRequest() {
        return System.currentTimeMillis() > nextCall.get();
    }

    @Override
    public AnimalsStatus extractAnimalStatus(Document document) {
        return AnimalsStatus.NOT_DEFINED;
    }

    @Override
    public ApartmentType extractApartmentType(Document document) {
        return null;
    }

    @Override
    public Integer extractArea(Document document) {
        return Optional.of(document.select("main div[aria-label='Powierzchnia']"))
          .map(Elements::first)
          .map(Element::text)
          .map(value -> value.replaceAll("[^0-9,]", ""))
          .map(value -> value.replaceAll(",", "."))
          .map(Double::parseDouble)
          .map(Double::intValue)
          .orElse(null);
    }

    @Override
    public BathroomStatus extractBathroomStatus(Document document) {
        return null;
    }

    @Override
    public Boolean extractConditioner(Document document) {
        return null;
    }

    @Override
    public Date extractCreatedAt(Document document) {
        return null;
    }

    @Override
    public Integer extractDeposit(Document document) {
        return Optional.of(document.select("main div[aria-label='Kaucja']"))
          .map(Elements::first)
          .map(Element::text)
          .map(value -> value.replaceAll("\\D", ""))
          .filter(NOT_EMPTY)
          .map(Integer::parseInt)
          .orElse(null);
    }

    @Override
    public String extractDescription(Document document) {
        return Optional.of(document.select("main section[role='region'] div[data-cy='adPageAdDescription']"))
          .map(Elements::first)
          .map(Element::text)
          .orElse(null);
    }

    @Override
    public Integer extractFeePrice(Document document) {
        return Optional.of(document.select("main div[aria-label='Czynsz']"))
          .map(Elements::first)
          .map(Element::text)
          .map(value -> value.replaceAll("\\D", ""))
          .filter(NOT_EMPTY)
          .map(Integer::parseInt)
          .orElse(null);
    }

    @Override
    public String extractLocation(Document document) {
        return null;
    }

    @Override
    public String extractMainImageUrl(Document document) {
        //main section div.image-gallery-slide-wrapper.bottom > div.image-gallery-swipe > div > div.image-gallery-slide.center > div > picture > img
        return Optional.of(document.select("main section div picture img"))
          .map(Elements::first)
          .map(element -> element.attr("src"))
          .orElse(null);
    }

    @Override
    public Integer extractRentPrice(Document document) {
        return Optional.of(document.select("main header strong[data-cy='adPageHeaderPrice']"))
          .map(Elements::first)
          .map(Element::text)
          .map(text -> text.replaceAll("\\D", ""))
          .filter(NOT_EMPTY)
          .map(Integer::parseInt)
          .orElse(null);
    }

    @Override
    public List<LinkData> extractSearchPageLinks(Document document) {
        return Optional.of(document.select("main div[role='main'] div[data-cy='search.listing'] ul"))
          .map(Elements::first)
          .map(element -> element.select("li").stream())
          .orElse(Stream.empty())
          .map(this::extractLinkData)
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
    }

    private LinkData extractLinkData(Element element) {
        var aTag = Optional.of(element.select("a[data-cy='listing-item-link']"))
          .map(Elements::first);

        String link = aTag
          .map(e -> e.attr("href"))
          .map(url -> baseUrl() + url)
          .orElse("");

        String title = aTag
          .map(e -> e.select("h3[data-cy='listing-item-title']"))
          .map(Elements::first)
          .map(e -> e.attr("title"))
          .orElse("");

        return (link + title).isEmpty() ? null : new LinkData(AppUtils.convertToUrlOrNull(link), link, title);
    }

    @Override
    public String extractTitle(Document document) {
        return Optional.of(document.select("main header [data-cy='adPageAdTitle']"))
          .map(Elements::first)
          .map(Element::text)
          .orElse(null);
    }

    @Override
    public boolean isOutdated(Document document) {
        return document.select("div[data-cy='redirectedFromInactiveAd']").stream()
          .findAny()
          .isPresent();
    }

    @Override
    public String name() {
        return OTODOM;
    }

    @Override
    public int readTimeoutMillis() {
        return 100_000;
    }

    @SneakyThrows
    @Override
    public URL resolveSearchPageUrl(int pageNumber, AppParams params) {
        //https://www.otodom.pl/pl/oferty/wynajem/mieszkanie/krakow?page=1&limit=36&market=ALL&ownerTypeSingleSelect=ALL&distanceRadius=0&priceMax=5000&areaMin=75&locations=%5Bcities_6-38%5D&roomsNumber=%5BFOUR%5D&viewType=listing
        //https://www.otodom.pl/pl/oferty/wynajem/mieszkanie/krakow?page=2&limit=24&market=ALL&ownerTypeSingleSelect=ALL&distanceRadius=0&areaMin=75&roomsNumber=%5BFOUR%5D&locations=%5Bcities_6-38%5D&viewType=listing
        String replacedValues = "https://www.otodom.pl/pl/oferty/wynajem/mieszkanie/krakow?page={{page}}&limit=36&market=ALL&ownerTypeSingleSelect=ALL&distanceRadius=0&priceMax={{price_cap}}&areaMin=75&locations=%5Bcities_6-38%5D&roomsNumber=%5BFOUR%5D&viewType=listing"
          .replaceAll("\\{\\{page}}", String.valueOf(pageNumber))
          .replaceAll("\\{\\{price_cap}}", String.valueOf(params.priceCap()));
        return new URL(replacedValues);
    }

    @Override
    public int searchPageInitialValue() {
        return 1;
    }

    @Override
    public void updateRequestTime() {
        this.nextCall.set(System.currentTimeMillis() + TIMEOUT);
    }

    @Override
    public void markAsExhausted() {
        this.nextCall.set(System.currentTimeMillis() + Constants.NEW_ADS_AWAIT_TIMEOUT);
    }
}
