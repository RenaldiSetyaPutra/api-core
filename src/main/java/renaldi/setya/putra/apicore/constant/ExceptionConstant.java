package renaldi.setya.putra.apicore.constant;

import java.util.Map;

public class ExceptionConstant {
    public static final Map<String, String> IDN_MAP = Map.of(
            "must not be blank", "Field tidak boleh kosong",
            "must not be null", "Field tidak boleh bernilai null",
            "must be a well-formed email address", "Format email tidak valid",
            "must be greater than or equal to 0", "Nilai harus lebih besar atau sama dengan 0",
            "must be a number", "Harus berupa angka"
    );

    public static final Map<String, String> ENG_MAP = Map.of(
            "must not be blank", "Field must not be blank",
            "must not be null", "Field must not be null",
            "must be a well-formed email address", "Email format is invalid",
            "must be greater than or equal to 0", "Value must be greater than or equal to 0",
            "must be a number", "Must be a number"
    );

    public static final String DEFAULT_SOURCE_SYSTEM = "100";
    public static final String DEFAULT_MESSAGE_EN = "Invalid request";
    public static final String DEFAULT_MESSAGE_ID = "Request tidak valid";

    private ExceptionConstant() {
    }
}
