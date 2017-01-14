package recomendation.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beacon {
    @NotEmpty
    private String beaconId;

    private String locationId;

    public recomendation.domain.Beacon toDomain() {
        return recomendation.domain.Beacon.builder()
                .beaconId(beaconId)
                .locationId(locationId)
                .build();
    }
}
