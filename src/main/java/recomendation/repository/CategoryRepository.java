package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Aisle;
import recomendation.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findById(String id);
    Category findByName(String name);


    Category findByAisle(Aisle aisle);
}
