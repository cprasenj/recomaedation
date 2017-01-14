package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recomendation.contract.Aisle;
import recomendation.service.AisleService;
import recomendation.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/aisle")
public class AisleController {

    @Autowired
    private AisleService aisleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public Aisle findById(@PathVariable("id") String id) {

        return new Aisle(aisleService.findById(id).getId());
    }

    @RequestMapping("findByCategory/{id}")
    public Aisle findByCategory(@PathVariable("id") String id) {
        return new Aisle(aisleService.findByCategory(categoryService.findById(id)).getId());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
