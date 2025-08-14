package app.api.Exception;

public final class ErrorMessageKeys {
    private ErrorMessageKeys() {} // Private constructor to prevent instantiation

    public static final String INVALID_INPUT_KEY = "error.input.invalid";
    public static final String FILE_PROCESSING_ERROR_KEY = "error.file.processing";
    public static final String UNSUPPORTED_MEDIA_TYPE_KEY = "error.media.unsupported";
    public static final String FILE_SIZE_EXCEEDED_KEY = "error.file.size";
    public static final String USER_ALREADY_EXISTS_KEY = "error.user.exists";
    public static final String INVALID_CREDENTIALS_KEY = "error.auth.credentials";
    public static final String RESOURCE_NOT_FOUND_KEY = "error.resource.notfound";
    public static final String ACCESS_DENIED_KEY = "error.auth.denied";
    public static final String METHOD_NOT_ALLOWED_KEY = "error.method.notallowed";
    public static final String VALIDATION_ERROR_KEY = "error.validation";
    public static final String INTERNAL_SERVER_ERROR_KEY = "error.server.internal";



    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String FILE_PROCESSING_ERROR = "FILE_PROCESSING_ERROR";
    public static final String UNSUPPORTED_MEDIA_TYPE = "UNSUPPORTED_MEDIA_TYPE";
    public static final String FILE_SIZE_EXCEEDED = "FILE_SIZE_EXCEEDED";
    public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    public static final String ACCESS_DENIED = "ACCESS_DENIED";
    public static final String METHOD_NOT_ALLOWED = "METHOD_NOT_ALLOWED";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

}