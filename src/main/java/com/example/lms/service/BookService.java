package com.example.lms.service;

import com.example.lms.dto.BookDTO;
import com.example.lms.entity.Book;
import com.example.lms.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepo bookRepo;

    public BookDTO save(BookDTO request){
        return new BookDTO(bookRepo.saveAndFlush(Book.builder()
                .author(request.getAuthor())
                .name(request.getName())
                .build()));
    }
    public List<BookDTO> getAll(){
        List<BookDTO> list = new ArrayList<>();
        for(Book book: bookRepo.findAll()){
            list.add(new BookDTO(book));
        }
        return list;
    }
    public BookDTO getById(Integer id){
        return new BookDTO(bookRepo.getById(id));
    }
    public BookDTO update( Integer id,BookDTO request) {
        Book book = bookRepo.getById(id);
        book.setAuthor(request.getAuthor());
        book.setName(request.getName());
        return new BookDTO(bookRepo.save(book));
    }
    public void delete(Integer id) {
        bookRepo.deleteById(id);
        System.out.println("Deleted");
    }




}
