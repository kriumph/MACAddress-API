package MacAddress.Model;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;

/**
 *
 * RequestMaker interface contains input API and output API method
 * Interface defines several methods that will be used
 */
public interface RequestMaker {
    JSONObject checkCreditsBalance() throws IOException;
    JSONObject searchMacAddress(String inputAddressString) throws IOException;
    JSONObject sendEmail(String outputReceiverString, String outputSenderString, String outputSubjectString, String outputContentString) throws IOException;
}
