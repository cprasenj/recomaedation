package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findById(String id);
    List<Product> findByCategories(List<String> categoryIds);
    Product findByName(String Name);

}
