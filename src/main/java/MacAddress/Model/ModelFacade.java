package MacAddress.Model;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * ModelFacade interface contains all model's methods
 * Interface defines several methods that will be used
 */
public interface ModelFacade {

    JSONObject getCreditBalanceOnline() throws IOException;
    JSONObject getMacAddressInfoOnline(String inputAddressString) throws IOException;
    JSONObject sendEmailOnline(String outputReceiverString, String outputSenderString, String outputSubjectString, String outputContentString) throws IOException;

    JSONObject getCreditBalanceOffline();
    JSONObject getMacAddressInfoOffline(String inputAddressString);
    JSONObject sendEmailOffline(String outputSenderString, String outputReceiverString, String outputSubjectString, String outputContentString);

    boolean initialize() throws SQLException;
    boolean insertInfo(String address, String information);
    String selectInfo(String address);
    boolean updateInfo(String address, String information);

    void setIndicatedYear(int indicatedYear);
    int getIndicatedYear();
    boolean compareToUpdatedYearOnline(String inputAddressString) throws IOException;
    boolean compareToUpdatedYearOffline(String inputAddressString);
}
