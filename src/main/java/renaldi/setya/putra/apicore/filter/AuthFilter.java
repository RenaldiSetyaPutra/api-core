package renaldi.setya.putra.apicore.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import renaldi.setya.putra.apicore.dto.ClaimDto;
import renaldi.setya.putra.apicore.exception.ProcessException;
import renaldi.setya.putra.apicore.service.JwtTokenService;
import renaldi.setya.putra.apicore.utils.CacheUtil;

import java.io.IOException;

import static renaldi.setya.putra.apicore.constant.CacheConstant.CACHE_TOKEN;
import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.UNAUTHORIZE_CODE;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.DEFAULT_SOURCE_SYSTEM;
import static renaldi.setya.putra.apicore.constant.ExcludeUrlConstant.EXCLUDE_URLS;
import static renaldi.setya.putra.apicore.constant.HeaderConstant.AUTHORIZATION;
import static renaldi.setya.putra.apicore.constant.MessageConstant.UNAUTHORIZE_EN_MESSAGE;
import static renaldi.setya.putra.apicore.constant.MessageConstant.UNAUTHORIZE_ID_MESSAGE;

@Component
@Slf4j
@Order(0)
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final CacheUtil cacheUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        boolean filter = EXCLUDE_URLS.stream()
                .noneMatch(uri::contains);

        if (filter) {
            String auth = request.getHeader(AUTHORIZATION);

            if (StringUtils.isBlank(auth)) {
                throw new ProcessException(UNAUTHORIZE_CODE, DEFAULT_SOURCE_SYSTEM, UNAUTHORIZE_ID_MESSAGE, UNAUTHORIZE_EN_MESSAGE);
            }

            ClaimDto claim = jwtTokenService.getClaims(auth);
            var cache = cacheUtil.getCache(CACHE_TOKEN + "|" + claim.getUserProfileId());

            if (cache == null) {
                throw new ProcessException(UNAUTHORIZE_CODE, DEFAULT_SOURCE_SYSTEM, UNAUTHORIZE_ID_MESSAGE, UNAUTHORIZE_EN_MESSAGE);
            }
        }

        filterChain.doFilter(request, response);
    }
}
