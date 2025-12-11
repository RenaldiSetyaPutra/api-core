package renaldi.setya.putra.apicore.logging;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import renaldi.setya.putra.apicore.utils.JsonUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FeignLogging extends Logger {

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(methodTag(configKey) + " " + format, args));
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        String body = request.body() != null ? new String(request.body()) : "";
        Map<String, String> requestHeaders = new HashMap<>();
        request.headers().forEach((key, valueList) -> {
            String value = valueList != null && !valueList.isEmpty()
                    ? valueList.iterator().next()
                    : "";
            requestHeaders.put(key, value);
        });

        log.info(
                """
                =========================================================================
                -----------------------------OUTBOUND REQUEST----------------------------
                URI             : {}
                Method          : {}
                Request Header  : {}
                Request Body    : {}
                =========================================================================
                """,
                request.url(),
                request.httpMethod(),
                requestHeaders,
                JsonUtil.prettyJson(body)
        );
    }

    @Override
    protected Response logAndRebufferResponse(
            String configKey,
            Level logLevel,
            Response response,
            long elapsedTime
    ) throws IOException {

        String body = "";
        if (response.body() != null) {
            body = Util.toString(response.body().asReader());
        }

        Map<String, String> responseHeaders = new HashMap<>();
        response.headers().forEach((key, valueList) -> {
            String value = valueList != null && !valueList.isEmpty()
                    ? valueList.iterator().next()
                    : "";
            responseHeaders.put(key, value);
        });

        log.info(
                """
                =========================================================================
                ----------------------------OUTBOUND RESPONSE----------------------------
                URI             : {}
                Status          : {}
                Response Header : {}
                Response Body   : {}
                =========================================================================
                """,
                response.request().url(),
                response.status(),
                responseHeaders,
                JsonUtil.prettyJson(body)
        );

        return response.toBuilder()
                .body(body.getBytes(StandardCharsets.UTF_8))
                .build();
    }
}