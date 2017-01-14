package recomendation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Product {
    @Id
    private String id;

    private ProductDetail productDetail;

    private String location;

    @DBRef
    private Aisle aisles;

    @DBRef
    private List<Category> categories;

}
