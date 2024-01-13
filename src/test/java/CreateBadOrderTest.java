import api.client.Order;
import constants.BaseClass;
import org.junit.Before;
import org.junit.Test;

public class CreateBadOrderTest {

    Order order = new Order();

    @Before
    public void beforeTest() {
        BaseClass.setUp();
    }

    @Test
    public void testCreateOrder() {
        order.createBadOrder();
    }
}
