package recomendation.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private String name;
    @NotEmpty
    private String id;
    private List<String> aisleIds;
}
