package recomendation.service;

import org.springframework.stereotype.Service;
import recomendation.contract.CartUpdateRequest;
import recomendation.domain.Cart;
import recomendation.repository.BeaconRepository;
import recomendation.repository.CartRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;
    private BeaconRepository beaconRepository;

    public CartService(CartRepository cartRepository, BeaconRepository beaconRepository) {
        this.cartRepository = cartRepository;
        this.beaconRepository = beaconRepository;
    }

    public Cart findById(String id) {
        return cartRepository.findById(id);
    }

    public Cart findByGcmId(String gcmId) {
        return cartRepository.findByGcmId(gcmId);
    }

    public Cart findByLocation(String LocationId) {
        return cartRepository.findByLocationId(LocationId);
    }

    public List<Cart> findByProductId(String productId) {
        return cartRepository.findByProductIds(Collections.singletonList(productId));
    }

    public Cart save(String gcmId) {
        return cartRepository.save(Cart.builder().gcmId(gcmId).build());
    }


    public Cart updateCart(CartUpdateRequest updateRequest) {
        Cart cart = cartRepository.findById(updateRequest.getCartId());
        List<String> productIdsCurrently = cart.getProductIds();

        List<String> productIdsToAdd = updateRequest.getProductIdsToAdd();
        if (productIdsToAdd != null) {
            productIdsCurrently.addAll(productIdsToAdd);
        }

        List<String> productIdsToRemove = updateRequest.getProductIdsToRemove();
        if (productIdsToRemove != null) {
            productIdsCurrently.removeAll(productIdsToRemove);
        }
        String newLocationId = beaconRepository.findByBeaconId(updateRequest.getBeaconId()).getLocationId();

        cart.setLocationId(newLocationId);
        cart.setProductIds(productIdsCurrently);

        return cartRepository.save(cart);
    }
}
