package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Beacon;

import java.util.List;

public interface BeaconRepository extends MongoRepository<Beacon, String> {
    Beacon findByBeaconId(String id);
    List<Beacon> findByLocationId(String aisleId);

}
