package app.api.Exception;

import app.api.Exception.CustomException.UserAlreadyExistException;
import app.api.Persistence.DTOS.Responses.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Error code constants
    public static final String INVALID_INPUT = "ERR_INVALID_INPUT";
    public static final String FILE_PROCESSING_ERROR = "ERR_FILE_PROCESSING";
    public static final String UNSUPPORTED_MEDIA_TYPE = "ERR_UNSUPPORTED_MEDIA_TYPE";
    public static final String FILE_SIZE_EXCEEDED = "ERR_FILE_SIZE_EXCEEDED";
    public static final String USER_ALREADY_EXISTS = "ERR_USER_ALREADY_EXISTS";
    public static final String INVALID_CREDENTIALS = "ERR_INVALID_CREDENTIALS";
    public static final String RESOURCE_NOT_FOUND = "ERR_RESOURCE_NOT_FOUND";
    public static final String ACCESS_DENIED = "ERR_ACCESS_DENIED";
    public static final String METHOD_NOT_ALLOWED = "ERR_METHOD_NOT_ALLOWED";
    public static final String VALIDATION_ERROR = "ERR_VALIDATION_FAILED";
    public static final String INTERNAL_SERVER_ERROR = "ERR_INTERNAL_SERVER";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                INVALID_INPUT,
                "Invalid input provided: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                FILE_PROCESSING_ERROR,
                "Error processing file operation"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaTypeException(UnsupportedMediaTypeStatusException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                UNSUPPORTED_MEDIA_TYPE,
                "The provided file format is not supported"
        );
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                FILE_SIZE_EXCEEDED,
                "The uploaded file exceeds the maximum allowed size"
        );
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                USER_ALREADY_EXISTS,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                INVALID_CREDENTIALS,
                "Invalid username or password"
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    // Additional common exception handlers
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                ACCESS_DENIED,
                "You don't have permission to access this resource"
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                RESOURCE_NOT_FOUND,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {

        ErrorResponse errorResponse = createErrorResponse(
                METHOD_NOT_ALLOWED,
                String.format("Method %s is not supported for this endpoint", ex.getMethod())
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = createErrorResponse(
                VALIDATION_ERROR,
                String.join(", ", errors)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = createErrorResponse(
                VALIDATION_ERROR,
                String.join(", ", errors)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Catch-all exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        ErrorResponse errorResponse = createErrorResponse(
                INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // Helper method to create consistent error responses
    private ErrorResponse createErrorResponse(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}