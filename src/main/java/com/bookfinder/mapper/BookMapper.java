package com.bookfinder.mapper;

import com.bookfinder.domain.Book;
import com.bookfinder.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "offer.provider", source = "provider")
    @Mapping(target = "offer.hasStock", source = "hasStock")
    @Mapping(target = "offer.link", source = "link")
    BookDTO toDto(Book book);

    @Mapping(target = "provider", source = "offer.provider")
    @Mapping(target = "hasStock", source = "offer.hasStock")
    @Mapping(target = "link", source = "offer.link")
    Book toEntity(BookDTO bookDTO);
}
