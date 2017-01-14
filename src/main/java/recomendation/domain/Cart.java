package recomendation.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static java.util.Collections.emptyList;

@Data
@Builder
@Document
public class Cart {
    @Id
    private String id;

    private String gcmId;

    private List<String> productIds;

    private String locationId;


    public recomendation.contract.Cart toContract() {
        return recomendation.contract.Cart.builder()
                .id(id)
                .gcmId(gcmId)
                .locationId(locationId)
                .productIds(getProductIdList())
                .build();
    }

    private List<String> getProductIdList() {
        return productIds == null ? emptyList() : productIds;
    }
}
