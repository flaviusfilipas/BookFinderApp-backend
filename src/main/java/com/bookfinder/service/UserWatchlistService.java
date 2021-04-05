package com.bookfinder.service;

import com.bookfinder.repository.UserWatchlistRepository;
import org.springframework.stereotype.Service;

@Service
public class UserWatchlistService {
    private final UserWatchlistRepository userWatchlistRepository;

    public UserWatchlistService(UserWatchlistRepository userWatchlistRepository) {
        this.userWatchlistRepository = userWatchlistRepository;
    }
}
