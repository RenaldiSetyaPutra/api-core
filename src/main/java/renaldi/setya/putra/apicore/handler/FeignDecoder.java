package renaldi.setya.putra.apicore.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import renaldi.setya.putra.apicore.dto.BaseResponse;
import renaldi.setya.putra.apicore.exception.ProcessException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.*;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.*;
import static renaldi.setya.putra.apicore.constant.MessageConstant.*;

@Slf4j
public class FeignDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Exception decode(String methodKey, Response response) {
        try {
            InputStream inputStream = response.body().asInputStream();
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            BaseResponse<?> baseResponse = objectMapper.readValue(body, BaseResponse.class);

            return new ProcessException(
                    baseResponse.getCode(),
                    baseResponse.getSourceSystem(),
                    baseResponse.getIdMessage(),
                    baseResponse.getEnMessage(),
                    HttpStatus.valueOf(response.status())
            );

        } catch (Exception e) {
            return new ProcessException(
                    GENERAL_ERROR_CODE,
                    DEFAULT_SOURCE_SYSTEM,
                    GENERAL_ERROR_ID_MESSAGE,
                    GENERAL_ERROR_EN_MESSAGE,
                    HttpStatus.valueOf(response.status())
            );
        }
    }
}
