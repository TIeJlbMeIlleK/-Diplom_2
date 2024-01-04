import api.client.Client;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangingDataForClientTest {

    Client client =  new Client();
    private String token;

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        client.createClient();
    }

    @Test
    public void testChangeData(){
        token = Client.loginAndExtractAccessTokenOfClient();
        client.changeCreditsOfClient(token);
    }

    @After
    public void deleteCourier() {
        Client.deleteClient(token);
    }
}
