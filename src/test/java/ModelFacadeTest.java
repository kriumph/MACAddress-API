import MacAddress.Database.DatabaseManager;
import MacAddress.Model.ModelFacade;
import MacAddress.Model.ModelFacadeImpl;
import MacAddress.Model.RequestMakerOffline;
import MacAddress.Model.RequestMakerOnline;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ModelFacadeTest {

    @Mock
    private RequestMakerOffline requestMakerOffline;
    private RequestMakerOnline requestMakerOnline;
    private DatabaseManager databaseManager;

    @Before
    public void init() {
        requestMakerOffline = mock(RequestMakerOffline.class);
        requestMakerOnline = mock(RequestMakerOnline.class);
        databaseManager = mock(DatabaseManager.class);
    }

    //Test for online API for output and input by requestMakerOnline
    @Test
    public void getCreditBalanceOnline() throws IOException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        JSONObject returned = JSONObject.parseObject("{\n" +
                "    \"credits\": 998\n" +
                "}");
        when(requestMakerOnline.checkCreditsBalance()).thenReturn(returned);
        assertEquals(returned, model.getCreditBalanceOnline());
    }

    @Test
    public void getMacAddressInfoOnline() throws IOException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String inputAddressString = "12:12:12:12:12:12";
        JSONObject returned = JSONObject.parseObject("{\n" +
                "    \"vendorDetails\":{\n" +
                "        \"oui\":\"443839\",\n" +
                "        \"isPrivate\":false,\n" +
                "        \"companyName\":\"Cumulus Networks, Inc\",\n" +
                "        \"companyAddress\":\"650 Castro Street, suite 120-245 Mountain View  CA  94041 US\",\n" +
                "        \"countryCode\":\"US\"\n" +
                "    },\n" +
                "    \"blockDetails\":{\n" +
                "        \"blockFound\":true,\n" +
                "        \"borderLeft\":\"443839000000\",\n" +
                "        \"borderRight\":\"443839FFFFFF\",\n" +
                "        \"blockSize\":16777216,\n" +
                "        \"assignmentBlockSize\":\"MA-L\",\n" +
                "        \"dateCreated\":\"2012-04-08\",\n" +
                "        \"dateUpdated\":\"2015-09-27\"\n" +
                "    },\n" +
                "    \"macAddressDetails\":{\n" +
                "        \"searchTerm\":\"" + inputAddressString +"\",\n" +
                "        \"isValid\":true,\n" +
                "        \"virtualMachine\":\"Not detected\",\n" +
                "        \"applications\":[\n" +
                "            \"Multi-Chassis Link Aggregation (Cumulus Linux)\"\n" +
                "        ],\n" +
                "        \"transmissionType\":\"unicast\",\n" +
                "        \"administrationType\":\"UAA\",\n" +
                "        \"wiresharkNotes\":\"No details\",\n" +
                "        \"comment\":\"\"\n" +
                "    }\n" +
                "}");
        when(requestMakerOnline.searchMacAddress(inputAddressString)).thenReturn(returned);
        assertEquals(returned, model.getMacAddressInfoOnline(inputAddressString));
    }

    @Test
    public void sendEmailOnline() throws IOException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String outputSenderString = "sender";
        String outputReceiverString = "receiver";
        String outputSubjectString = "subject";
        String outputContentString = "content";

        String success = "{\"success\":\"Successfully.\"}\n";
        JSONObject returned = JSONObject.parseObject(success);

        when(requestMakerOnline.sendEmail(outputSenderString, outputReceiverString, outputSubjectString, outputContentString)).thenReturn(returned);
        assertEquals(returned, model.sendEmailOnline(outputSenderString, outputReceiverString, outputSubjectString, outputContentString));
    }

    //Test for dummy API for output and input by requestMakerOffline
    @Test
    public void getCreditBalanceOffline(){
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        JSONObject returned = JSONObject.parseObject("{\n" +
                "    \"credits\": 998\n" +
                "}");
        when(requestMakerOffline.checkCreditsBalance()).thenReturn(returned);
        assertEquals(returned, model.getCreditBalanceOffline());
    }

    @Test
    public void getMacAddressInfoOffline() throws IOException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String inputAddressString = "12:12:12:12:12:12";
        JSONObject returned = JSONObject.parseObject("{\n" +
                "    \"vendorDetails\":{\n" +
                "        \"oui\":\"443839\",\n" +
                "        \"isPrivate\":false,\n" +
                "        \"companyName\":\"Cumulus Networks, Inc\",\n" +
                "        \"companyAddress\":\"650 Castro Street, suite 120-245 Mountain View  CA  94041 US\",\n" +
                "        \"countryCode\":\"US\"\n" +
                "    },\n" +
                "    \"blockDetails\":{\n" +
                "        \"blockFound\":true,\n" +
                "        \"borderLeft\":\"443839000000\",\n" +
                "        \"borderRight\":\"443839FFFFFF\",\n" +
                "        \"blockSize\":16777216,\n" +
                "        \"assignmentBlockSize\":\"MA-L\",\n" +
                "        \"dateCreated\":\"2012-04-08\",\n" +
                "        \"dateUpdated\":\"2015-09-27\"\n" +
                "    },\n" +
                "    \"macAddressDetails\":{\n" +
                "        \"searchTerm\":\"" + inputAddressString +"\",\n" +
                "        \"isValid\":true,\n" +
                "        \"virtualMachine\":\"Not detected\",\n" +
                "        \"applications\":[\n" +
                "            \"Multi-Chassis Link Aggregation (Cumulus Linux)\"\n" +
                "        ],\n" +
                "        \"transmissionType\":\"unicast\",\n" +
                "        \"administrationType\":\"UAA\",\n" +
                "        \"wiresharkNotes\":\"No details\",\n" +
                "        \"comment\":\"\"\n" +
                "    }\n" +
                "}");
        when(requestMakerOffline.searchMacAddress(inputAddressString)).thenReturn(returned);
        assertEquals(returned, model.getMacAddressInfoOffline(inputAddressString));
    }

    @Test
    public void sendEmailOffline(){
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String outputSenderString = "sender";
        String outputReceiverString = "receiver";
        String outputSubjectString = "subject";
        String outputContentString = "content";

        String success = "{\"success\":\"Successfully.\"}\n";
        JSONObject returned = JSONObject.parseObject(success);

        when(requestMakerOffline.sendEmail(outputSenderString, outputReceiverString, outputSubjectString, outputContentString)).thenReturn(returned);
        assertEquals(returned, model.sendEmailOffline(outputSenderString, outputReceiverString, outputSubjectString, outputContentString));
    }

    //Test for database by DatabaseManager
    @Test
    public void initialize() throws SQLException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        boolean returned = true;
        when(databaseManager.initialize()).thenReturn(returned);
        assertEquals(returned, model.initialize());
    }

    @Test
    public void insertInfo() {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        boolean returned = true;
        String address = "50:1F:C6:64:D0:09";
        String information = "---------------------------------------------------------------------------------\n" +
                "\n" +
                "Mac Address Details\n" +
                "searchTerm: 50:1F:C6:64:D0:09\n" +
                "isValid: true\n" +
                "virtualMachine: Not detected\n" +
                "applications: []\n" +
                "transmissionType: unicast\n" +
                "administrationType: UAA\n" +
                "wiresharkNotes: No details\n" +
                "comment: \n" +
                "\n" +
                "Block Details\n" +
                "blockFound: true\n" +
                "borderLeft: 501FC6000000\n" +
                "borderRight: 501FC6FFFFFF\n" +
                "blockSize: 16777216\n" +
                "assignmentBlockSize: MA-L\n" +
                "dateCreated: 2020-11-25\n" +
                "dateUpdated: 2020-11-25\n" +
                "\n" +
                "Vendor Details\n" +
                "oui: 501FC6\n" +
                "isPrivate: false\n" +
                "companyName: Apple, Inc\n" +
                "companyAddress: 1 Infinite Loop Cupertino CA 95014 US\n" +
                "countryCode: US\n" +
                "\n" +
                "---------------------------------------------------------------------------------\n" +
                "\n" +
                "Next Mac Address Information will be after here";
        when(databaseManager.insertInfo(address, information)).thenReturn(returned);
        assertEquals(returned, model.insertInfo(address, information));
    }

    @Test
    public void selectInfo() {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String returned = "---------------------------------------------------------------------------------\n" +
                "\n" +
                "Mac Address Details\n" +
                "searchTerm: 50:1F:C6:64:D0:09\n" +
                "isValid: true\n" +
                "virtualMachine: Not detected\n" +
                "applications: []\n" +
                "transmissionType: unicast\n" +
                "administrationType: UAA\n" +
                "wiresharkNotes: No details\n" +
                "comment: \n" +
                "\n" +
                "Block Details\n" +
                "blockFound: true\n" +
                "borderLeft: 501FC6000000\n" +
                "borderRight: 501FC6FFFFFF\n" +
                "blockSize: 16777216\n" +
                "assignmentBlockSize: MA-L\n" +
                "dateCreated: 2020-11-25\n" +
                "dateUpdated: 2020-11-25\n" +
                "\n" +
                "Vendor Details\n" +
                "oui: 501FC6\n" +
                "isPrivate: false\n" +
                "companyName: Apple, Inc\n" +
                "companyAddress: 1 Infinite Loop Cupertino CA 95014 US\n" +
                "countryCode: US\n" +
                "\n" +
                "---------------------------------------------------------------------------------\n" +
                "\n" +
                "Next Mac Address Information will be after here\n";
        String macAddressInput = "50:1F:C6:64:D0:09";
        when(databaseManager.selectInfo(macAddressInput)).thenReturn(returned);
        assertEquals(returned, model.selectInfo(macAddressInput));
    }

    @Test
    public void updateInfo() {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        boolean returned = true;
        String address = "50:1F:C6:64:D0:09";
        String information = "---------------------------------------------------------------------------------\n" +
                "\n" +
                "Mac Address Details\n" +
                "searchTerm: 50:1F:C6:64:D0:09\n" +
                "isValid: true\n" +
                "virtualMachine: Not detected\n" +
                "applications: []\n" +
                "transmissionType: unicast\n" +
                "administrationType: UAA\n" +
                "wiresharkNotes: No details\n" +
                "comment: \n" +
                "\n" +
                "Block Details\n" +
                "blockFound: true\n" +
                "borderLeft: 501FC6000000\n" +
                "borderRight: 501FC6FFFFFF\n" +
                "blockSize: 16777216\n" +
                "assignmentBlockSize: MA-L\n" +
                "dateCreated: 2020-11-25\n" +
                "dateUpdated: 2020-11-25\n" +
                "\n" +
                "Vendor Details\n" +
                "oui: 501FC6\n" +
                "isPrivate: false\n" +
                "companyName: Apple, Inc\n" +
                "companyAddress: 1 Infinite Loop Cupertino CA 95014 US\n" +
                "countryCode: US\n" +
                "\n" +
                "---------------------------------------------------------------------------------\n" +
                "\n" +
                "Next Mac Address Information will be after here";
        when(databaseManager.updateInfo(address, information)).thenReturn(returned);
        assertEquals(returned, model.updateInfo(address, information));
    }

    @Test
    public void compareToUpdatedDateOnline() throws IOException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String inputAddressString = "12:12:12:12:12:12";
        JSONObject returned = JSONObject.parseObject("{\n" +
                "    \"vendorDetails\":{\n" +
                "        \"oui\":\"443839\",\n" +
                "        \"isPrivate\":false,\n" +
                "        \"companyName\":\"Cumulus Networks, Inc\",\n" +
                "        \"companyAddress\":\"650 Castro Street, suite 120-245 Mountain View  CA  94041 US\",\n" +
                "        \"countryCode\":\"US\"\n" +
                "    },\n" +
                "    \"blockDetails\":{\n" +
                "        \"blockFound\":true,\n" +
                "        \"borderLeft\":\"443839000000\",\n" +
                "        \"borderRight\":\"443839FFFFFF\",\n" +
                "        \"blockSize\":16777216,\n" +
                "        \"assignmentBlockSize\":\"MA-L\",\n" +
                "        \"dateCreated\":\"2012-04-08\",\n" +
                "        \"dateUpdated\":\"2015-09-27\"\n" +
                "    },\n" +
                "    \"macAddressDetails\":{\n" +
                "        \"searchTerm\":\"" + inputAddressString +"\",\n" +
                "        \"isValid\":true,\n" +
                "        \"virtualMachine\":\"Not detected\",\n" +
                "        \"applications\":[\n" +
                "            \"Multi-Chassis Link Aggregation (Cumulus Linux)\"\n" +
                "        ],\n" +
                "        \"transmissionType\":\"unicast\",\n" +
                "        \"administrationType\":\"UAA\",\n" +
                "        \"wiresharkNotes\":\"No details\",\n" +
                "        \"comment\":\"\"\n" +
                "    }\n" +
                "}");
        when(requestMakerOnline.searchMacAddress(inputAddressString)).thenReturn(returned);
        assertEquals(true, model.compareToUpdatedYearOnline(inputAddressString));
    }

    @Test
    public void compareToUpdatedDateOffline() throws IOException {
        ModelFacade model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
        String inputAddressString = "12:12:12:12:12:12";
        JSONObject returned = JSONObject.parseObject("{\n" +
                "    \"vendorDetails\":{\n" +
                "        \"oui\":\"443839\",\n" +
                "        \"isPrivate\":false,\n" +
                "        \"companyName\":\"Cumulus Networks, Inc\",\n" +
                "        \"companyAddress\":\"650 Castro Street, suite 120-245 Mountain View  CA  94041 US\",\n" +
                "        \"countryCode\":\"US\"\n" +
                "    },\n" +
                "    \"blockDetails\":{\n" +
                "        \"blockFound\":true,\n" +
                "        \"borderLeft\":\"443839000000\",\n" +
                "        \"borderRight\":\"443839FFFFFF\",\n" +
                "        \"blockSize\":16777216,\n" +
                "        \"assignmentBlockSize\":\"MA-L\",\n" +
                "        \"dateCreated\":\"2012-04-08\",\n" +
                "        \"dateUpdated\":\"2015-09-27\"\n" +
                "    },\n" +
                "    \"macAddressDetails\":{\n" +
                "        \"searchTerm\":\"" + inputAddressString +"\",\n" +
                "        \"isValid\":true,\n" +
                "        \"virtualMachine\":\"Not detected\",\n" +
                "        \"applications\":[\n" +
                "            \"Multi-Chassis Link Aggregation (Cumulus Linux)\"\n" +
                "        ],\n" +
                "        \"transmissionType\":\"unicast\",\n" +
                "        \"administrationType\":\"UAA\",\n" +
                "        \"wiresharkNotes\":\"No details\",\n" +
                "        \"comment\":\"\"\n" +
                "    }\n" +
                "}");
        when(requestMakerOffline.searchMacAddress(inputAddressString)).thenReturn(returned);
        assertEquals(true, model.compareToUpdatedYearOffline(inputAddressString));
    }
}

