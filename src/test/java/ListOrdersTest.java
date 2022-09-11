
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ListOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void viewListOrdersStatusCode200() {
        ListOrders listOrders = new ListOrders(0, "2", 10, 0);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(listOrders)
                .when()
                .get("/api/v1/orders");
        response.then()
                .statusCode(200);
        System.out.println(response.body().asString());
    }



}
