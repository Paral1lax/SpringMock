package com.example.SpringMock.controller;
import com.example.SpringMock.model.RequestDTO;
import com.example.SpringMock.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Random;

@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();
    Random random = new Random();
    @PostMapping(
            value = "/info/postBalances",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){
        try{
            String clientUID = requestDTO.getClientId();
            char firstChar = clientUID.charAt(0);
            int maxLimit = 10000;
            String currency = "RUB";
            if (firstChar == '8') {
                maxLimit = 2000;
                currency = "US";
            }
            else if (firstChar == '9') {
                maxLimit = 1000;
                currency = "EU";
            }
            float balance = random.nextInt(maxLimit * 100) / 100f;
            System.out.println(balance);
            ResponseDTO responseDTO = new ResponseDTO(requestDTO.getRqUID(), requestDTO.getClientId(),
                    requestDTO.getAccount(), currency, balance, BigDecimal.valueOf(maxLimit));

            log.error("********** RequestDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("********** ResponseDTO *********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;
        } catch (Exception ex){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());}
    }
}
