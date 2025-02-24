package pe.edu.utp.conexify.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private String username;
    private LocalDateTime date;
    private String text;
    private Long likes;
    private List<CommentDTO> comments;
    private boolean likedCommentsByUser;
    private boolean replyByUser;
    private String textReply;
    private boolean editingByUser;
}
