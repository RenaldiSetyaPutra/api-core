package renaldi.setya.putra.apicore.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClaimConstant {
    USER_PROFILE_ID("userProfileId"),
    USERNAME("username"),
    NAME("name"),
    EMAIL("email"),
    PHONE_NUMBER("phoneNumber");

    private final String key;
}
