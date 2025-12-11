package renaldi.setya.putra.apicore.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import renaldi.setya.putra.apicore.dto.BaseResponse;
import renaldi.setya.putra.apicore.exception.ProcessException;

import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.*;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ResponseEntity<BaseResponse<T>> handleValidationException(MethodArgumentNotValidException ex) {
        String defaultMsg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(DEFAULT_MESSAGE_EN);

        String idnMsg = translateIdn(defaultMsg);
        String engMsg = translateEng(defaultMsg);

        BaseResponse<T> response = new BaseResponse<>(VALIDATION_CODE, DEFAULT_SOURCE_SYSTEM, idnMsg, engMsg);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public <T> ResponseEntity<BaseResponse<T>> handleConstraintViolation(ConstraintViolationException ex) {

        String defaultMsg = ex.getConstraintViolations()
                .stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse(DEFAULT_MESSAGE_EN);

        String idnMsg = translateIdn(defaultMsg);
        String engMsg = translateEng(defaultMsg);

        BaseResponse<T> response = new BaseResponse<>(VALIDATION_CODE, DEFAULT_SOURCE_SYSTEM, idnMsg, engMsg);

        return ResponseEntity.badRequest().body(response);
    }

    private String translateIdn(String msg) {
        return IDN_MAP.getOrDefault(msg, DEFAULT_MESSAGE_ID);
    }

    private String translateEng(String msg) {
        return ENG_MAP.getOrDefault(msg, DEFAULT_MESSAGE_EN);
    }

    @ExceptionHandler(ProcessException.class)
    public ResponseEntity<?> unauthorizedException(ProcessException e) {
        return new ResponseEntity<>(e.getBaseResponse(), e.getHttpStatus());
    }
}
