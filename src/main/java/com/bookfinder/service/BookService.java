package com.bookfinder.service;

import com.bookfinder.domain.Book;
import com.bookfinder.dto.BookDTO;
import com.bookfinder.dto.custom.WatchlistBookDTO;
import com.bookfinder.mapper.BookMapper;
import com.bookfinder.mapper.custom.WatchlistBookMapper;
import com.bookfinder.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final WatchlistBookMapper watchlistBookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper, WatchlistBookMapper watchlistBookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.watchlistBookMapper = watchlistBookMapper;
    }

    public BookDTO save(BookDTO bookDTO){
        BookDTO bookByIsbn = this.findByISBN(bookDTO.getIsbn());
        if(bookByIsbn != null){
            return bookByIsbn;
        }
        Book entity = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(entity);
        return bookMapper.toDto(savedBook);
    }

    public BookDTO findByISBN(String isbn){
        Book book = bookRepository.findByisbn(isbn);
        return bookMapper.toDto(book);
    }

    public List<WatchlistBookDTO> getAllWatchlistBooksByUserId(String userId){
        return bookRepository.findWatchlistBooksByUserId(userId)
                .stream()
                .map(watchlistBookMapper::toDto)
                .collect(Collectors.toList());
    }
}
