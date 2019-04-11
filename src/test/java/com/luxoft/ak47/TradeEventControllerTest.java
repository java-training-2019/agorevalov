package com.luxoft.ak47;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import static org.assertj.core.api.Assertions.*;


import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.hamcrest.xml.HasXPath.hasXPath;
import static org.junit.jupiter.api.Assertions.*;

class TradeEventControllerTest {


    @Test
    void tradeEvent() {
        RestAssured
                .given()
                .when().get("/tradeEvent/123")
                .then().statusCode(200);
    }

    @Test
    void shouldGet404onNonExistingPage() {
            when().get("/nonExisting").then().statusCode(404);
    }

    @Test
    void shouldReturnTradesWithVersion0() {
        when().get("/tradeEvent/123").then().statusCode(200).body("tradeEvent.version", equalTo("0"));

    }

    @Test
    void shouldCheckTradeId() {
        String id = "sampleId";
        when().get("/tradeEvent/{id}", id)
                .then().statusCode(200)
                .body("tradeEvent.version", equalTo("0"))
                .body("tradeEvent.id", equalTo(id))
                .body(not(hasXPath("tradeEvent/tradeLocation")));

    }

    @Test
    void checkCurrencyIsNotNull() {
        when().get("/tradeEvent/123").then().statusCode(200).body("tradeEvent.currency", not(isEmptyOrNullString()));
    }


  @Test void shouldHaveTradeLocationIfFromOBS() {
      when().get("/tradeEvent/OBS-123").then().body(hasXPath("/tradeEvent/tradeLocation"));
  }

    //проверить чтобы был из ОБС и сделать чтобы приходил нормально
    // трейд должен быть с валютой - test that currency has 3 uppercase characters
    // controller should return random currency from the list


    }


