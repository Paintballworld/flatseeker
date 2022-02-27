package com.yakzhanov.flatseeker.model;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkData {

    private URL url;
    private String link;
    private String title;

}
