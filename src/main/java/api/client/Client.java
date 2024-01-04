package api.client;

import constants.Api;
import constants.ContentType;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Client {
    private static final File client = new File("src/test/resources/client.json");
    private static final File badClient = new File("src/test/resources/badClient.json");

    private static final File loginClient = new File("src/test/resources/clientLogin.json");
    private static final File badClientLogin = new File("src/test/resources/badClientLogin.json");
    private static final File newCredits = new File("src/test/resources/newCredits.json");


    @Step("Создание клиента")
    public void createClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(client)
                .when()
                .post(Api.CLIENT_API_REGISTRATION)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Создание дубликата клиента")
    public void createDuplicateClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(client)
                .when()
                .post(Api.CLIENT_API_REGISTRATION)
                .then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    @Step("Создание клиента с невалидными кредами")
    public void createBadClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(badClient)
                .when()
                .post(Api.CLIENT_API_REGISTRATION)
                .then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Авторизация клиента")
    public void loginClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginClient)
                .when()
                .post(Api.CLIENT_LOGIN_API_ENDPOINT)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }


    @Step("Авторизация невалидного курьера")
    public void badLoginClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(badClientLogin)
                .when()
                .post(Api.CLIENT_LOGIN_API_ENDPOINT)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Step("Изменение email и name по клиенту")
    public void changeCreditsOfClient(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType(ContentType.CONTENT_TYPE)
                .body(newCredits)
                .when()
                .patch(Api.FOR_ACTIONS_ON_THE_CLIENT)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("v_evgrafov_iitdgroup@yandex.ru"))
                .body("user.name", equalTo("v_evgrafov"));
    }

    @Step("Изменение email и name по клиенту, без авторизации")
    public void changeCreditsOfUnknowClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(newCredits)
                .when()
                .patch(Api.FOR_ACTIONS_ON_THE_CLIENT)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }


    @Step("Получить accessToken по клиенту")
    public static String loginAndExtractAccessTokenOfClient() {
        Response response = given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginClient)
                .when()
                .post(Api.CLIENT_LOGIN_API_ENDPOINT)
                .then()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .extract()
                .response();

        return response.path("accessToken");
    }

    @Step("Получить name по клиенту")
    public static String loginAndExtractNameOfClient() {
        Response response = given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginClient)
                .when()
                .post(Api.CLIENT_LOGIN_API_ENDPOINT)
                .then()
                .statusCode(200)
                .body("user.name", notNullValue())
                .extract()
                .response();

        return response.path("user.name");
    }

    @Step("Получить refreshToken по клиенту")
    public static int loginAndExtractRefreshTokenOfClient() {
        Response response = given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginClient)
                .when()
                .post(Api.CLIENT_LOGIN_API_ENDPOINT)
                .then()
                .statusCode(200)
                .body("refreshToken", notNullValue())
                .extract()
                .response();

        return response.path("refreshToken");
    }

    @Step("Удалить клиента")
    public static void deleteClient(String  clientToken) {
        given()
                .header("Authorization", clientToken)
                .when()
                .delete(Api.FOR_ACTIONS_ON_THE_CLIENT)
                .then()
                .statusCode(202)
                .body("success", equalTo(true));
    }
}
