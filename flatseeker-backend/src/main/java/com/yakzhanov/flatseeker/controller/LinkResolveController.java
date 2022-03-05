package com.yakzhanov.flatseeker.controller;

import javax.validation.Valid;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dto.LinkResolveRequest;
import com.yakzhanov.flatseeker.model.dto.LinkResolveResponse;
import com.yakzhanov.flatseeker.service.LinkResolveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/link-resolver")
public class LinkResolveController {

    private final LinkResolveService linkResolveService;

    @PostMapping("/")
    public ResponseEntity<LinkResolveResponse> tryToResolve(@RequestBody @Valid LinkResolveRequest request) {
        return ResponseEntity.of(linkResolveService.tryToResolve(request));
    }

}
