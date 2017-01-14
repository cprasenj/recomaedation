package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.domain.Aisle;
import recomendation.domain.Category;
import recomendation.service.AisleService;
import recomendation.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AisleService aisleService;

    @RequestMapping("/{id}")
    public Category findById(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }

    @RequestMapping("findByName/{name}")
    public Category findByName(@PathVariable("name") String name) {
        return categoryService.findByName(name);
    }

    @RequestMapping("findByAisle/{aisle}")
    public recomendation.contract.Category findByCategory(@PathVariable("aisle") String aisle) {
        Aisle aisleById = aisleService.findById(aisle);
        Category categoryByAisle = categoryService.findByAisle(aisleById);
        return new recomendation.contract.Category(categoryByAisle.getName(), categoryByAisle.getId());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBeacon(@RequestBody recomendation.contract.Category category) {
        categoryService.save(new Category((category.getName())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
