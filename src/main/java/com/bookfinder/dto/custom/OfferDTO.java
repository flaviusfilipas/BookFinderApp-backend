package com.bookfinder.dto.custom;

import lombok.Data;

@Data
public class OfferDTO {
    private String link;
    private String provider;
    private Boolean hasStock;
}
