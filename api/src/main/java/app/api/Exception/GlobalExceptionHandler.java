package app.api.Exception;

import app.api.Exception.CustomException.UserAlreadyExistException;
import app.api.Persistence.DTOS.Responses.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String getErrorMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.INVALID_INPUT_KEY, ex.getMessage());
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.INVALID_INPUT, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.FILE_PROCESSING_ERROR_KEY);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.FILE_PROCESSING_ERROR, message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaTypeException(UnsupportedMediaTypeStatusException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.UNSUPPORTED_MEDIA_TYPE_KEY);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.UNSUPPORTED_MEDIA_TYPE, message);
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.FILE_SIZE_EXCEEDED_KEY);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.FILE_SIZE_EXCEEDED, message);
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.USER_ALREADY_EXISTS_KEY, ex.getMessage());
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.USER_ALREADY_EXISTS, message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.INVALID_CREDENTIALS_KEY);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.INVALID_CREDENTIALS, message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.ACCESS_DENIED_KEY);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.ACCESS_DENIED, message);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.RESOURCE_NOT_FOUND_KEY, ex.getMessage());
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.RESOURCE_NOT_FOUND, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.METHOD_NOT_ALLOWED_KEY, ex.getMethod());
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.METHOD_NOT_ALLOWED, message);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList().toString();
        String message = this.getErrorMessage(ErrorMessageKeys.VALIDATION_ERROR_KEY, details);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.VALIDATION_ERROR, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String details = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList().toString();
        String message = this.getErrorMessage(ErrorMessageKeys.VALIDATION_ERROR_KEY, details);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.VALIDATION_ERROR, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        String message = this.getErrorMessage(ErrorMessageKeys.INTERNAL_SERVER_ERROR_KEY);
        ErrorResponse errorResponse = createErrorResponse(ErrorMessageKeys.INTERNAL_SERVER_ERROR, message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }



    private ErrorResponse createErrorResponse(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}