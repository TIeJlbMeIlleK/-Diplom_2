import api.client.Order;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderWithoutIngridientsTest {

    Order order = new Order();

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void testCreateOrder() {
        order.createEmptyOrder();
    }
}
