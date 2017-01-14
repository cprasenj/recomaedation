package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.domain.Aisle;
import recomendation.domain.Category;
import recomendation.repository.AisleRepository;

import java.util.List;

@Service
public class AisleService {

    private AisleRepository aisleRepository;

    public AisleService(AisleRepository aisleRepository) {
        this.aisleRepository = aisleRepository;
    }

    public Aisle findById(String id) {
        return aisleRepository.findById(id);
    }

    public List<Aisle> findByCategory(List<Category> categories) {
        return aisleRepository.findByCategoriesIn(categories);
    }

    public Aisle findByLocation(String locationId) {
        return aisleRepository.findByLocationId(locationId);
    }

    public void save(Aisle aisle) {
         aisleRepository.save(aisle);
    }
}
