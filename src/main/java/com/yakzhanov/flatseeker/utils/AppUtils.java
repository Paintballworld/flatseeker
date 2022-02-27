package com.yakzhanov.flatseeker.utils;

import java.net.MalformedURLException;
import java.net.URL;
import com.sun.istack.Nullable;

public class AppUtils {

    @Nullable
    public static URL convertToUrlOrNull(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
