package renaldi.setya.putra.apicore.service;

import jakarta.servlet.http.HttpServletRequest;
import renaldi.setya.putra.apicore.dto.ClaimDto;
import renaldi.setya.putra.apicore.exception.ProcessException;

import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.*;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.*;
import static renaldi.setya.putra.apicore.constant.MessageConstant.*;

public abstract class BaseService<REQ, RES> {

    public RES execute(REQ request, ClaimDto claimDto) {
        try {
            return process(request, claimDto);
        } catch (ProcessException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ProcessException(GENERAL_ERROR_CODE, DEFAULT_SOURCE_SYSTEM, GENERAL_ERROR_ID_MESSAGE, GENERAL_ERROR_EN_MESSAGE);
        }
    }

    protected abstract RES process(REQ request, ClaimDto claimDto);
}

