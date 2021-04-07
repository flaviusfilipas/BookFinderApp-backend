package com.bookfinder.dto;

import lombok.Data;

@Data
public class UserWatchlistDTO {
    private Integer id;
    private Boolean hasStockAlert;
    private Boolean hasPriceAlert;
    private Double originalPrice;
    private Double lastPrice;
    private Integer bookId;
    private String userId;
}
