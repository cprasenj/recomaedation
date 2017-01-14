package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.domain.Aisle;
import recomendation.domain.Category;
import recomendation.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(String id) {
        return categoryRepository.findById(id);

    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);

    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findByAisle(List<Aisle> aisles) {
        return categoryRepository.findByAisles(aisles);
    }
}
