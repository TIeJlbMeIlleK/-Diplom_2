package api.client;

import constants.Api;
import constants.ContentType;
import io.qameta.allure.Step;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Order {

    private static final File order = new File("src/test/resources/order.json");
    private static final File badOrder = new File("src/test/resources/badOrder.json");
    private static final File emptyOrder = new File("src/test/resources/emptyOrder.json");

    @Step("Создание заказа по авторизованному клиенту, с проверкой name по клиенту")
    public void createOrderWithLoginAndClientName(String accessToken, String clientName) {
        given()
                .header("Authorization", accessToken)
                .contentType(ContentType.CONTENT_TYPE)
                .body(order)
                .when()
                .post(Api.ORDER_API)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("name", equalTo("Экзо-плантаго space бессмертный флюоресцентный бургер"))
                .body("order.number", notNullValue())
                .body("order.owner.name", equalTo(clientName));
    }

    @Step("Создание заказа по авторизованному клиенту")
    public void createOrderWithLogin(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType(ContentType.CONTENT_TYPE)
                .body(order)
                .when()
                .post(Api.ORDER_API)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("name", equalTo("Экзо-плантаго space бессмертный флюоресцентный бургер"));
    }

    @Step("Создание заказа без авторизации")
    public void createOrderWithoutLogin() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(order)
                .when()
                .post(Api.ORDER_API)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("name", equalTo("Экзо-плантаго space бессмертный флюоресцентный бургер"))
                .body("order.number", notNullValue());
    }

    @Step("Создание заказа без ингридиентов")
    public void createEmptyOrder() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(emptyOrder)
                .when()
                .post(Api.ORDER_API)
                .then()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Создание заказа с не валидным идентификатором")
    public void createBadOrder() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(badOrder)
                .when()
                .post(Api.ORDER_API)
                .then()
                .statusCode(500);
    }

    @Step("Получение заказов по авторизованному клиенту, у которого есть заказы")
    public void getOrderForClientWithOrders(String accessToken) {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header("Authorization", accessToken)
                .when()
                .get(Api.ORDER_API)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("orders", notNullValue());
    }

    @Step("Получение заказов по клиенту, который не авторизован")
    public void getOrdersForNullClient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .when()
                .get(Api.ORDER_API)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

}
