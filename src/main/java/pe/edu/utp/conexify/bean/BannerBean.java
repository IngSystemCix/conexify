package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;
import pe.edu.utp.conexify.dto.PhotoDTO;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class BannerBean implements Serializable {
    private List<PhotoDTO> photos;
    private UploadedFile upload;

    @PostConstruct
    public void init() {
        photos = List.of(
            PhotoDTO.builder()
                .itemImageSrc("banner_demo.jpg")
                .thumbnailImageSrc("banner_demo.jpg")
                .alt("Banner 1")
                .title("Banner 1")
                .build()
        );
    }
}
