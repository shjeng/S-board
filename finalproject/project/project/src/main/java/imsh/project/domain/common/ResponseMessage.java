package imsh.project.domain.common;

public class ResponseMessage {

    // HTTP Status 200
    public static final String SUCCESS = "Success.";

    // HTTP Status 400
    public static final String VALIDATION_FAILED = "Validation Failed.";
    public static final String DUPLICATE_EMAIL = "Duplicate email.";
    public static final String DUPLICATE_NICKNAME = "Duplicate nickname.";
    public static final String DUPLICATE_TEL_NUMBER = "Duplicate tel number.";
    public static final String NOT_EXISTED_USER = "This user not exist.";
    public static final String NOT_EXISTED_BOARD = "this board does not exist.";
    // HTTP Status 401
    public static final String SGIN_IN_FAIL = "Login information mismatch.";
    public static final String AUTHORIZATION_FAIL = "Authorization Failed.";
    // HTTP Status 403
    public static final String NO_PERMISSION = "Do not have permission.";
    // HTTP Status 500
    public static final String DATABASE_ERROR = "Database error.";
}
