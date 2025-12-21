package renaldi.setya.putra.apicore.service;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import renaldi.setya.putra.apicore.dto.ClaimDto;
import renaldi.setya.putra.apicore.exception.ProcessException;
import renaldi.setya.putra.apicore.utils.CacheUtil;

import static renaldi.setya.putra.apicore.constant.CacheConstant.CACHE_TOKEN;
import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.UNAUTHORIZE_CODE;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.DEFAULT_SOURCE_SYSTEM;
import static renaldi.setya.putra.apicore.constant.HeaderConstant.AUTHORIZATION;
import static renaldi.setya.putra.apicore.constant.MessageConstant.UNAUTHORIZE_EN_MESSAGE;
import static renaldi.setya.putra.apicore.constant.MessageConstant.UNAUTHORIZE_ID_MESSAGE;

@Service
@RequiredArgsConstructor
public class BaseController {
    private final JwtTokenService jwtTokenService;
    private final CacheUtil cacheUtil;

    public HttpServletRequest getCurrentHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    public ClaimDto getClaims() {
        HttpServletRequest httpServletRequest = getCurrentHttpServletRequest();
        String auth = httpServletRequest.getHeader(AUTHORIZATION);

        if (StringUtils.isBlank(auth)) {
            throw new ProcessException(UNAUTHORIZE_CODE, DEFAULT_SOURCE_SYSTEM, UNAUTHORIZE_ID_MESSAGE, UNAUTHORIZE_EN_MESSAGE, HttpStatus.UNAUTHORIZED);
        }

        ClaimDto claim = jwtTokenService.getClaims(auth);
        var cache = cacheUtil.getCache(CACHE_TOKEN + "|" + claim.getUserProfileId());

        if (cache == null) {
            throw new ProcessException(UNAUTHORIZE_CODE, DEFAULT_SOURCE_SYSTEM, UNAUTHORIZE_ID_MESSAGE, UNAUTHORIZE_EN_MESSAGE, HttpStatus.UNAUTHORIZED);
        }

        return claim;
    }
}
