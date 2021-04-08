package com.bookfinder.dto;

import com.bookfinder.dto.custom.OfferDTO;
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
    private OfferDTO offer;
}
