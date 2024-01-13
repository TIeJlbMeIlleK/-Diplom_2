import api.client.Client;
import api.client.Order;
import constants.BaseClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderWithLoginTest {

    Client client = new Client();
    Order order = new Order();
    private String token;
    String clientName;

    @Before
    public void beforeTest() {
        BaseClass.setUp();
        client.createClient();
        token = Client.loginAndExtractAccessTokenOfClient();
        clientName = Client.loginAndExtractNameOfClient();
    }

    @Test
    public void testCreateOrder() {
        order.createOrderWithLoginAndClientName(token, clientName);
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(token);
    }
}
