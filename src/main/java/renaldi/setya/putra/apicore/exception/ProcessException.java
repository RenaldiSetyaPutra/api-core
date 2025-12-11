package renaldi.setya.putra.apicore.exception;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import renaldi.setya.putra.apicore.dto.BaseResponse;

public class ProcessException extends RuntimeException {
    private final BaseResponse<?> baseResponse;
    private final HttpStatus httpStatus;

    public ProcessException(String code, String sourceSystem, String idnMessage, String engMessage) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.baseResponse = new BaseResponse<>();
        this.baseResponse.setCode(code);
        this.baseResponse.setSourceSystem(sourceSystem);
        this.baseResponse.setIdMessage(idnMessage);
        this.baseResponse.setEnMessage(engMessage);
    }

    public ProcessException(String code, String sourceSystem, String idnMessage, String engMessage, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.baseResponse = new BaseResponse<>();
        this.baseResponse.setCode(code);
        this.baseResponse.setSourceSystem(sourceSystem);
        this.baseResponse.setIdMessage(idnMessage);
        this.baseResponse.setEnMessage(engMessage);
    }

    @Generated
    public BaseResponse<?> getBaseResponse() {
        return this.baseResponse;
    }

    @Generated
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
