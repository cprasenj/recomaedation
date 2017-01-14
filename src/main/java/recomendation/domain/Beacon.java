package recomendation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Beacon {
    @Id
    private String id;

    @NotNull
    private String location;

    @DBRef
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Beacon () {};

    public Beacon(String location) {
        this.location = location;
    }

    public Beacon(String location, Category category) {

        this.location = location;
        this.category = category;
    }
}
