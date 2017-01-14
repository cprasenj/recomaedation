package recomendation.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Category {
    private String name;
    @NotEmpty
    private String id;
}
