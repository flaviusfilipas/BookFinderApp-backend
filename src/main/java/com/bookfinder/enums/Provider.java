package com.bookfinder.enums;

import com.bookfinder.pojo.ScrapyRTAPIArgs;
import com.bookfinder.pojo.SplashAPIArgs;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public enum Provider {
    CARTURESTI("Carturesti","carturesti"){
        @Override
        public String getUri(String link) throws UnsupportedEncodingException {
            return new ScrapyRTAPIArgs()
                    .withUrl(new SplashAPIArgs()
                            .withUrl(link)
                            .withFilters("easylist")
                            .withWaitTime("1")
                            .withForbiddenContentType("text/css,font/*")
                            .withImages("0")
                            .buildUrl())
                    .withSpiderName(getSpiderName())
                    .withCallback("parse_simple_book_info")
                    .buildURL();
        }
    },
    DIVERTA("Diverta","diverta") {
        @Override
        public String getUri(String link) throws UnsupportedEncodingException {
           return new ScrapyRTAPIArgs()
                    .withUrl(new SplashAPIArgs()
                            .withUrl(link)
                            .withFilters("easylist")
                            .withWaitTime("1")
                            .withImages("0")
                            .withForbiddenContentType("text/css,font/*")
                            .buildUrl())
                    .withSpiderName(getSpiderName())
                    .withCallback("parse_book_info")
                    .buildURL();
        }
    },
    BOOKS_EXPRESS("Books Express","booksExpress") {
        @Override
        public String getUri(String link) {
            return new ScrapyRTAPIArgs()
                    .withUrl(link)
                    .withSpiderName(getSpiderName())
                    .withCallback("parse_book_info")
                    .buildURL();
        }
    },
    EMAG("Emag", "emag"){
        @Override
        public String getUri(String link) throws UnsupportedEncodingException {
            return new ScrapyRTAPIArgs()
                    .withUrl(new SplashAPIArgs()
                            .withUrl(link)
                            .withForbiddenContentType("text/css,font/*")
                            .buildUrl())
                    .withSpiderName(getSpiderName())
                    .buildURL();
        }
    },
    ELEFANT("Elefant","elefant"){
        @Override
        public String getUri(String link) {
            return new ScrapyRTAPIArgs()
                    .withUrl(link)
                    .withSpiderName(getSpiderName())
                    .buildURL();
        }
    },
    LIBRARIENET("Librarie.net","librarienet"){
        @Override
        public String getUri(String link) throws UnsupportedEncodingException {
            return new ScrapyRTAPIArgs()
                    .withUrl(new SplashAPIArgs()
                        .withUrl(link)
                        .withWaitTime("1")
                        .withForbiddenContentType("text/css,font/*")
                        .buildUrl())
                    .withSpiderName(getSpiderName())
                    .buildURL();
        }
    },
    LIBRIS("Libris","libris"){
        @Override
        public String getUri(String link) throws UnsupportedEncodingException {
            return new ScrapyRTAPIArgs()
                    .withUrl(new SplashAPIArgs()
                            .withUrl(link)
                            .withForbiddenContentType("text/css,font/*")
                            .withFilters("easylist")
                            .buildUrl())
                    .withSpiderName(getSpiderName())
                    .buildURL();
        }
    };
    private final String name;
    private final String spiderName;

    Provider(String name, String spiderName){
        this.name = name;
        this.spiderName = spiderName;
    }
    public abstract String getUri(String link) throws UnsupportedEncodingException;

    public static Provider valueOfName(String name) {
        return Arrays.stream(values())
                .filter(provider -> provider.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalAccessError("error"));
    }

    public String getName() {
        return name;
    }

    public String getSpiderName() {
        return spiderName;
    }
}
