package recomendation.service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.springframework.stereotype.Service;
import recomendation.contract.GcmMessageRequest;
import recomendation.domain.Cart;

import java.io.IOException;

@Service
public class GcmMessageService {
    private AisleService aisleService;
    private CartService cartService;


    public GcmMessageService(AisleService aisleService, CartService cartService) {
        this.aisleService = aisleService;
        this.cartService = cartService;
    }


    final String GCM_API_KEY = "AIzaSyDUV44SZxuwW0ua69cXzCmfZOSaVn1MHuE";
    final int retries = 3;

    public void sendMessage(GcmMessageRequest gcmMessageRequest) {
        Sender sender = new Sender(GCM_API_KEY);
        String locationId = aisleService.findById(gcmMessageRequest.getAisleId()).getLocationId();
        Cart cart = cartService.findByLocation(locationId);
        Message msg = new Message.Builder().addData("message", "some Message").build();
        try {
            Result result = sender.send(msg, cart.getGcmId(), retries);
        } catch (IOException e) {

        }
    }
}
