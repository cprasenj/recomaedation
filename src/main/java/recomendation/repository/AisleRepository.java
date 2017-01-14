package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Aisle;
import recomendation.domain.Category;

import java.util.List;

public interface AisleRepository extends MongoRepository<Aisle, String> {
    Aisle findById(String id);

    List<Aisle> findByCategoriesIn(List<Category> categories);

    Aisle findByLocationId(String locationId);

}
