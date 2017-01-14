package recomendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import recomendation.domain.Cart;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findById(String id);
    Cart findByGcmId(String id);
    List<Cart> findByProductIds(List<String> productIds);
    Cart findByLocationId(String locationId);
}
