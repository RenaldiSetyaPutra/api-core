package renaldi.setya.putra.apicore.constant;

public class ErrorCodeConstant {
    // 200
    public static final String SUCCESS_CODE = "20000";

    // 400
    public static final String VALIDATION_CODE = "40001";
    public static final String DATA_EXISTS_CODE = "40002";
    public static final String INVALID_PASSWORD_CODE = "40003";

    // 401
    public static final String UNAUTHORIZE_CODE = "40101";
    public static final String INVALID_TOKEN_CODE = "40102";

    // 404
    public static final String DATA_NOT_FOUND_CODE = "40401";

    // 500
    public static final String GENERAL_ERROR_CODE = "50001";
    public static final String RESPONSE_NULL_CODE = "50002";

    private ErrorCodeConstant() {
    }
}
