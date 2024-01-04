import api.client.Client;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateNewClientTest {

    Client client =  new Client();

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void testCreateClient(){
        client.createClient();
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(Client.loginAndExtractAccessTokenOfClient());
    }
}
