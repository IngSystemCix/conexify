package pe.edu.utp.conexify.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PoliciesDTO {
    private String title;
    private String description;
}
