import api.client.Client;
import constants.BaseClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangingDataForClientTest {

    Client client = new Client();
    private String token;

    @Before
    public void beforeTest() {
        BaseClass.setUp();
        client.createClient();
    }

    @Test
    public void changeNameAndEmailOfClient() {
        token = Client.loginAndExtractAccessTokenOfClient();
        client.changeNameAndEmailOfClient(token);
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(token);
    }
}
