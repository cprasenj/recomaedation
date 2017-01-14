package recomendation.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateRequest {

    private String beaconId;

    private String cartId;

    private List<String> productIdsToAdd;

    private List<String> productIdsToRemove;

}
