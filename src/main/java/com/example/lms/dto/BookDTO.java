package com.example.lms.dto;

import com.example.lms.entity.Book;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BookDTO {
    private Integer id;
    private String name;
    private String author;

    public BookDTO(Book book){
        this.id = book.getId();
        this.name= book.getName();
        this.author= book.getAuthor();
    }
}
