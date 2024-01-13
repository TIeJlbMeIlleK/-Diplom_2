import api.client.Client;
import constants.BaseClass;
import org.junit.Before;
import org.junit.Test;

public class ChangingDataForUnknowClientTest {

    Client client = new Client();

    @Before
    public void beforeTest() {
        BaseClass.setUp();
    }

    @Test
    public void testChangeData() {
        client.changeCreditsOfUnknowClient();
    }

}
