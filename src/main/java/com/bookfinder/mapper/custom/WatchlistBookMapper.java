package com.bookfinder.mapper.custom;

import com.bookfinder.domain.custom.WatchlistBook;
import com.bookfinder.dto.custom.WatchlistBookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WatchlistBookMapper {
    @Mapping(source = "book.title", target = "bookDTO.title")
    @Mapping(source = "book.author", target = "bookDTO.author")
    @Mapping(source = "book.isbn", target = "bookDTO.isbn")
    @Mapping(source = "book.imgUrl", target = "bookDTO.imgUrl")
    @Mapping(source = "book.publisher", target = "bookDTO.publisher")
    @Mapping(source = "book.coverType", target = "bookDTO.coverType")
    @Mapping(source = "book.numberOfPages", target = "bookDTO.numberOfPages")
    @Mapping(source = "book.link", target = "bookDTO.offer.link")
    @Mapping(source = "book.provider", target = "bookDTO.offer.provider")
    @Mapping(source = "book.hasStock", target = "bookDTO.offer.hasStock")
    @Mapping(source = "userWatchlist.id", target = "id")
    @Mapping(source = "userWatchlist.hasStockAlert", target = "hasStockAlert")
    @Mapping(source = "userWatchlist.hasPriceAlert", target = "hasPriceAlert")
    @Mapping(source = "userWatchlist.lastPrice", target = "lastPrice")
    @Mapping(source = "userWatchlist.originalPrice", target = "originalPrice")
    @Mapping(source = "user.id", target = "currentUserId")
    WatchlistBookDTO toDto(WatchlistBook watchlistBook);
}
