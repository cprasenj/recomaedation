package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.contract.Aisle;
import recomendation.domain.Category;
import recomendation.service.AisleService;
import recomendation.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/aisle")
public class AisleController {

    @Autowired
    private AisleService aisleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public Aisle findById(@PathVariable("id") String id) {
        return aisleService.findById(id).toContract();
    }

    @RequestMapping("category/{id}")
    public Aisle findByCategory(@PathVariable("id") String id) {
        return aisleService.findByCategory(singletonList(categoryService.findById(id))).get(0).toContract();
    }

    @RequestMapping("location/{locationId}")
    public Aisle findByLocation(@PathVariable("locationId") String locationId) {
        return aisleService.findByLocation(locationId).toContract();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAisle(@RequestBody Aisle aisle) {
        aisleService.save(toDomain(aisle));
    }

    private recomendation.domain.Aisle toDomain(Aisle aisle) {
        return recomendation.domain.Aisle.builder()
                .id(aisle.getId())
                .locationId(aisle.getLocationId())
                .categories(getCategories(aisle.getCategoryIds()))
                .build();
    }

    private List<Category> getCategories(List<String> categoryIds) {
        return categoryIds == null ? Collections.emptyList() : categoryIds.stream()
                .map(i -> categoryService.findById(i))
                .collect(toList());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
