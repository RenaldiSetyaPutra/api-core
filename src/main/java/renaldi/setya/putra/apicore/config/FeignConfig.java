package renaldi.setya.putra.apicore.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import renaldi.setya.putra.apicore.handler.FeignDecoder;
import renaldi.setya.putra.apicore.logging.FeignLogging;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignDecoder();
    }

    @Bean
    public feign.Logger feignLogger() {
        return new FeignLogging();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
