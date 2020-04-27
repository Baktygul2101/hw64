package kg.attractor.votemachine.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
@Data
public class User {
    @Id
    @Getter
    @Setter
    public String id;
    @Getter
    @Setter
    public String name;
    @Getter
    @Setter
    public String email;
    @DBRef
    @Getter
    @Setter
    public List<Task> tasks = new ArrayList<>();
}