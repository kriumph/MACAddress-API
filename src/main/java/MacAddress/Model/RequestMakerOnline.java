package MacAddress.Model;

import MacAddress.App;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import java.io.IOException;

/**
 * RequestMakerOnline implements RequestMaker interface
 * The class overrides all the methods that in interface
 * The class will return the dummy result default
 */
public class RequestMakerOnline implements RequestMaker {

    private static final MediaType JSON_TYPE = MediaType.parse("application/json;charset=utf-8");
    private final String inputKey = App.readFile("src/main/resources/input.txt");
    private final String outputKey = App.readFile("src/main/resources/output.txt");

    /**
     * @param url - the request url
     * @return String from result of API in JSON format
     * @throws IOException
     */
    public String httpGet(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String result;
        okhttp3.Request request = new okhttp3.Request
                .Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String string = response.body().string();
        result = String.valueOf(JSON.parse(string));
        return result;
    }

    /**
     * @param url - the request url
     * @param params - the request contains sender, receiver, subject and content
     * @return String from result of API in JSON format
     * @throws IOException
     */
    public String postData(String url, JSONObject params) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(params));
        okhttp3.Request request = new okhttp3.Request
                .Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + outputKey)
                .build();
        Response response = null;
        response = okHttpClient.newCall(request).execute();
        return response.body().string() ;
    }

    /**
     * @return JSONObject - a JSONObject parsed from API result
     */
    @Override
    public JSONObject checkCreditsBalance() throws IOException {
        return JSONObject.parseObject(httpGet("https://api.macaddress.io/v1/credits?apiKey=" + inputKey + "&output=json"));
    }

    /**
     * @param inputAddressString - string of input address
     * @return JSONObject - a JSONObject parsed from API result
     */
    @Override
    public JSONObject searchMacAddress(String inputAddressString) throws IOException {
        return JSONObject.parseObject(httpGet("https://api.macaddress.io/v1?apiKey=" + inputKey + "&output=json&search=" + inputAddressString));
    }

    /**
     * @param outputSenderString - string of output sender email address
     * @param outputReceiverString - string of output receiver email address
     * @param outputSubjectString - string of output subject
     * @param outputContentString - string of output content that will be sent
     * @return JSONObject - a JSONObject parsed from API result
     */
    @Override
    public JSONObject sendEmail(String outputSenderString, String outputReceiverString, String outputSubjectString, String outputContentString) throws IOException {

        String request = "{\"personalizations\":" +
                "[{\"to\":[{\"email\":\"" + outputReceiverString + "\"}]}]," +
                "\"from\":{\"email\":\"" + outputSenderString + "\"}," +
                "\"subject\":\"" + outputSubjectString + "\"," +
                "\"content\":[{\"type\":\"text/plain\",\"value\":\"" + outputContentString + "\"}]}";

        JSONObject data = JSONObject.parseObject(request);
        String result = postData("https://api.sendgrid.com/v3/mail/send", data);
        String success = "{\"success\":\"Successfully.\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(result);

        if(jsonObject != null){
            return jsonObject;
        }else{
            return JSONObject.parseObject(success);
        }
    }
}
