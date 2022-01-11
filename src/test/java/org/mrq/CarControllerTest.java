package org.mrq;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
public class CarControllerTest {

    @Test
    public void testGetAllCarEndpointShouldReturnCorrectResponse() {
        given()
                .when().get("/api/v1/cars")
                .then()
                .statusCode(200).body("pageNum", equalTo(0),
                        "pageSize", equalTo(5),
                        "numberOfElements", equalTo(5),
                        "totalElements", equalTo(10),
                        "totalPages", equalTo(2),
                        "data[0].year", equalTo(2022));
    }

    @Test
    public void testGetCarByIdWithExistingIdShouldReturnCorrectResponse() {
        given().when().get("/api/v1/cars/1").then()
                .statusCode(200).body("data.id", equalTo(1),
                        "data.make", equalTo("Audi"),
                        "data.model", equalTo("A3"),
                        "data.year", equalTo(2020));
    }

    @Test
    public void testGetCarByIdWithNonExistentIdShouldThrowError() {
        final int nonExistentRecordId = 99;
        given().when().get("/api/v1/cars/" + nonExistentRecordId).then()
                .statusCode(400).body(
                        "title", equalTo(RuntimeException.class.getName()),
                        "detail", equalTo("Car with id: " + nonExistentRecordId + " not found."));
    }

}
