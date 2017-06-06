/**
 * Created by Dąbrowscy on 04.06.2017.
 */

public class DoLoginXML {

    String loginXml = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><DoLoginRequest xmlns=\"https://webapi.allegro.pl/service.php\"><userLogin></userLogin><userPassword></userPassword><countryCode>1</countryCode><webapiKey></webapiKey><localVersion>doZmiany</localVersion></DoLoginRequest></s:Body></s:Envelope>";

    public DoLoginXML(String versionKey) {
        loginXml = loginXml.replace("doZmiany", versionKey);
    }

}
