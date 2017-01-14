package recomendation.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Beacon {

    @Id
    private String id;

    private String beaconId;
    private String locationId;

    public recomendation.contract.Beacon toContract() {
        return recomendation.contract.Beacon.builder()
                .beaconId(beaconId)
                .locationId(locationId)
                .build();
    }
}
