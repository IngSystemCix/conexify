package pe.edu.utp.conexify.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDTO {
    private String itemImageSrc;
    private String thumbnailImageSrc;
    private String alt;
    private String title;
}
