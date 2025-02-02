package pe.edu.utp.conexify.util;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {
    private String itemImageSrc;
    private String thumbnailImageSrc;
    private String alt;
    private String title;
}
