//package renaldi.setya.putra.apicore.handler;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import renaldi.setya.putra.apicore.exception.ProcessException;
//
//@RestControllerAdvice
//public class ProcessExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler({ProcessException.class})
//    public ResponseEntity<?> processException(ProcessException e) {
//        return new ResponseEntity<>(e.getBaseResponse(), e.getHttpStatus());
//    }
//}
