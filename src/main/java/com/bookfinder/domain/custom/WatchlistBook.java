package com.bookfinder.domain.custom;

import com.bookfinder.domain.Book;
import com.bookfinder.domain.User;
import com.bookfinder.domain.UserWatchlist;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WatchlistBook {
    private Book book;
    private User user;
    private UserWatchlist userWatchlist;
}
