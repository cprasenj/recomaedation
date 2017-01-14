package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Beacon;
import recomendation.domain.Category;

public interface BeaconRepository extends MongoRepository<Beacon, String> {
    Beacon findById(String id);
    Beacon findByLocation(String location);
    Beacon findByCategory(Category category);

}
