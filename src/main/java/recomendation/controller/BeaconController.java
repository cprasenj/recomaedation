package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.contract.Beacon;
import recomendation.service.BeaconService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/beacon")
public class BeaconController {

    @Autowired
    private BeaconService beaconService;

    @RequestMapping("/{id}")
    public Beacon findById(@PathVariable("id") String id) {
        return beaconService.findById(id).toContract();
    }

    @RequestMapping("location/{locationId}")
    public List<Beacon> findByLocation(@PathVariable("locationId") String locationId) {
        return beaconService.findByLocation(locationId).stream()
                .map(recomendation.domain.Beacon::toContract)
                .collect(toList());
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBeacon(@RequestBody Beacon beacon) {
        beaconService.save(beacon.toDomain());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}