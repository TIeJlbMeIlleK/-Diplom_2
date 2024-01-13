import api.client.Client;
import constants.BaseClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientLoginTest {
    Client client = new Client();

    @Before
    public void beforeTest() {
        BaseClass.setUp();
        client.createClient();
    }

    @Test
    public void testLoginClient() {
        client.loginClient();
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(Client.loginAndExtractAccessTokenOfClient());
    }
}
