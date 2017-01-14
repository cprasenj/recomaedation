package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.domain.Aisle;
import recomendation.domain.Category;
import recomendation.repository.AisleRepository;

@Service
public class AisleService {

    private AisleRepository aisleRepository;

    public AisleService(AisleRepository aisleRepository) {
        this.aisleRepository = aisleRepository;
    }

    public Aisle findById(String id) {
        return aisleRepository.findById(id);
    }

    public Aisle findByCategory(Category category) {
        return aisleRepository.findByCategory(category);
    }
}
