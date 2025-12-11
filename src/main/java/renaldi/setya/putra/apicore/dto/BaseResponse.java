package renaldi.setya.putra.apicore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.*;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.*;
import static renaldi.setya.putra.apicore.constant.MessageConstant.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 944427764497422235L;

    private String activityRefCode;
    private String sourceSystem;
    private String code;
    private String idMessage;
    private String enMessage;
    private T result;

    public BaseResponse() {
        this.code = SUCCESS_CODE;
        this.sourceSystem = DEFAULT_SOURCE_SYSTEM;
        this.idMessage = SUCCESS_ID_MESSAGE;
        this.enMessage = SUCCESS_EN_MESSAGE;
        this.activityRefCode = generateRefCode();
    }

    public BaseResponse(T result) {
        this();
        this.result = result;
    }

    public BaseResponse(String code, String sourceSystem, String idMsg, String enMsg) {
        this.code = code;
        this.sourceSystem = sourceSystem;
        this.idMessage = idMsg;
        this.enMessage = enMsg;
        this.activityRefCode = generateRefCode();
    }

    public BaseResponse(String code, String sourceSystem, String idMsg, String enMsg, T result) {
        this.code = code;
        this.sourceSystem = sourceSystem;
        this.idMessage = idMsg;
        this.enMessage = enMsg;
        this.result = result;
        this.activityRefCode = generateRefCode();
    }

    public BaseResponse(String code, String sourceSystem, String idMsg, String enMsg, String activityRefCode, T result) {
        this.code = code;
        this.sourceSystem = sourceSystem;
        this.idMessage = idMsg;
        this.enMessage = enMsg;
        this.activityRefCode = activityRefCode;
        this.result = result;
    }

    private String generateRefCode() {
        return UUID.randomUUID().toString();
    }
}
