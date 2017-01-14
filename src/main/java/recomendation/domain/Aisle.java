package recomendation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Aisle {

    @Id
    private String id;

    @DBRef
    private List<Category> categories;

}
