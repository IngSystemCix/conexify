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
        photos = List.of();
    }
}
