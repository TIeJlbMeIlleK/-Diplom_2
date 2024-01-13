import api.client.Client;
import constants.BaseClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateDoublicateClientTest {


    Client client = new Client();

    @Before
    public void beforeTest() {
        BaseClass.setUp();
    }

    @Test
    public void testCreateClient() {
        client.createClient();
        client.createDuplicateClient();
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(Client.loginAndExtractAccessTokenOfClient());
    }
}
