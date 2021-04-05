package com.bookfinder.controller;

import com.bookfinder.service.UserWatchlistService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-watchlists")
public class UserWatchlistResource {
    private final UserWatchlistService userWatchlistService;

    public UserWatchlistResource(UserWatchlistService userWatchlistService) {
        this.userWatchlistService = userWatchlistService;
    }
}
