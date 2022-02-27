package com.yakzhanov.flatseeker.platform;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.yakzhanov.flatseeker.conf.AppParams;
import com.yakzhanov.flatseeker.conf.Constants;
import com.yakzhanov.flatseeker.model.AnimalsStatus;
import com.yakzhanov.flatseeker.model.ApartmentType;
import com.yakzhanov.flatseeker.model.BathroomStatus;
import com.yakzhanov.flatseeker.model.LinkData;
import com.yakzhanov.flatseeker.utils.AppUtils;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component("Gumtree")
public class GumtreeAptPlatform implements AptPlatform {

    private static final Long TIMEOUT = 1000L;
    private final AtomicLong nextCall = new AtomicLong(System.currentTimeMillis());

    @Override
    public String baseUrl() {
        return "https://gumtree.pl";
    }

    @Override
    public boolean canSendRequest() {
        return System.currentTimeMillis() > nextCall.get();
    }

    @Override
    public AnimalsStatus extractAnimalStatus(Document document) {
        return searchFromAttributeList(document, "zwier")
          .map(this::resolveAnimalStatus)
          .orElse(null);
    }

    @Override
    public ApartmentType extractApartmentType(Document document) {
        return searchFromAttributeList(document, "Rodzaj nieruchomości")
          .map(this::resolveType)
          .orElse(null);
    }

    @Override
    public Integer extractArea(Document document) {
        return searchFromAttributeList(document, "m2")
          .map(Integer::parseInt)
          .orElse(-1);
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
        return searchFromAttributeList(document, "Data dodania")
          .map(this::parseDate)
          .orElse(null);
    }

    @SneakyThrows
    private Date parseDate(String value) {
        return new SimpleDateFormat("dd/MM/yyyy").parse(value);
    }

    @Override
    public Integer extractDeposit(Document document) {
        return null;
    }

    @Override
    public String extractDescription(Document document) {
        return readStringValue(document, "div#wrapper div.vip-details div.description");
    }

    @Override
    public Integer extractFeePrice(Document document) {
        return null;
    }

    @Override
    public String extractLocation(Document document) {
        return null;
    }

    @Override
    public String extractMainImageUrl(Document document) {
        return Optional.of(document.select("div#wrapper div.vip-gallery div.main-bg div.main img"))
          .map(Elements::first)
          .map(element -> element.attr("src"))
          .orElse(null);
    }

    @Override
    public Integer extractRentPrice(Document document) {
        return readIntValue(document, "div#wrapper div.price span.amount");
    }

    @Override
    public List<LinkData> extractSearchPageLinks(Document document) {
        return document.select("div.tileV1 a.href-link").stream()
          .map(this::extractSearchPageLink)
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
    }

    private LinkData extractSearchPageLink(Element element) {
        String link = element.attr("href");
        String title = element.text();
        return (link + title).isEmpty() ? null : new LinkData(AppUtils.convertToUrlOrNull(link), title);
    }

    @Override
    public String extractTitle(Document document) {
        return readStringValue(document, "div#wrapper span.myAdTitle");
    }

    @Override
    public String name() {
        return "Gumtree";
    }

    @Override
    public int readTimeoutMillis() {
        return 10_000;
    }

    @SneakyThrows
    @Override
    public URL resolveSearchPageUrl(int pageNumber, AppParams params) {
        var replacedValues = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-{{page}}/v1c9008l3200208p{{page}}?pr=,{{price}}&nr={{room_count}}"
          .replaceAll("\\{\\{page}}", String.valueOf(pageNumber))
          .replaceAll("\\{\\{price}}", String.valueOf(params.priceCap()))
          .replaceAll("\\{\\{room_count}}", String.valueOf(params.roomCount()));
        return new URL(replacedValues);
    }

    @Override
    public int searchPageInitialValue() {
        return 1;
    }

    @Override
    public void updateRequestTime() {
        nextCall.set(System.currentTimeMillis() + TIMEOUT);
    }

    @Override
    public void markAsExhausted() {
        nextCall.set(System.currentTimeMillis() + Constants.NEW_ADS_AWAIT_TIMEOUT);
    }

    @SuppressWarnings("SameParameterValue")
    private Integer readIntValue(Document document, String query) {
        return Optional.ofNullable(readStringValue(document, query))
          .map(str -> str.replaceAll("\\D", ""))
          .map(Integer::parseInt)
          .orElse(null);
    }

    private String readStringValue(Document document, String query) {
        return Optional.of(document.select(query))
          .map(Elements::first)
          .map(Element::text)
          .orElse(null);
    }

    private AnimalsStatus resolveAnimalStatus(String s) {
        if (s == null || s.isEmpty()) {
            return AnimalsStatus.NOT_DEFINED;
        }
        return "TAK".equalsIgnoreCase(s) ? AnimalsStatus.ALLOWED : AnimalsStatus.NOT_ALLOWED;
    }

    private ApartmentType resolveType(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return "Mieszkanie".equalsIgnoreCase(s) ? ApartmentType.FLAT : ApartmentType.HOUSE;
    }

    private Optional<String> searchFromAttributeList(Document document, String findBy) {
        return Optional.of(document.select("div#wrapper div.vip-details ul.selMenu"))
          .map(Elements::first)
          .map(element -> element.select("li").stream())
          .orElse(Stream.empty())
          .filter(element -> element.text().contains(findBy))
          .flatMap(element -> element.select("span.value").stream())
          .map(Element::text)
          .findFirst();
    }
}
