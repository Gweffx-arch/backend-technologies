package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/books")
public class BookUiController {

    private final BookService service;

    public BookUiController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", service.findAll());
        model.addAttribute("newBook", new BookDto(null, "", "", null));
        return "books";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("newBook") BookDto bookDto) {
        service.create(bookDto);
        return "redirect:/ui/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/ui/books";
    }
}