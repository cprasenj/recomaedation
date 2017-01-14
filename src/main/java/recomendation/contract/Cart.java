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
public class Cart {
    @NotEmpty
    private String id;

    @NotEmpty
    private String gcmId;

    private List<String> productIds;

    private String locationId;

    private String aisleId;
}
