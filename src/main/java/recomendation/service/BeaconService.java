package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.domain.Beacon;
import recomendation.repository.BeaconRepository;

import java.util.List;

@Service
public class BeaconService {

    private BeaconRepository beaconRepository;
    private AisleService aisleService;

    public BeaconService(BeaconRepository beaconRepository, AisleService aisleService) {
        this.beaconRepository = beaconRepository;
        this.aisleService = aisleService;
    }

    public Beacon findById(String id) {
        return beaconRepository.findByBeaconId(id);
    }

    public List<Beacon> findByLocation(String locationId) {
        return beaconRepository.findByLocationId(locationId);
    }

    public Beacon save(Beacon beacon) {
        return beaconRepository.save(beacon);
    }

}
