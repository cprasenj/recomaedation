package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Aisle;
import recomendation.domain.Beacon;
import recomendation.domain.Category;

public interface AisleRepository extends MongoRepository<Beacon, String> {
    Aisle findById(String id);

    Aisle findByCategory(Category category);
}
