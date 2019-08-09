package com.tim.app;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ElectionSteps {

    private String xml;
    private int statusCode;
    private String responseString;

    @Given("our POST \\/newresult endpoint is hit with valid XML data and the database is empty")
    public void ourPOSTNewpostEndpointIsHitWithValidXMLData() throws IOException {
        //clear the db
        URL clearUrl = new URL("http://localhost:8080/cleardb/password123");
        HttpURLConnection clearConnection = (HttpURLConnection) clearUrl.openConnection();
        clearConnection.setRequestMethod("GET");
        clearConnection.connect();
        assertEquals(200, clearConnection.getResponseCode());

        this.xml = "<constituencyResults><constituencyResult seqNo=\"1\"><consituencyId>2</consituencyId><constituencyName>Aberconwy</constituencyName><results><result><partyCode>LAB </partyCode><votes>8994</votes><share>33.00</share></result><result><partyCode>CON </partyCode><votes>7924</votes><share>29.10</share></result><result><partyCode>LD  </partyCode><votes>5197</votes><share>19.10</share></result><result><partyCode>PC  </partyCode><votes>3818</votes><share>14.00</share></result><result><partyCode>OTH</partyCode><votes>517</votes><share>1.90</share></result><result><partyCode>GRN </partyCode><votes>512</votes><share>1.90</share></result><result><partyCode>UKIP</partyCode><votes>296</votes><share>1.10</share></result></results></constituencyResult></constituencyResults>";
        URL url = new URL("http://localhost:8080/newresult");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
        connection.connect();

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(xml);
        wr.flush();
        wr.close();

        assertEquals(200, connection.getResponseCode());
    }


    @When("we hit our GET \\/labresults endpoint")
    public void weHitOurGETLabresultsEndpoint() throws IOException {
        URL url = new URL("http://localhost:8080/labresults");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        statusCode = connection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while((inputLine = bufferedReader.readLine()) != null){
            response.append(inputLine);
        }
        bufferedReader.close();
        responseString = response.toString();
    }

    @Then("we should get some information")
    public void weShouldGetSomeInformation() {
        assertEquals(200, statusCode);

        //check our response string
        assertEquals("[{\"id\":2,\"constituencyName\":\"Aberconwy\",\"votes\":8994,\"share\":33.0}]", responseString);
    }
}
