package recomendation.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Data
@Builder
@Document
public class Product {
    @Id
    private String id;

    private String name;

    @DBRef
    private List<Category> categories;

    public recomendation.contract.Product toContract() {
        return recomendation.contract.Product.builder()
                .name(name)
                .id(id)
                .categoryIds(getCategoryIds())
                .build();
    }

    public List<String> getCategoryIds() {
        if(categories!=null) {
            return categories.stream()
                    .map(Category::getId)
                    .collect(toList());
        }
        return emptyList();
    }

}
