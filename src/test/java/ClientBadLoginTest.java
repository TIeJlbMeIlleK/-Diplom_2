import api.client.Client;
import constants.BaseClass;
import org.junit.Before;
import org.junit.Test;

public class ClientBadLoginTest {
    Client client =  new Client();

    @Before
    public void beforeTest() {
        BaseClass.setUp();
    }

    @Test
    public void testLoginClient() {
        client.badLoginClient();
    }
}
