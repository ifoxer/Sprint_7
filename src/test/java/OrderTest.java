import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String color;

    public OrderTest(String color){
        this.color = color;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {""},
                {"BLACK, GREY"}
        };
    }


    @Test
    public void addNewOrderStatusCode201() {
        Order order = new Order("vasya", "vasilkov", "moscow", 3,
                "926-666-66-77", 4, "23-02-2022", "comment", color);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("api/v1/orders");
        response.then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
        System.out.println(response.body().asString());

    }

}
