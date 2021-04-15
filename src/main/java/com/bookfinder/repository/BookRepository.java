package com.bookfinder.repository;

import com.bookfinder.domain.Book;
import com.bookfinder.domain.custom.WatchlistBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findByisbnAndProvider(String isbn, String provider);

    @Query("SELECT NEW com.bookfinder.domain.custom.WatchlistBook(b,u,uw) " +
            "from Book b " +
            "inner join UserWatchlist uw on uw.book.id = b.id " +
            "inner join User u on u.id = uw.user.id " +
            "where u.id = :userId")
    List<WatchlistBook> findWatchlistBooksByUserId(@Param("userId") String userId);

    @Query("SELECT NEW com.bookfinder.domain.custom.WatchlistBook(b,u,uw) " +
            "from Book b " +
            "inner join UserWatchlist uw on uw.book.id = b.id " +
            "inner join User u on u.id = uw.user.id ")
    List<WatchlistBook> findAllWatchlistBooks();

    @Query("SELECT NEW com.bookfinder.domain.custom.WatchlistBook(b,u,uw) " +
            "from Book b " +
            "inner join UserWatchlist uw on uw.book.id = b.id " +
            "inner join User u on u.id = uw.user.id " +
            "where b.id = :id")
    WatchlistBook findWatchlistBookById(Integer id);
}
