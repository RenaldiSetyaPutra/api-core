package renaldi.setya.putra.apicore.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClaimDto {
    private Long userProfileId;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private Date issuedAt;
    private Date expiredAt;
}
