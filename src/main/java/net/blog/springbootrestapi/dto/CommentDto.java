package net.blog.springbootrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @Size(min = 2, message = "At least 2 characters are required.")
    private String body;

}
