package com.luv2code.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//Reuse for different controllers.
public class BookExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException exc) {
        BookErrorResponse res = new BookErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }


    //global exception
    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(Exception exc) {
        BookErrorResponse res = new BookErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                //exc.getMessage(),
                "Invalid Request",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
