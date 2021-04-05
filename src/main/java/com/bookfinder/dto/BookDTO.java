package com.bookfinder.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String imgUrl;
    private String publisher;
    private String coverType;
    private Integer numberOfPages;
    private String link;
    private String provider;
    private Boolean hasStock;
}
