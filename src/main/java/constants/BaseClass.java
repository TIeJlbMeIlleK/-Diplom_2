package constants;

import io.restassured.RestAssured;

public class BaseClass {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    public static void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
}
