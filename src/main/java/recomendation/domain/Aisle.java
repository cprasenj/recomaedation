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
public class Aisle {

    @Id
    private String id;

    @DBRef
    private List<Category> categories;

    private String locationId;

    public recomendation.contract.Aisle toContract() {
        return recomendation.contract.Aisle.builder()
                .categoryIds(getCategoryIds())
                .id(id)
                .locationId(locationId)
                .build();
    }

    public List<String> getCategoryIds() {
        if (categories != null) {
            categories.stream()
                    .map(Category::getId)
                    .collect(toList());
        }
        return emptyList();
    }
}
