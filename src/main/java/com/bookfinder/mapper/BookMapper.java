package com.bookfinder.mapper;

import com.bookfinder.domain.Book;
import com.bookfinder.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);
}
