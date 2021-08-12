package MacAddress.Model;

import com.alibaba.fastjson.JSONObject;

/**
 * RequestMakerOffline implements RequestMaker interface
 * The class overrides all the methods that in interface
 * The class will return the dummy result default
 */
public class RequestMakerOffline implements RequestMaker{

    /**
     * @return JSONObject - a dummy JSONObject
     */
    @Override
    public JSONObject checkCreditsBalance(){
        return JSONObject.parseObject("{\n" +
                "    \"credits\": 998\n" +
                "}");
    }

    /**
     * @param inputAddressString - string of input address
     * @return JSONObject - a dummy JSONObject
     */
    @Override
    public JSONObject searchMacAddress(String inputAddressString){
        return JSONObject.parseObject("{\n" +
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
    }

    /**
     * @param outputSenderString - string of output sender email address
     * @param outputReceiverString - string of output receiver email address
     * @param outputSubjectString - string of output subject
     * @param outputContentString - string of output content that will be sent
     * @return JSONObject - a dummy JSONObject
     */
    @Override
    public JSONObject sendEmail(String outputSenderString, String outputReceiverString, String outputSubjectString, String outputContentString) {
        String success = "{\"success\":\"Successfully.\"}\n";
        return JSONObject.parseObject(success);
    }

}
