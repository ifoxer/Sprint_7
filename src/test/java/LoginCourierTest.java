import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void loginCourierStatusCode200(){
        Courier courier = new Courier("robot3", "4321");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then().statusCode(200);
    }

    @Test
    public void loginCourierNotNullValue(){
        Courier courier = new Courier("robot4", "1234");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    public void loginCourierNoLoginStatusCode400() {
        Courier courier = new Courier("", "1234");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .statusCode(400);
    }

    @Test
    public void loginCourierNoPasswordStatusCode400() {
        Courier courier = new Courier("robot4", "");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .statusCode(400);
    }

    @Test
    public void loginCourierNoLoginAndPasswordResponse() {
        Courier courier = new Courier("", "");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void loginCourierIncorrectLoginStatusCode404() {
        Courier courier = new Courier("rob", "1234");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .statusCode(404);
    }

    @Test
    public void loginCourierIncorrectPasswordStatusCode404() {
        Courier courier = new Courier("robot4", "123456");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .statusCode(404);
    }

    @Test
    public void loginCourierIncorrectLoginAndPasswordResponse() {
        Courier courier = new Courier("rob", "123456");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("api/v1/courier/login");
        response.then()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
