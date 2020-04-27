package kg.attractor.votemachine.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "comments")
@Data
public class Comment {
    @Id
    public String id;
    @Getter
    @Setter
    public String comment;

    @DBRef
    @Getter
    @Setter
    public List<Comment> comments;
}
