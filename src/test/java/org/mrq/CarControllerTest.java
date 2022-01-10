package org.mrq;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mrq.web.controller.CarController;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class CarControllerTest {

    @Test
    public void testGetAllCarEndpoint() {
        given()
                .when().get("/api/v1/cars")
                .then()
                .statusCode(200);
    }
}
