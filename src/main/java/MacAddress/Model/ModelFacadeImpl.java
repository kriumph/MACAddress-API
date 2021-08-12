package MacAddress.Model;

import MacAddress.Database.DatabaseManager;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;

/**
 * ModelFacadeImpl implements ModelFacade interface
 * The class overrides all the methods that in interface
 * The model will call two RequestMaker for offline and online version
 * The model will call DatabaseManager to execute some operations
 */
public class ModelFacadeImpl implements ModelFacade {
    private RequestMakerOnline requestMakerOnline;
    private RequestMakerOffline requestMakerOffline;
    private DatabaseManager databaseManager;
    private int indicatedYear;

    /**
     * constructor of ModelFacadeImpl
     * @param requestMakerOnline an object of RequestMakerOnline class
     * @param requestMakerOffline an object of RequestMakerOffline class
     * @param databaseManager an object of DatabaseManager class
     */
    public ModelFacadeImpl(RequestMakerOnline requestMakerOnline, RequestMakerOffline requestMakerOffline, DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        this.requestMakerOffline = requestMakerOffline;
        this.requestMakerOnline = requestMakerOnline;
        this.indicatedYear = 0;
    }

    /**
     * get the credit balance online
     * @return JSONObject - the result of checkCreditsBalance()
     * @throws IOException
     */
    @Override
    public JSONObject getCreditBalanceOnline() throws IOException {
        return this.requestMakerOnline.checkCreditsBalance();
    }

    /**
     * get the mac address information online
     * @return JSONObject - the result of searchMacAddress()
     * @throws IOException
     */
    @Override
    public JSONObject getMacAddressInfoOnline(String inputAddressString) throws IOException {
        return this.requestMakerOnline.searchMacAddress(inputAddressString);
    }

    /**
     * @param outputSenderString - string of output sender email address
     * @param outputReceiverString - string of output receiver email address
     * @param outputSubjectString - string of output subject
     * @param outputContentString - string of output content that will be sent
     * @return JSONObject - the result of sendEmail()
     * @throws IOException
     */
    @Override
    public JSONObject sendEmailOnline(String outputSenderString, String outputReceiverString, String outputSubjectString, String outputContentString) throws IOException {
        return this.requestMakerOnline.sendEmail(outputSenderString, outputReceiverString, outputSubjectString, outputContentString);
    }

    /**
     * @param outputSenderString - string of output sender email address
     * @param outputReceiverString - string of output receiver email address
     * @param outputSubjectString - string of output subject
     * @param outputContentString - string of output content that will be sent
     * @return JSONObject - the result of sendEmail()
     * @throws IOException
     */
    @Override
    public JSONObject sendEmailOffline(String outputSenderString, String outputReceiverString, String outputSubjectString, String outputContentString) {
        return this.requestMakerOffline.sendEmail(outputSenderString, outputReceiverString, outputSubjectString, outputContentString);
    }

    /**
     * get the credit balance offline
     * @return JSONObject - the result of checkCreditsBalance()
     * @throws IOException
     */
    @Override
    public JSONObject getCreditBalanceOffline() {
        return this.requestMakerOffline.checkCreditsBalance();
    }

    /**
     * get the mac address information offline
     * @param inputAddressString - string of input address
     * @return JSONObject - the result of searchMacAddress()
     * @throws IOException
     */
    @Override
    public JSONObject getMacAddressInfoOffline(String inputAddressString) {
        return this.requestMakerOffline.searchMacAddress(inputAddressString);
    }

    /**
     * initialize the database
     * @return boolean - whether the initialize execution is successful
     */
    @Override
    public boolean initialize(){
        return this.databaseManager.initialize();
    }


    /**
     * @param address - string of inserted address
     * @param information - string of inserted information
     * insert the information into the database
     * @return boolean - whether the insert execution is successful
     */
    @Override
    public boolean insertInfo(String address, String information) {
        return this.databaseManager.insertInfo(address, information);
    }

    /**
     * @param address - string of selected address
     * select the information from the database
     * @return String - the selected information from database
     */
    @Override
    public String selectInfo(String address) {
        return this.databaseManager.selectInfo(address);
    }

    /**
     * @param address - string of updated address
     * @param information - string of updated information
     * update the information into the database
     * @return boolean - whether the update execution is successful
     */
    @Override
    public boolean updateInfo(String address, String information) {
        return this.databaseManager.updateInfo(address, information);
    }

    /**
     * @param indicatedYear - int of indicated year
     * set the information about indicated year
     */
    @Override
    public void setIndicatedYear(int indicatedYear) {
        this.indicatedYear = indicatedYear;
    }

    /**
     * get the information about indicated year
     * @return int - int of indicated year
     */
    @Override
    public int getIndicatedYear() {
        return this.indicatedYear;
    }

    /**
     * @param inputAddressString - string of updated address
     * compare the updated date with indicated year online
     * @return boolean - whether the updated date is after the indicated year
     */
    @Override
    public boolean compareToUpdatedYearOnline(String inputAddressString) throws IOException {
        int indicatedYear = this.getIndicatedYear();
        int updatedYear = Integer.parseInt(this.requestMakerOnline.searchMacAddress(inputAddressString).getJSONObject("blockDetails").getString("dateUpdated").substring(0, 4));
        if (indicatedYear < updatedYear) {
            return true;
        }
        return false;
    }

    /**
     * @param inputAddressString - string of updated address
     * compare the updated date with indicated year offline
     * @return boolean - whether the updated date is after the indicated year
     */
    @Override
    public boolean compareToUpdatedYearOffline(String inputAddressString){
        int indicatedYear = this.getIndicatedYear();
        int updatedYear = Integer.parseInt(this.requestMakerOffline.searchMacAddress(inputAddressString).getJSONObject("blockDetails").getString("dateUpdated").substring(0, 4));
        if (indicatedYear < updatedYear) {
            return true;
        }
        return false;
    }


}
