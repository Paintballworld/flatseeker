package com.yakzhanov.flatseeker.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyDefaultParams implements AppParams {

    @Value("${app.params.price-cap}")
    private Integer priceCap;

    @Value("${app.params.room-count}")
    private Integer roomCount;

    @Override
    public int priceCap() {
        return priceCap;
    }

    @Override
    public int roomCount() {
        return roomCount;
    }
}
