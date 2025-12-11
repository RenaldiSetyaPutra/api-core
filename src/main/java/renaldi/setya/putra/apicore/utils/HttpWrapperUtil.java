package renaldi.setya.putra.apicore.utils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import renaldi.setya.putra.apicore.utils.wrapper.HttpRequestWrapper;
import renaldi.setya.putra.apicore.utils.wrapper.HttpResponseWrapper;

import java.io.IOException;

public class HttpWrapperUtil {

    public static HttpRequestWrapper wrapRequest(ServletRequest request) throws IOException {
        if (request instanceof HttpRequestWrapper) {
            return (HttpRequestWrapper) request;
        } else {
            return new HttpRequestWrapper((HttpServletRequest) request);
        }
    }

    public static HttpResponseWrapper wrapResponse(ServletResponse response) {
        if (response instanceof HttpResponseWrapper) {
            return (HttpResponseWrapper) response;
        } else {
            return new HttpResponseWrapper((HttpServletResponse) response);
        }
    }
}
