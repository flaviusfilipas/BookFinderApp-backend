package com.bookfinder.controller;

import com.bookfinder.domain.custom.WatchlistBook;
import com.bookfinder.dto.BookDTO;
import com.bookfinder.dto.UserWatchlistDTO;
import com.bookfinder.dto.custom.WatchlistBookDTO;
import com.bookfinder.service.BookService;
import com.bookfinder.service.UserWatchlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookResource {
    private final BookService bookService;
    private final UserWatchlistService userWatchlistService;

    public BookResource(BookService bookService, UserWatchlistService userWatchlistService) {
        this.bookService = bookService;
        this.userWatchlistService = userWatchlistService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) throws URISyntaxException {
        BookDTO save = bookService.save(bookDTO);
        return ResponseEntity.created(new URI("/api/books/" + save.getId())).build();
    }
    @PostMapping("/watchlist")
    public ResponseEntity<Void> addToWatchlist(@RequestBody WatchlistBookDTO watchlistBookDTO) throws URISyntaxException {
        BookDTO savedBook = bookService.save(watchlistBookDTO.getBookDTO());
        UserWatchlistDTO savedUserWatchlist = userWatchlistService.save(watchlistBookDTO,
                savedBook.getId());
        return ResponseEntity.created(new URI("/api/books/watchlist/" + savedUserWatchlist.getId()))
                .build();
    }
    @GetMapping("/watchlist/user/{userId}")
    public ResponseEntity<List<WatchlistBookDTO>> getAllWatchlistBooksByUserId(@PathVariable String userId){
        List<WatchlistBookDTO> allWatchlistBooksByUserId = bookService.getAllWatchlistBooksByUserId(userId);
        return ResponseEntity.ok(allWatchlistBooksByUserId);
    }
}
