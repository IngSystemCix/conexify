package pe.edu.utp.conexify.util;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Long id;
    private String username;
    private LocalDateTime date;
    private String text;
    private Long likes;
    private List<Comment> comments;
    private boolean likedCommentsByUser;
    private boolean replyByUser;
    private String textReply;
    private boolean editingByUser;
}
