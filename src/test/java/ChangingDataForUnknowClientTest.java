import api.client.Client;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangingDataForUnknowClientTest {

    Client client =  new Client();

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void testChangeData(){
        client.changeCreditsOfUnknowClient();
    }

}
