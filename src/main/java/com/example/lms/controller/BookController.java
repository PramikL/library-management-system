package com.example.lms.controller;

import com.example.lms.dto.BookDTO;
import com.example.lms.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private static final String RETURN_URL = "redirect:/";

    @GetMapping()
    public String loadBookPage(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book";
    }

    @GetMapping("/add_book")
    public String showCreationForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "add_book";
    }

    @PostMapping("/save_book")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveBook(Model model, @Valid @ModelAttribute("book") BookDTO book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_book";
        }
        bookService.save(book);
        return RETURN_URL;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "update_book";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateBook(@PathVariable Integer id, @Valid @ModelAttribute("book") BookDTO book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation Errors:"+ bindingResult.getAllErrors());
            book.setId(id);
            return "update_book";
        }

        bookService.update(id, book);
        return RETURN_URL;
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable Integer id,Model model){
        bookService.delete(id);
        return RETURN_URL;
    }


}
