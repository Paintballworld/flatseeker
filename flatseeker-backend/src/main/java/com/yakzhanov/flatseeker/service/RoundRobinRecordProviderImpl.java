package com.yakzhanov.flatseeker.service;

import static com.yakzhanov.flatseeker.utils.AppUtils.parseRecord;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import com.yakzhanov.flatseeker.conf.AppParams;
import com.yakzhanov.flatseeker.conf.Constants;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.LinkData;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoundRobinRecordProviderImpl implements RecordProvider {

    private final Map<AptPlatform, AtomicInteger> nextPageForPlatformMap = new ConcurrentHashMap<>();
    private final Map<AptPlatform, Queue<URL>> linksQueueForPlatformMap = new ConcurrentHashMap<>();
    private final ConcurrentLinkedDeque<AptPlatform> platformsRoundRobinQueue = new ConcurrentLinkedDeque<>();
    private final AppParams appParams;
    private final ApartmentRecordRepository apartmentRecordRepository;
    private final DictService dictService;

    @Autowired
    private void fillPlatformQueue(List<AptPlatform> aptPlatforms) {
        platformsRoundRobinQueue.addAll(aptPlatforms);
    }

    @Override
    public Optional<ApartmentRecord> nextRecord() {
        return Optional.ofNullable(nextColdPlatform())
          .map(this::nextPlatformRecord);
    }

    private URL getNextAptPageUrl(AptPlatform platform) {
        var urlQueue = linksQueueForPlatformMap.computeIfAbsent(platform, this::loadNextSearchPageUrls);
        synchronized (linksQueueForPlatformMap) {
            if (urlQueue.size() < 2) {
                log.info("Removing URL queue as it is empty");
                linksQueueForPlatformMap.remove(platform);
            }
        }
        return urlQueue.poll();
    }

    private Integer getPlatformNextSearchPage(AptPlatform platform) {
        return nextPageForPlatformMap.computeIfAbsent(platform, p -> new AtomicInteger(p.searchPageInitialValue())).getAndIncrement();
    }

    @SneakyThrows
    private Queue<URL> loadNextSearchPageUrls(AptPlatform platform) {
        log.info("Updating page list for '{}'", platform.name());
        platform.updateRequestTime();
        var linkDataList = Optional.of(getPlatformNextSearchPage(platform))
          .map(nextPageNumber -> platform.resolveSearchPageUrl(nextPageNumber, appParams))
          .map(nextPageUrl -> safeParsePage(platform, nextPageUrl))
          .map(platform::extractSearchPageLinks)
          .orElse(Collections.emptyList());

        apartmentRecordRepository.loadLinkOnly(platform.name())
          .forEach(existingLink -> linkDataList.removeIf(linkData -> compareLinks(existingLink, linkData)));

        if (linkDataList.isEmpty()) {
            log.info("Platform '{}' is exhausted and therefore will be ignored for {} ms", platform.name(), Constants.NEW_ADS_AWAIT_TIMEOUT);
            nextPageForPlatformMap.put(platform, new AtomicInteger(platform.searchPageInitialValue()));
            platform.markAsExhausted();
        }

        return linkDataList.stream()
          .peek(linkData -> warnIfNull(linkData, platform))
          .map(LinkData::getUrl)
          .filter(Objects::nonNull)
          .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));
    }

    private boolean compareLinks(String existingLink, LinkData linkData) {
        return linkData.getLink().replaceFirst("www\\.", "").equals(existingLink.replaceFirst("www\\.", ""));
    }

    private void warnIfNull(LinkData linkData, AptPlatform platform) {
        if (linkData.getUrl() == null) {
            log.warn("Link Data extracted from '{}' has unresolved url. {}", platform.name(), linkData);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SneakyThrows
    private AptPlatform nextColdPlatform() {
        log.info("Finding cold platform");
        synchronized (platformsRoundRobinQueue) {
            var first = platformsRoundRobinQueue.peekFirst();
            while (!platformsRoundRobinQueue.peek().canSendRequest()) {
                var next = platformsRoundRobinQueue.pollFirst();
                platformsRoundRobinQueue.addLast(next);
                if (platformsRoundRobinQueue.peek().equals(first)) {
                    log.info("No cold platform available.");
                    return null;
                }
            }
        }
        var result = platformsRoundRobinQueue.pollFirst();
        platformsRoundRobinQueue.addLast(result);
        return result;
    }

    @SneakyThrows
    private ApartmentRecord nextPlatformRecord(AptPlatform platform) {
        log.info("Retrieving next record from platform {}", platform.name());
        URL nextAptPageUrl = getNextAptPageUrl(platform);
        if (nextAptPageUrl == null) {
            return null;
        }
        platform.updateRequestTime();
        var document = Jsoup.parse(nextAptPageUrl, platform.readTimeoutMillis());
        return parseRecord(document, platform, nextAptPageUrl, dictService.defaultValue(ProcessStatus.class));
    }

    @SneakyThrows
    private Document safeParsePage(AptPlatform platform, URL nextPageUrl) {
        return Jsoup.parse(nextPageUrl, platform.readTimeoutMillis());
    }
}
