import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AddCourierTest {

    @Before
    public void SetUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Создание курьера получение кода 201")
    public void addNewCourierStatusCode201(){
        NewCourier courier = new NewCourier("robotec_"+ new Random().nextInt(50),"4321","Vasily");
        Response response =
        given()
                .header("Content-type","application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .statusCode(201);
    }


    @Test
    public void addNewCourierResponseTrue(){
        NewCourier courier = new NewCourier("zobot_"+ new Random().nextInt(50),"4321","Vasily");
        Response response =
        given()
                .header("Content-type","application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    public void addNewCourierStatusCode409(){
        NewCourier courier = new NewCourier("robot1", "4321","Filya");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .statusCode(409);
    }

    @Test
    public void addNewCourierStatusCodeResponse(){
        NewCourier courier = new NewCourier("robot1", "4321","Cool");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void addNewCourierNoLoginStatusCode400(){
        NewCourier courier = new NewCourier("", "4321", "Vova");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .statusCode(400);
    }

    @Test
    public void addNewCourierNoPasswordStatusCode400(){
        NewCourier courier = new NewCourier("robot16", "", "Coca");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .statusCode(400);
    }

    @Test
    public void addNewCourierNoPasswordAndLoginResponse(){
        NewCourier courier = new NewCourier("", "", "Pepsi");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    public void addNewCourierNoFirstNameStatusCode201(){
        NewCourier courier = new NewCourier("robo_"+ new Random().nextInt(50), "4321", "");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .statusCode(201);
    }
}