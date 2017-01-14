package recomendation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Document
public class Category {
    @Id
    private String id;

    @NotNull
    private String name;

    @DBRef
    private List<Aisle> aisles;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public recomendation.contract.Category toContract() {
        return recomendation.contract.Category.builder()
                .name(name)
                .id(id)
                .aisleIds(getAisleIds())
                .build();
    }

    public List<String> getAisleIds() {
        return aisles.stream()
                .map(Aisle::getId)
                .collect(toList());
    }
}
