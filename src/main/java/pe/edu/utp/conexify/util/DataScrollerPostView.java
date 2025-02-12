package pe.edu.utp.conexify.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.text.RuleBasedNumberFormat;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;
import pe.edu.utp.conexify.adapters.LocalDateTimeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@Getter
@Setter
@Named
@ViewScoped
public class DataScrollerPostView implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(DataScrollerPostView.class.getName());
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    private List<Post> posts;
    private String currentUser;
    private Post selectedPost;

    @PostConstruct
    public void init() {

        currentUser = "Juan Romero";

        posts = new ArrayList<>(List.of(
                Post.builder()
                        .id(1L)
                        .textPost(true)
                        .linkPost(false)
                        .imagePost(false)
                        .videoPost(false)
                        .content("Hello, how are you? I hope you're doing well. Today is a beautiful day, and the sun is shining brightly in the sky. The birds are chirping, and there's a gentle breeze that makes everything feel fresh. Sometimes, taking a deep breath and enjoying the little things in life can bring so much happiness. Have you ever stopped to appreciate how wonderful the world around us is? It's easy to get caught up in daily routines and forget to pause for a moment. Maybe today is a good day to take a short walk and clear your mind. A cup of coffee, a good book, or a nice conversation can make a big difference. Life is full of small joys, and each day is an opportunity to experience something new. What’s something that made you smile today?")
                        .username("Juan Romero")
                        .date(LocalDateTime.parse("2021-09-01T10:00:00"))
                        .isPublic(true)
                        .likes(1000L)
                        .comments(List.of(
                                Comment.builder()
                                        .id(1L)
                                        .username("Maria Perez")
                                        .date(LocalDateTime.parse("2021-09-01T10:05:00"))
                                        .text("I'm fine, thank you!")
                                        .comments(List.of(
                                                Comment.builder()
                                                        .id(4L)
                                                        .username("Juan Romero")
                                                        .date(LocalDateTime.parse("2021-09-01T10:10:00"))
                                                        .text("That's great to hear!")
                                                        .comments(List.of(
                                                                Comment.builder()
                                                                        .id(5L)
                                                                        .username("Maria Perez")
                                                                        .date(LocalDateTime.parse("2021-09-01T10:15:00"))
                                                                        .text("Yes, it is!")
                                                                        .likes(10L)
                                                                        .build()
                                                        ))
                                                        .likes(10L)
                                                        .build()
                                        ))
                                        .likes(100L)
                                        .build(),
                                Comment.builder()
                                        .id(2L)
                                        .username("Carlos Sanchez")
                                        .date(LocalDateTime.parse("2021-09-01T10:10:00"))
                                        .text("I'm fine too!")
                                        .likes(50L)
                                        .build()
                        ))
                        .build(),
                Post.builder()
                        .id(2L)
                        .textPost(false)
                        .linkPost(true)
                        .imagePost(false)
                        .videoPost(false)
                        .content("https://www.youtube.com/watch?v=SdxThJQc3kU&t=10s")
                        .username("Maria Perez")
                        .date(LocalDateTime.parse("2021-09-01T10:15:00"))
                        .isPublic(true)
                        .likes(500L)
                        .comments(List.of(
                                Comment.builder()
                                        .id(3L)
                                        .username("Juan Romero")
                                        .date(LocalDateTime.parse("2021-09-01T10:20:00"))
                                        .text("Nice video!")
                                        .likes(50L)
                                        .build()
                        ))
                        .build(),
                Post.builder()
                        .id(3L)
                        .textPost(true)
                        .linkPost(false)
                        .imagePost(false)
                        .videoPost(false)
                        .content("Good morning!")
                        .username("Carlos Sanchez")
                        .date(LocalDateTime.parse("2021-09-01T10:25:00"))
                        .isPublic(true)
                        .likes(200L)
                        .comments(List.of())
                        .build())
        );
    }

    public String converterAmountLikes(Long likes) {
        double amountLikes = likes.doubleValue();

        if (amountLikes >= 1_000_000) {
            return String.format("%.1fM " + Bundle.getAttributeI18N("label_like"), amountLikes / 1_000_000);
        } else if (amountLikes >= 100_000) {
            return String.format("%.1fk " + Bundle.getAttributeI18N("label_like"), amountLikes / 1_000);
        } else if (amountLikes >= 10_000) {
            return String.format("%.1fk " + Bundle.getAttributeI18N("label_like"), amountLikes / 1_000);
        } else if (amountLikes >= 1_000) {
            return String.format("%.1fk " + Bundle.getAttributeI18N("label_like"), amountLikes / 1_000);
        } else {
            return likes + " " +  Bundle.getAttributeI18N("label_like");
        }
    }

    public String converterAmountComments(Long comments) {
        int amountComments = comments.intValue();

        if (amountComments >= 1_000_000) {
            return String.format("%.1fM " + Bundle.getAttributeI18N("label_comment"), amountComments / 1_000_000.0);
        } else if (amountComments >= 100_000) {
            return String.format("%.1fk " + Bundle.getAttributeI18N("label_comment"), amountComments / 1_000.0);
        } else if (amountComments >= 10_000) {
            return String.format("%.1fk " + Bundle.getAttributeI18N("label_comment"), amountComments / 1_000.0);
        } else if (amountComments >= 1_000) {
            return String.format("%.1fk " + Bundle.getAttributeI18N("label_comment"), amountComments / 1_000.0);
        } else {
            return comments + " " + Bundle.getAttributeI18N("label_comment");
        }
    }

    public void toggleLike(Post post) {
        LOGGER.info("Toggling like for post ID: " + post.getId());
        if (post.isLikedByUser()) {
            post.setLikes(post.getLikes() - 1);
            post.setLikedByUser(false);
            LOGGER.info("Post unliked. New likes count: " + post.getLikes());
        } else {
            post.setLikes(post.getLikes() + 1);
            post.setLikedByUser(true);
            LOGGER.info("Post liked. New likes count: " + post.getLikes());
        }
    }

    public void deletePost() {
        if (selectedPost != null) {
            posts.remove(selectedPost);
            selectedPost = null; // Limpiar después de eliminar
            LOGGER.info("Post deleted.");
        }
    }

    public String convertNumberToWords(int number) {
        LOGGER.info("Converting number to words...");
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(Locale.forLanguageTag(Bundle.getAttributeI18N("language")), RuleBasedNumberFormat.SPELLOUT);
        return ruleBasedNumberFormat.format(number);
    }

    public void showComments(Post post) {
        if (post != null) {
            LOGGER.info("Showing comments for post ID: " + post.getId());

            Map<String, List<String>> params = new HashMap<>();
            params.put("postId", Collections.singletonList(post.getId().toString()));
            params.put("comments", Collections.singletonList(gson.toJson(post.getComments())));

            DialogFrameworkOptions options = DialogFrameworkOptions.builder()
                    .modal(true)
                    .resizable(false)
                    .width("90%")
                    .height("60%")
                    .contentHeight("100%")
                    .contentWidth("100%")
                    .closable(true)
                    .draggable(false)
                    .responsive(true)
                    .closeOnEscape(true)
                    .build();

            PrimeFaces.current().dialog().openDynamic("/comments-post", options, params);
        }
    }

    public int countTotalComments(List<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            return 0;
        }

        return comments.stream()
                .mapToInt(comment -> 1 + countTotalComments(comment.getComments()))
                .sum();
    }

}
