package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.domain.Beacon;
import recomendation.domain.Category;
import recomendation.repository.BeaconRepository;

@Service
public class BeaconService {

    private BeaconRepository beaconRepository;

    public BeaconService(BeaconRepository beaconRepository) {
        this.beaconRepository = beaconRepository;
    }

    public Beacon findById(String id) {
        return beaconRepository.findById(id);
    }

    public Beacon findByLocation(String location) {
        return beaconRepository.findByLocation(location);
    }

    public Beacon save(Beacon beacon) {
        return beaconRepository.save(beacon);
    }

    public Beacon findByCategory(Category category) {
        return beaconRepository.findByCategory(category);
    }
}
