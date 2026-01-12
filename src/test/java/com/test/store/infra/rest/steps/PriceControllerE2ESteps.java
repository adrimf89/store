package com.test.store.infra.rest.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

public class PriceControllerE2ESteps {

    @LocalServerPort
    private int port;

    private Response response;

    @Given("the API is running")
    public void theApplicationIsRunning() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @When("I request the effective price for productId {long}, brandId {long} at {string}")
    public void iRequestTheEffectivePriceForProductAt(Long productId, Long brandId, String effectiveDate) {
        response = RestAssured.given()
                .queryParam("effectiveDate", effectiveDate)
                .when()
                .get("/api/prices/{brandId}/products/{productId}", brandId, productId);
    }

    @When("I request the effective price for productId {long}, brandId {long} and null date")
    public void iRequestTheEffectivePriceForProductAt(Long productId, Long brandId) {
        response = RestAssured.given()
                .when()
                .get("/api/prices/{brandId}/products/{productId}", brandId, productId);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the price in the response should be {bigdecimal}")
    public void thePriceInTheResponseShouldBe(BigDecimal expectedPrice) {
        response.then().assertThat().body("price", Matchers.equalTo(expectedPrice.floatValue()));
    }

    @Then("the error response detail should match product {int}, brand {int} and date {string}")
    public void errorDetailMatches(long productId, long brandId, String effectiveDate) {
        String expected = "Price not found for Product ID '%d', Brand ID '%d' and effective date '%s'"
                .formatted(productId, brandId, effectiveDate);
        response.then().body("detail", Matchers.equalTo(expected));
    }

    @Then("the error response detail should match field {string} is missing")
    public void parameterErrorDetailMatches(String missingField) {
        String expected = "Required request parameter '%s'"
                .formatted(missingField);
        response.then().body("detail", Matchers.startsWith(expected));
    }
}
