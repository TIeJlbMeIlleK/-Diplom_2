import api.client.Order;
import constants.BaseClass;
import org.junit.Before;
import org.junit.Test;

public class GetOrdersWithoutLoginTest {

    Order order = new Order();

    @Before
    public void beforeTest() {
        BaseClass.setUp();
    }

    @Test
    public void testGetOrders() {
        order.getOrdersForNullClient();
    }
}
