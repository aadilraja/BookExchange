package app.api.Exception;

import app.api.Responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException ex) {

        ErrorResponse errorResponse=new ErrorResponse(
           "INVALID_INPUT", //Error Code
                ex.getMessage(), //Error Message
                LocalDateTime.now() //Error TimeStamp
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> ioException(IOException ex) {
        ErrorResponse errorResponse=new ErrorResponse(
                "ERROR_SAVING_FILE",
                ex.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public ResponseEntity<ErrorResponse> unsupportedMediaTypeStatusException(UnsupportedMediaTypeStatusException ex) {
        ErrorResponse errorResponse=new ErrorResponse(
                "INVALID_MEDIA_TYPE",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> maxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        ErrorResponse errorResponse=new ErrorResponse(
                "INVALID_SIZE",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
    }

}
