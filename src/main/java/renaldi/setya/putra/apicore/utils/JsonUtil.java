package renaldi.setya.putra.apicore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static String prettyJson(Object obj) {
        try {
            if (obj instanceof String jsonString) {
                Object json = mapper.readValue(jsonString, Object.class);
                return mapper.writeValueAsString(json);
            }
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return String.valueOf(obj);
        }
    }
}

