package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.contract.Category;
import recomendation.domain.Aisle;
import recomendation.service.AisleService;
import recomendation.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AisleService aisleService;

    @RequestMapping("/{id}")
    public Category findById(@PathVariable("id") String id) {
        return categoryService.findById(id).toContract();
    }

    @RequestMapping("/name/{name}")
    public Category findByName(@PathVariable("name") String name) {
        return categoryService.findByName(name).toContract();
    }

    @RequestMapping("/aisle/{aisle}")
    public List<Category> findByCategory(@PathVariable("aisle") String aisle) {
        Aisle aisleById = aisleService.findById(aisle);
        return categoryService.findByAisle(Collections.singletonList(aisleById)).stream()
                .map(recomendation.domain.Category::toContract)
                .collect(toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBeacon(@RequestBody recomendation.contract.Category category) {
        categoryService.save(new recomendation.domain.Category((category.getName())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
