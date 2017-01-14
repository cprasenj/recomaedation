package recomendation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Document
public class Category {
    @Id
    private String id;

    @NotNull
    private String name;

    @DBRef
    private List<Aisle> aisles;

    public void setAisle(List<Aisle> aisles) {
        this.aisles = aisles;
    }

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}
