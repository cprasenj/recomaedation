package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.contract.Cart;
import recomendation.contract.CartUpdateRequest;
import recomendation.service.CartService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/{id}")
    public Cart findById(@PathVariable("id") String id) {
        return cartService.findById(id).toContract();
    }

    @RequestMapping("gcmId/{gcmId}")
    public Cart findByGcmId(@PathVariable("gcmId") String gcmId) {
        return cartService.findByGcmId(gcmId).toContract();
    }

    @RequestMapping("product/{productId}")
    public List<Cart> findByProductId(@PathVariable("productId") String productId) {
        return cartService.findByProductId(productId).stream()
                .map(recomendation.domain.Cart::toContract)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/register/{gcmId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createBeacon(@PathVariable("gcmId") String gcmId) {
        return cartService.save(gcmId).toContract();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createBeacon(@RequestBody CartUpdateRequest updateRequest) {
        return cartService.updateCart(updateRequest).toContract();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
