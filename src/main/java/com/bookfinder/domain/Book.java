package com.bookfinder.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
