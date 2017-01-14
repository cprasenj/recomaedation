package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Aisle;
import recomendation.domain.Category;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findById(String id);
    Category findByName(String name);
    List<Category> findByAisles(List<Aisle> aisles);
}
