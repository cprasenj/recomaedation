package recomendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recomendation.contract.GcmMessageRequest;
import recomendation.service.GcmMessageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/gcm-Message")
public class GcmController {

    @Autowired
    private GcmMessageService messageService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void sendGcmMessage(@RequestBody GcmMessageRequest gcmMessageRequest) {
        messageService.sendMessage(gcmMessageRequest);
    }

    @RequestMapping("/aisleId/{aisleId}/productId/{productId}")
    public void sendMessageViaGetRequest(@PathVariable("aisleId") String aisleId, @PathVariable("productId") String productId) {
        GcmMessageRequest request = GcmMessageRequest.builder()
                .aisleId(aisleId)
                .productId(productId)
                .build();
        messageService.sendMessage(request);
    }

    @ExceptionHandler(Exception.class)
    public void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
