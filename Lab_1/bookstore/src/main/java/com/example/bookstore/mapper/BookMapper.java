package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);

    void updateEntityFromDto(BookDto bookDto, @MappingTarget Book book);
}