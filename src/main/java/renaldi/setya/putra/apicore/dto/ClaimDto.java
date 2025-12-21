package renaldi.setya.putra.apicore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimDto {
    private Long userProfileId;
    private String cif;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
}
