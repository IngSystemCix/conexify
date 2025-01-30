package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pe.edu.utp.conexify.service.EmojinService;
import pe.edu.utp.conexify.service.EmojinServiceImpl;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@Named
@ApplicationScoped
public class Emojin implements Serializable {

    private final EmojinService emojinService;
    private Map<String, List<String>> emojisByCategory;
    private List<String> categories;
    private String category;

    @Inject
    public Emojin() {
        this.category = "smileys-emotion";
        this.categories = new ArrayList<>(Arrays.asList(
                "smileys-emotion",
                "people-body",
                "animals-nature",
                "food-drink",
                "travel-places",
                "activities",
                "objects",
                "symbols",
                "flags"
        ));
        this.emojinService = new EmojinServiceImpl();
        emojisByCategory = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        if (emojisByCategory == null || emojisByCategory.isEmpty()) {
            emojisByCategory = new HashMap<>();
            for (String category : categories) {
                emojisByCategory.put(category, emojinService.getAllEmojis(category).get(category));
            }
        }
    }

    public String getEmoticonTab(String category) {
        return switch (category) {
            case "smileys-emotion" -> "😀";
            case "people-body" -> "👨";
            case "animals-nature" -> "🐶";
            case "food-drink" -> "🍔";
            case "travel-places" -> "🚗";
            case "activities" -> "🎮";
            case "objects" -> "📦";
            case "symbols" -> "❤️";
            case "flags" -> "🏳️";
            default -> "❓";
        };
    }
}
