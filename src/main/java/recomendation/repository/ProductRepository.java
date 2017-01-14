package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findById(String id);
    Product findByAisle(String aisle);
    Product findByLocation(String location);
    Product findByCategory(String categoryId);

}
