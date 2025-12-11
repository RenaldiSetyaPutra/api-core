package renaldi.setya.putra.apicore.utils.wrapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponseWrapper extends HttpServletResponseWrapper {

    private final Map<String, List<String>> headers = new HashMap<>();

    public HttpResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void addHeader(String name, String value) {
        headers.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
        super.addHeader(name, value);
    }

    @Override
    public void setHeader(String name, String value) {
        headers.put(name, new ArrayList<>(List.of(value)));
        super.setHeader(name, value);
    }

    public Map<String, List<String>> getCapturedHeaders() {
        return headers;
    }
}