package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BookDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public BookDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public BookDto create(BookDto dto) {
        Book entity = mapper.toEntity(dto);
        Book saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public BookDto update(Long id, BookDto dto) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        mapper.updateEntityFromDto(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}