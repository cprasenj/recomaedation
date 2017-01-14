package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.domain.Beacon;
import recomendation.service.BeaconService;
import recomendation.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/beacon")
public class BeaconController {

    @Autowired
    private BeaconService beaconService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public Beacon findById(@PathVariable("id") String id) {
        return beaconService.findById(id);
    }

    @RequestMapping("findByLocation/{location}")
    public Beacon findByLocation(@PathVariable("location") String location) {
        return beaconService.findByLocation(location);
    }

    @RequestMapping("findByCategory/{categoryName}")
    public recomendation.contract.Beacon findByCategory(@PathVariable("categoryName") String categoryName) {
        Beacon byCategory = beaconService.findByCategory(categoryService.findByName(categoryName));
        return new recomendation.contract.Beacon(byCategory.getLocation(), byCategory.getCategory().getName(), byCategory.getId());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBeacon(@RequestBody recomendation.contract.Beacon beacon) {
        Beacon newBeacon = new Beacon(beacon.getLocation());
        if (beacon.getCategory() != null) {
           newBeacon.setCategory(categoryService.findByName(beacon.getCategory()));
        }
        beaconService.save(newBeacon);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}