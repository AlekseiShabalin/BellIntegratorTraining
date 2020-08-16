import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class Steps {

    @Step
    @Description(value = "Проверка совпадения аваторов у пользователей")
    public static void checkAvatarUsers(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .body("data.avatar", hasItems("https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg"));
    }

    @Step
    @Description(value = "Проверка совпадения аваторов у пользователей с использованием спецификации")
    public static void checkAvatarWithSpec(RequestSpecification reqSpec, ResponseSpecification resSpec){
        given()
                .spec(reqSpec)
                .when()
                .get("/users?page=2")
                .then()
                .log().all()
                .body("data.avatar", hasItems("https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg"))
                .spec(resSpec);
    }

    @Step
    @Description(value = "Успешная регистрация пользователя в системе")
    public static void successfulUserRegistration(){
        Map<String, String> dataReq = new HashMap<>();
        dataReq.put("email", "eve.holt@reqres.in");
        dataReq.put("password", "pistol");

        Map<String, String> dataRes = new HashMap<>();
        dataRes.put("id", "4");
        dataRes.put("token", "QpwL5tke4Pnpja7X4");

        Response response = given()
                .contentType("application/json")
                .body(dataReq)
                .when()
                .post("/register")
                .then()
                .log().all()
                .extract().response();

        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("id").toString(), dataRes.get("id"), "ID is not found");
        Assert.assertEquals(jsonResponse.get("token").toString(), dataRes.get("token"), "Token is not found");
    }

    @Step
    @Description(value = "Регистрация пользователя в системе без ввода пароля")
    public static void registrationUserWithoutPassword(){
        Map<String, String> dataReq = new HashMap<>();
        dataReq.put("email", "sydney@fife");

        Response response = given()
                .contentType("application/json")
                .body(dataReq)
                .when()
                .post("/register")
                .then()
                .log().all()
                .statusCode(400)
                .extract().response();

        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("error").toString(), "Missing password");
    }

    @Step
    @Description(value = "Проверка сортировки по годам")
    public static void checkSortByYear(){
        Response response = given()
                .when()
                .get("/unknown")
                .then()
                .log().all()
                .extract().response();
        List<Integer> jsonResponse = response.jsonPath().getList("data.year");
        Assert.assertTrue(jsonResponse.stream().sorted().collect(Collectors.toList()).equals(jsonResponse), "Sorting by year is not correct");
    }

}
