package com.bookfinder.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_watchlist")
@Data
public class UserWatchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean hasStockAlert;
    private Boolean hasPriceAlert;
    private Double originalPrice;
    private Double lastPrice;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;

}
