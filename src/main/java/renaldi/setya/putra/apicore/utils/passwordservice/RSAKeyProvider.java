package renaldi.setya.putra.apicore.utils.passwordservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RSAKeyProvider {
    @Value("${psd.public.key}")
    private String publicKey;

    public String getPublicKey() { return publicKey; }
}
