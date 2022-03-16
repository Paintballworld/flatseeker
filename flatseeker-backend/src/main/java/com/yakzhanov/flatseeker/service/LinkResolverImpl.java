package com.yakzhanov.flatseeker.service;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import com.yakzhanov.flatseeker.exception.UnknownPlatformException;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkResolverImpl implements LinkResolver {

    private final Map<String, AptPlatform> platformMap;
    private final DictService dictService;
    private Map<String, String> urlPlatformMap;

    @PostConstruct
    public void init() {
        urlPlatformMap = platformMap.values().stream()
          .collect(Collectors.toMap(
            platform -> platform.baseUrl().replaceFirst("http(s*)://(www\\.)*", ""),
            AptPlatform::name
          ));
    }

    @SneakyThrows
    @Override
    public ApartmentRecord resolve(String link) {
        URL url = new URL(link);
        return Optional.ofNullable(extractBaseUrl(link))
          .map(urlPlatformMap::get)
          .map(platformMap::get)
          .map(platform -> AppUtils.parseRecord(readAsDocument(url, platform), platform, url, dictService.defaultValue(ProcessStatus.class)))
          .orElseThrow(UnknownPlatformException::new);
    }

    @SneakyThrows
    private Document readAsDocument(URL url, AptPlatform platform) {
        return Jsoup.parse(url, platform.readTimeoutMillis());
    }

    private String extractBaseUrl(String url) {
        return url.replaceAll("http(s*)://(www\\.)*([^.]+)\\.(\\w+).*", "$3.$4");
    }

}
