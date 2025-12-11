package renaldi.setya.putra.apicore.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import renaldi.setya.putra.apicore.utils.JsonUtil;
import renaldi.setya.putra.apicore.utils.wrapper.HttpRequestWrapper;
import renaldi.setya.putra.apicore.utils.wrapper.HttpResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Order(1)
@RequiredArgsConstructor
public class LogInternalFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        HttpRequestWrapper req = new HttpRequestWrapper(request);
        HttpResponseWrapper resWrapper = new HttpResponseWrapper(response);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(resWrapper);

        long start = System.currentTimeMillis();

        String bodyReq = new String(req.getCachedBody(), StandardCharsets.UTF_8);
        Map<String, String> requestHeaders = new HashMap<>();
        req.getHeaderNames().asIterator()
                .forEachRemaining(h -> requestHeaders.put(h, req.getHeader(h)));

        log.info(
                """
                =========================================================================
                ------------------------------INBOUND REQUEST----------------------------
                URI             : {}
                Method          : {}
                Request Header  : {}
                Request Body    : {}
                =========================================================================
                """,
                req.getRequestURI(),
                req.getMethod(),
                requestHeaders,
                JsonUtil.prettyJson(bodyReq)
        );

        filterChain.doFilter(req, res);

        long duration = System.currentTimeMillis() - start;

        String bodyRes = new String(res.getContentAsByteArray(), StandardCharsets.UTF_8);
        Map<String, String> responseHeaders = new HashMap<>();
        resWrapper.getCapturedHeaders().forEach((key, values) -> responseHeaders.put(key, String.join(", ", values)));

        log.info(
                """
                =========================================================================
                ------------------------------INBOUND RESPONSE---------------------------
                URI             : {}
                Status          : {}
                Duration        : {} ms
                Response Header : {}
                Response Body   : {}
                =========================================================================
                """,
                req.getRequestURI(),
                res.getStatus(),
                duration,
                responseHeaders,
                JsonUtil.prettyJson(bodyRes)
        );

        res.copyBodyToResponse();
    }
}
