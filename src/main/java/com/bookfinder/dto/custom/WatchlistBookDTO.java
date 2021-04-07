package com.bookfinder.dto.custom;

import com.bookfinder.dto.BookDTO;
import lombok.Data;

@Data
public class WatchlistBookDTO {
    private Integer id;
    private BookDTO bookDTO;
    private Double originalPrice;
    private Double lastPrice;
    private String currentUserId;
    private Boolean hasStockAlert;
    private Boolean hasPriceAlert;
}
