package app.api.Exception;

import app.api.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException ex) {

        ErrorResponse errorResponse=new ErrorResponse(
           "Exception_in_Argument", //Error Code
                ex.getMessage(), //Error Message
                LocalDateTime.now() //Error TimeStamp
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

}
