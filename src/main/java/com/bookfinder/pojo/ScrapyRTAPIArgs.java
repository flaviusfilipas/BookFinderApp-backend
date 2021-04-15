package com.bookfinder.pojo;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScrapyRTAPIArgs {
    private static final String SCRAPYRT_SERVER_URL= "http://localhost:9080/crawl.json";
    private String url;
    private String callback;
    private String spiderName;

    public String getUrl() {
        return url;
    }

    public ScrapyRTAPIArgs withUrl(String url) {
        this.url = "url="+url;
        return this;
    }

    public String getCallback() {
        return callback;
    }

    public ScrapyRTAPIArgs withCallback(String callback) {
        this.callback = "callback="+callback;
        return this;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public ScrapyRTAPIArgs withSpiderName(String spiderName) {
        this.spiderName = "spider_name="+spiderName;
        return this;
    }

    public String buildURL(){
        return SCRAPYRT_SERVER_URL + "?" + Stream.of(getUrl(),getSpiderName(),getCallback())
                .filter(Objects::nonNull)
                .collect(Collectors.joining("&"));
    }
}
