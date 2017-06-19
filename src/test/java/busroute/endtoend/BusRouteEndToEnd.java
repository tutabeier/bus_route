package busroute.endtoend;

import busroute.Application;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@TestPropertySource("/test.properties")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class BusRouteEndToEnd {
    @Value("${server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = this.serverPort;
    }

    @Test
    public void shouldReturnAJsonWithTrueIfFindsARoute() {
        int depsId = 6;
        int arrsId = 4;

        given()
            .pathParam("dep_sid", depsId)
            .pathParam("arr_sid", arrsId)
        .when()
            .get("api/direct?dep_sid={dep_sid}&arr_sid={arr_sid}")
        .then()
            .statusCode(200)
            .body("dep_sid", equalTo(depsId))
            .body("arr_sid", equalTo(arrsId))
            .body("direct_bus_route", equalTo(true));
    }

    @Test
    public void shouldReturnAJsonWithFalseIfDoesntFindsARoute() {
        given()
            .pathParam("dep_sid", 0)
            .pathParam("arr_sid", 8)
        .when()
            .get("api/direct?dep_sid={dep_sid}&arr_sid={arr_sid}")
        .then()
            .statusCode(200)
            .body("dep_sid", equalTo(0))
            .body("arr_sid", equalTo(8))
            .body("direct_bus_route", equalTo(false));
    }
}