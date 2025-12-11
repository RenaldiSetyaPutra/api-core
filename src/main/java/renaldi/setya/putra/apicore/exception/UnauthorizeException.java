//package renaldi.setya.putra.apicore.exception;
//
//import lombok.Generated;
//import org.springframework.http.HttpStatus;
//import renaldi.setya.putra.apicore.dto.BaseResponse;
//
//public class UnauthorizeException extends RuntimeException {
//    private final BaseResponse<?> baseResponse;
//    private final HttpStatus httpStatus;
//
//    public  UnauthorizeException(String code, String sourceSystem, String idnMessage, String engMessage) {
//        this.httpStatus = HttpStatus.UNAUTHORIZED;
//        this.baseResponse = new BaseResponse<>();
//        this.baseResponse.setCode(code);
//        this.baseResponse.setSourceSystem(sourceSystem);
//        this.baseResponse.setIdMessage(idnMessage);
//        this.baseResponse.setEnMessage(engMessage);
//    }
//
//
//    @Generated
//    public BaseResponse<?> getBaseResponse() {
//        return this.baseResponse;
//    }
//
//    @Generated
//    public HttpStatus getHttpStatus() {
//        return this.httpStatus;
//    }
//}
