package com.bookfinder.repository;

import com.bookfinder.domain.UserWatchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWatchlistRepository extends JpaRepository<UserWatchlist,Integer> {
}
