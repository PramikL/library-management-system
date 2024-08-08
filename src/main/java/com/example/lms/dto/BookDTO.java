package com.example.lms.dto;

import com.example.lms.entity.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BookDTO {
    private Integer id;


    @NotEmpty(message = "Book name is required")
    private String name;

    @NotEmpty(message = "Author name is required")
    private String author;

    public BookDTO(Book book){
        this.id = book.getId();
        this.name= book.getName();
        this.author= book.getAuthor();
    }
}
