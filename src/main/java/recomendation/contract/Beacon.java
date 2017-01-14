package recomendation.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Beacon {
    @NotEmpty
    private String location;
    private String category;
    private String id;

}
