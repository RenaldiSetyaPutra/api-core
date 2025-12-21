package renaldi.setya.putra.apicore.service;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import renaldi.setya.putra.apicore.dto.ClaimDto;
import renaldi.setya.putra.apicore.exception.ProcessException;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static renaldi.setya.putra.apicore.constant.ClaimEnum.*;
import static renaldi.setya.putra.apicore.constant.ErrorCodeConstant.*;
import static renaldi.setya.putra.apicore.constant.ExceptionConstant.*;
import static renaldi.setya.putra.apicore.constant.MessageConstant.*;

@Service
public class JwtTokenService {
    @Value("${jwt.public.key}")
    private String publicKey;

    @Value("${jwt.private.key}")
    private String privateKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    public PrivateKey loadPrivateKey(String base64PrivateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load private key", e);
        }
    }

    private PublicKey loadPublicKey(String base64PublicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load public key", e);
        }
    }

    public String generateToken(String username, Long userId, Map<String, Object> claims) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationMs);

            return Jwts.builder()
                    .subject(username)
                    .claims(claims)
                    .issuedAt(now)
                    .expiration(expiryDate)
                    .signWith(loadPrivateKey(privateKey), Jwts.SIG.RS512)
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }

    public ClaimDto getClaims(String token) {
        String tokenReplace = token.replace("Bearer ", "");
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(loadPublicKey(publicKey))
                    .build()
                    .parseSignedClaims(tokenReplace)
                    .getPayload();

            return ClaimDto.builder()
                    .username(claims.getSubject())
                    .userProfileId(claims.get(USER_PROFILE_ID.getKey(), Long.class))
                    .cif(claims.get(CIF.getKey(), String.class))
                    .name(claims.get(NAME.getKey(), String.class))
                    .email(claims.get(EMAIL.getKey(), String.class))
                    .phoneNumber(claims.get(PHONE_NUMBER.getKey(), String.class))
                    .build();
        } catch (ExpiredJwtException ex) {
            throw new ProcessException(UNAUTHORIZE_CODE, DEFAULT_SOURCE_SYSTEM, UNAUTHORIZE_ID_MESSAGE, UNAUTHORIZE_EN_MESSAGE);
        } catch (JwtException ex) {
            throw new ProcessException(INVALID_TOKEN_CODE, DEFAULT_SOURCE_SYSTEM, INVALID_TOKEN_ID_MESSAGE, INVALID_TOKEN_EN_MESSAGE);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(loadPublicKey(publicKey))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

