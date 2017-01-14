package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.domain.Category;
import recomendation.domain.Product;
import recomendation.repository.ProductRepository;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private AisleService aisleService;

    public ProductService(ProductRepository productRepository, AisleService aisleService) {
        this.productRepository = productRepository;
        this.aisleService = aisleService;
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByAisle(String aisleId) {
        return aisleService.findById(aisleId)
                .getCategories()
                .stream()
                .map(Category::getId)
                .map(categoryId -> productRepository.findByCategories(singletonList(categoryId)))
                .flatMap(Collection::stream)
                .collect(toList());
    }

    public List<Product> findByCategory(String categoryId) {
        return productRepository.findByCategories(singletonList(categoryId));
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
