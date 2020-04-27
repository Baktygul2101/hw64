package kg.attractor.votemachine.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    @Getter
    @Setter
    public String id;
    @Getter
    @Setter
    public String title;
    @Getter
    @Setter
    public String description;
    @Getter
    @Setter
    public String due;
}