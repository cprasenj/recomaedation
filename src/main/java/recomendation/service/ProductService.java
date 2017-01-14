package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.repository.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
