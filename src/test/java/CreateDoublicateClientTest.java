import api.client.Client;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateDoublicateClientTest {


    Client client =  new Client();

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void testCreateClient(){
        client.createClient();
        client.createDuplicateClient();
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(Client.loginAndExtractAccessTokenOfClient());
    }
}
