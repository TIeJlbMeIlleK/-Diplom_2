import api.client.Client;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientLoginTest {
    Client client =  new Client();

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        client.createClient();
    }

    @Test
    public void testLoginClient(){
        client.loginClient();
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(Client.loginAndExtractAccessTokenOfClient());
    }
}
