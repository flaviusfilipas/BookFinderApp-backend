package com.bookfinder.pojo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplashAPIArgs {
    private static final String SPLASH_SERVER_URL = "http://localhost:8050/render.html";
    private String url;
    private String waitTime;
    private String forbiddenContentType;
    private String images;
    private String filters;

    public String getUrl() {
        return url;
    }

    public SplashAPIArgs withUrl(String url) {
        this.url = "url="+url;
        return this;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public SplashAPIArgs withWaitTime(String waitTime) {
        this.waitTime = "wait_time="+waitTime;
        return this;
    }

    public String getForbiddenContentType() {
        return forbiddenContentType;
    }

    public SplashAPIArgs withForbiddenContentType(String forbiddenContentType) {
        this.forbiddenContentType = "forbidden_content_types=" + forbiddenContentType;
        return this;
    }

    public String getFilters() {
        return filters;
    }

    public SplashAPIArgs withFilters(String filters) {
        this.filters = "filters="+filters;
        return this;
    }
    public SplashAPIArgs withImages(String images) {
        this.images = "images="+images;
        return this;
    }

    public String buildUrl() throws UnsupportedEncodingException {
        String url = SPLASH_SERVER_URL + "?" + Stream.of(getUrl(),getForbiddenContentType(),getWaitTime(),getFilters())
                .filter(Objects::nonNull)
                .collect(Collectors.joining("&"));
        return URLEncoder.encode(url,StandardCharsets.UTF_8.toString());
    }
}
