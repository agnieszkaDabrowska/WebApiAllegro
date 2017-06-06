import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

/**
 * Created by Dąbrowscy on 03.06.2017.
 */
public class allegtoDoQueryAllSysStatusRequest {

    String AllegroWebApi = "https://webapi.allegro.pl/service.php";
    String verKey;
    String sessionHandlerPart;

    @Test
    public void PostDoQueryAllSysStatusRequest() {
        String resp;
        resp = given()
                .contentType("application/xml")
                .body(DoQueryAllSysStatusRequestXML.request)
                .when()
                .post(AllegroWebApi)
                .asString();
        int startIdx = resp.indexOf("<ns1:verKey>");
        int endIdx = resp.indexOf("</ns1:verKey>");
        verKey = resp.substring(startIdx + 12, endIdx);

        doLogin();
    }

    public void doLogin() {
        DoLoginXML mojXML = new DoLoginXML(verKey);
        String doLogin =
        given()
                .contentType("application/xml")
                .body(mojXML.loginXml)
                .when()
                .post(AllegroWebApi)
                .asString();
        int startIdx = doLogin.indexOf("<ns1:sessionHandlePart>");
        int endIdx = doLogin.indexOf("</ns1:sessionHandlePart>");

        sessionHandlerPart = doLogin.substring(startIdx + 23, endIdx);
    }

    public void doMyBiling() {

    }


}
