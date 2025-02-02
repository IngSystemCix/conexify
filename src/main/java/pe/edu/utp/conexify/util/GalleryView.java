package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class GalleryView implements Serializable {
    private List<Photo> photos;
    private int activeIndex = 0;

    @PostConstruct
    public void init() {
        photos = List.of(
            Photo.builder()
                .itemImageSrc("gallery_demo_1.png")
                .thumbnailImageSrc("gallery_demo_1.png")
                .alt("Gallery 1")
                .title("Gallery 1")
                .build(),
            Photo.builder()
                .itemImageSrc("gallery_demo_2.png")
                .thumbnailImageSrc("gallery_demo_2.png")
                .alt("Gallery 2")
                .title("Gallery 2")
                .build(),
            Photo.builder()
                .itemImageSrc("gallery_demo_3.png")
                .thumbnailImageSrc("gallery_demo_3.png")
                .alt("Gallery 3")
                .title("Gallery 3")
                .build()
        );
    }
}
