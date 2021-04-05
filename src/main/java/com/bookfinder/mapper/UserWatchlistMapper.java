package com.bookfinder.mapper;

import com.bookfinder.domain.UserWatchlist;
import com.bookfinder.dto.UserWatchlistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserWatchlistMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    UserWatchlistDTO toDto(UserWatchlist userWatchlist);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    UserWatchlist toEntity(UserWatchlistDTO userWatchlistDTO);
}
