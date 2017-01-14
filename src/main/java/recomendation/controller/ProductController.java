package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.contract.Product;
import recomendation.domain.Aisle;
import recomendation.domain.Category;
import recomendation.service.AisleService;
import recomendation.service.CategoryService;
import recomendation.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AisleService aisleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("{id}")
    public Product findById(@PathVariable("id") String id) {
       return productService.findById(id).toContract();
    }
    @RequestMapping("name/{name}")
    public Product findByName(@PathVariable("name") String name) {
       return productService.findByName(name).toContract();
    }

    @RequestMapping("/aisle/{aisleId}")
    public List<Product> findByAisle(@PathVariable("aisleId") String aisleId) {
        return productService.findByAisle(aisleId).stream()
                .map(recomendation.domain.Product::toContract)
                .collect(toList());
    }

    @RequestMapping("/category/{categoryId}")
    public List<Product> findByCategory(@PathVariable("categoryId") String categoryId) {
        return productService.findByCategory(categoryId).stream()
                .map(recomendation.domain.Product::toContract)
                .collect(toList());

    }

    @RequestMapping("/beacon/{locationId}")
    public List<Product> findByBeacon(@PathVariable("locationId") String locationId) {
        Aisle aisle = aisleService.findByLocation(locationId);
        return productService.findByAisle(aisle.getId()).stream()
                .map(recomendation.domain.Product::toContract)
                .collect(toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productService.save(toDomain(product));
    }

    private recomendation.domain.Product toDomain(Product product) {
        return recomendation.domain.Product.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(getCategories(product.getCategoryIds()))
                .build();
    }

    private List<Category> getCategories(List<String> categoryIds) {
        return categoryIds == null ? emptyList() : categoryIds.stream()
                .map(i -> categoryService.findById(i))
                .collect(toList());
    }

    @ExceptionHandler(Exception.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
