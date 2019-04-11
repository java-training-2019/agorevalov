package com.luxoft.ak47;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeEventController {

    @RequestMapping(value = "/tradeEvent/{id}", produces = {MediaType.TEXT_XML_VALUE})
    String tradeEvent(@PathVariable String id ){
        String tradeLocationTag;
        if (id.toUpperCase().contains("OBS")){
            tradeLocationTag = "<tradeLocation>HKG</tradeLocation>";
        } else
            tradeLocationTag = "";
        return "<tradeEvent><id>" + id + "</id><version>0</version>" + "<currency>" + getRandomCurrency() + "</currency>" + tradeLocationTag + "</tradeEvent>";
    }

    private static String getRandomCurrency(){
        String currency;
        int a = (int) (Math.random()*3);
        if (a == 0) {
            currency = "EUR";
        } else if (a == 1) {
            currency = "PLN";
        } else currency = "USD";
        return currency;
    }
}
