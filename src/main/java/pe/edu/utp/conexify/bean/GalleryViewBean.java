package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import pe.edu.utp.conexify.dto.PhotoDTO;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class GalleryViewBean implements Serializable {
    private List<PhotoDTO> photos;
    private int activeIndex = 0;

    @PostConstruct
    public void init() {
        photos = List.of();
    }
}
