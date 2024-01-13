import api.client.Client;
import api.client.Order;
import constants.BaseClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetOrdersWithLoginTest {

    Client client = new Client();
    Order order = new Order();
    String token;

    @Before
    public void beforeTest() {
        BaseClass.setUp();
        client.createClient();
        token = Client.loginAndExtractAccessTokenOfClient();
        order.createOrderWithLogin(token);
    }

    @Test
    public void testGetOrders() {
        order.getOrderForClientWithOrders(token);
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(token);
    }
}
