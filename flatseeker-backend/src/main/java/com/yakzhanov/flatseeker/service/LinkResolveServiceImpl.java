package com.yakzhanov.flatseeker.service;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import com.yakzhanov.flatseeker.model.dto.LinkResolveRequest;
import com.yakzhanov.flatseeker.model.dto.LinkResolveResponse;
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
    public Optional<LinkResolveResponse> tryToResolve(@NotNull LinkResolveRequest request) {
        // TODO: 05.03.2022 Change to duplicate by title, rather than a link, beecause link might contain utm parameters, which are irrelevant and might change
        return apartmentRecordRepository.findByLink(request.getLinkToResolve())
          .map(LinkResolveResponse::ofExisting)
          .or(() -> Optional.of(linkResolver.resolve(request.getLinkToResolve()))
            .map(LinkResolveResponse::ofNew));
    }
}
