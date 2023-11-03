package net.blog.springbootrestapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// Throw this exception when writing business logic and validating the parameters.
@Getter
public class BlogAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public BlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BlogAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }
}
