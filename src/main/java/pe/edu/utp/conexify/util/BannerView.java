package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class BannerView implements Serializable {
    private List<Photo> photos;
    private UploadedFile upload;

    @PostConstruct
    public void init() {
        photos = List.of(
            Photo.builder()
                .itemImageSrc("banner_demo.jpg")
                .thumbnailImageSrc("banner_demo.jpg")
                .alt("Banner 1")
                .title("Banner 1")
                .build()
        );
    }
}
