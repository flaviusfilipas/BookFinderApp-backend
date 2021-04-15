package com.bookfinder.builder;

import com.bookfinder.enums.Provider;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class URIBuilder {

    public static URI buildUriForProvider(String providerName, String bookUrl) throws UnsupportedEncodingException, URISyntaxException {
        return new URI(Provider.valueOfName(providerName).getUri(bookUrl));
    }
}
