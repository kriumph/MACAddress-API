package MacAddress.Presenter;

import MacAddress.Database.DatabaseManager;
import MacAddress.Model.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.io.IOException;

import static MacAddress.App.isOnlineInput;
import static MacAddress.App.isOnlineOutput;

/**
 * Presenter class will get the value of information from model
 * then will set the information to the GUI
 */
public class Presenter{

    private ModelFacade model;
    /** The GUI should display for multiple requests, it will be iterated */
    private String contentString = "";
    private int indicatedYear;
    private String contentOutputString = "";

    @FXML
    private Pane Home, Search, Check, Output, Indicate;
    public ScrollPane macAddressInfo, creditsBalance, OutputInfo;
    public TextField macAddressInput, outputSender, outputReceiver, outputSubject, IndicatorInput;
    public Text IndicatorWarning;

    private OnlineCreditService onlineCreditService = new OnlineCreditService();
    private OfflineCreditService offlineCreditService = new OfflineCreditService();
    private OnlineSearchService onlineSearchService = new OnlineSearchService();
    private OfflineSearchService offlineSearchService = new OfflineSearchService();
    private OnlineOutputService onlineOutputService = new OnlineOutputService();
    private OfflineOutputService offlineOutputService = new OfflineOutputService();
    private InsertService insertService = new InsertService();
    private SelectService selectService = new SelectService();

    /**
     * This method capsule the set visible operation into one module
     * @param toVisible Pane - that will be set as visible true
     */
    public void visible_control(Pane toVisible){
        Home.setVisible(false);
        Search.setVisible(false);
        Check.setVisible(false);
        Output.setVisible(false);
        Indicate.setVisible(false);
        toVisible.toFront();
        toVisible.setVisible(true);
    }

    /**
     * ActionEvent representing some type of action.
     * After indicate button clicked, check the indicated year,
     * if it is valid, turn to Home page,
     * if not, ask input again
     * @param actionEvent
     */
    public void IndicateButtonClick(ActionEvent actionEvent) {
        try{
            Integer indicatorInput = Integer.parseInt(IndicatorInput.getCharacters().toString());
            if (indicatorInput <= 2021 && indicatorInput >= 1900){
                indicatedYear = indicatorInput;
                visible_control(Home);
            }else{
                IndicatorWarning.setText("It MUST be bettwen 1900 and 2021, please try again");
            }
        }catch(Exception e){
            IndicatorWarning.setText("It MUST be bettwen 1900 and 2021, please try again");
        }

    }

    /**
     * ActionEvent representing some type of action.
     * After home button clicked, turn to home page
     * @param actionEvent
     */
    public void HomeClick(ActionEvent actionEvent){
        visible_control(Home);
    }

    /**
     * ActionEvent representing some type of action.
     * After search button clicked, turn to search page
     * @param actionEvent
     */
    public void SearchPageClick(ActionEvent actionEvent){
        visible_control(Search);
    }

    /**
     * ActionEvent representing some type of action.
     * After clear button clicked, clear the macAddressInfo
     * @param actionEvent
     */
    public void ClearButtonClick(ActionEvent actionEvent) {
        contentString = "";
        Text text = new Text(contentString);
        macAddressInfo.setContent(text);
    }

    /**
     * ActionEvent representing some type of action.
     * After search API button clicked, it distinguishes the isOnlineInput
     * If the isOnlineInput is true, send the request to API
     * and set the result to scrollpane
     * If the isOnlineInput is false, set the dummy result to scrollpane
     * @param actionEvent
     */
    public void SearchAPIButtonClick(ActionEvent actionEvent) {
        RequestMakerOnline requestMakerOnline = new RequestMakerOnline();
        RequestMakerOffline requestMakerOffline = new RequestMakerOffline();
        DatabaseManager databaseManager = new DatabaseManager();
        model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);

        if(isOnlineInput){
            onlineSearchService.macAddressInputString = macAddressInput.getCharacters().toString();
            insertService.macAddressInputString = macAddressInput.getCharacters().toString();
            onlineSearchService.restart();
            insertService.restart();
        }else{
            offlineSearchService.macAddressInputString = macAddressInput.getCharacters().toString();
            offlineSearchService.restart();

        }
    }

    /**
     * ActionEvent representing some type of action.
     * After search Database button clicked, it distinguishes the isOnlineInput
     * If the isOnlineInput is true, select the information from database
     * and set the result to scrollpane
     * If the isOnlineInput is false, set the dummy result to scrollpane
     * @param actionEvent
     */
    public void SearchDatabaseButtonClick(ActionEvent actionEvent) {
        RequestMakerOnline requestMakerOnline = new RequestMakerOnline();
        RequestMakerOffline requestMakerOffline = new RequestMakerOffline();
        DatabaseManager databaseManager = new DatabaseManager();
        model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);


        if(isOnlineInput){
            selectService.macAddressInputString = macAddressInput.getCharacters().toString();
            selectService.restart();
        }else{
            offlineSearchService.macAddressInputString = macAddressInput.getCharacters().toString();
            offlineSearchService.restart();
        }

    }

    /**
     * ActionEvent representing some type of action.
     * After check button clicked, turn to check page
     * @param actionEvent
     */
    public void CheckPageClick(ActionEvent actionEvent){
        visible_control(Check);
    }

    /**
     * ActionEvent representing some type of action.
     * After check credits button clicked, it distinguishes the isOnlineInput
     * If the isOnlineInput is true, send the request to API
     * and set the result to scrollpane
     * If the isOnlineInput is false, set the dummy result to scrollpane
     * @param actionEvent
     */
    public void CheckButtonClick(ActionEvent actionEvent) {
        RequestMakerOnline requestMakerOnline = new RequestMakerOnline();
        RequestMakerOffline requestMakerOffline = new RequestMakerOffline();
        DatabaseManager databaseManager = new DatabaseManager();
        model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);

        if(isOnlineInput){
            onlineCreditService.restart();
        }else{
            offlineCreditService.restart();
        }
    }

    /**
     * ActionEvent representing some type of action.
     * After output button clicked, turn to output page
     * Set the content of output
     * @param actionEvent
     */
    public void OutputButtonClick(ActionEvent actionEvent){
        visible_control(Output);
        contentOutputString = contentString.replace("Too New", "*");
        Text text = new Text(contentOutputString);
        OutputInfo.setContent(text);
    }
    /**
     * ActionEvent representing some type of action.
     * After send email button clicked, it distinguishes the isOnlineOutput
     * If the isOnlineOutput is true, send the request to API to send email
     * and set the result to scrollpane
     * If the isOnlineOutput is false, set the dummy result to scrollpane
     * @param actionEvent
     */
    public void SendEmailButtonClick(ActionEvent actionEvent) {
        RequestMakerOnline requestMakerOnline = new RequestMakerOnline();
        RequestMakerOffline requestMakerOffline = new RequestMakerOffline();
        DatabaseManager databaseManager = new DatabaseManager();
        model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);

        onlineOutputService.outputSenderString = outputSender.getCharacters().toString();
        onlineOutputService.outputReceiverString = outputReceiver.getCharacters().toString();
        onlineOutputService.outputSubjectString = outputSubject.getCharacters().toString();
        onlineOutputService.outputContentString = contentOutputString;

        if(contentString == ""){
            Text text = new Text("Content cannot be blank");
            OutputInfo.setContent(text);
        }

        if (isOnlineOutput) {
            onlineOutputService.restart();
        } else {
            offlineOutputService.restart();
        }
    }

    /**
     * Check the output result from output and parse the data
     * Then return a String of result that be more readable
     * @param jsonObject - the jsonObject parsed from output result
     * @param outputSenderString - the string of sender email address
     * @param outputReceiverString - the string of receiver email address
     * @return parsed string of output result
     */
    public String checkOutputResult(JSONObject jsonObject, String outputSenderString, String outputReceiverString) {
        if (jsonObject != null && jsonObject.containsKey("errors")) {
            JSONObject object = JSONObject.parseObject(jsonObject.toString());
            return JSON.toJSONString(object, SerializerFeature.PrettyFormat);

        } else {
            return "Successfully send email from " + outputSenderString + " to " + outputReceiverString;
        }
    }

    /**
     * Check the check credits result from output and parse the data
     * Then return a String of credits result that be more readable
     * @param jsonObject - the jsonObject parsed from check credits result
     * @return parsed string of check result
     */
    public String checkCreditResult(JSONObject jsonObject) {
        if(jsonObject.containsKey("credits")){
            return "Left Credits: " + jsonObject.getString("credits");

        }else {
            return jsonObject.getString("error");
        }
    }

    /**
     * Check the select sql query result from database and parse the data
     * @param info - the information of select execution of sql query
     * @param macAddressInput - the selected macAddressInput
     * @return parsed string of select result
     */
    public String checkSelectResult(String info, String macAddressInput) {
        if(info != null){
            return info;
        }else{
            return "---------------------------------------------------------------------------------\n\n"  +
                    "Some errors caught when search for " + macAddressInput +
                    "\nThe reason: Database have no information about that." + "\n\n" +
                    "---------------------------------------------------------------------------------\n\n" +
                    "Next Mac Address Information will be after here\n\n";
        }
    }
    /**
     *
     * Check the input API result and parse the data
     * @param jsonObject - the information of input API request
     * @param macAddressInput - the searched macAddressInput
     * @return parsed string of input API result
     */
    public String checkAPIResult(JSONObject jsonObject, String macAddressInput) throws IOException {
        if(jsonObject.containsKey("vendorDetails")){
            MacAddressInfo content = JSONObject.parseObject(jsonObject.toString(), MacAddressInfo.class);
            if (!content.toString().equals("The MAC Address is invalid.")){
                RequestMakerOnline requestMakerOnline = new RequestMakerOnline();
                RequestMakerOffline requestMakerOffline = new RequestMakerOffline();
                DatabaseManager databaseManager = new DatabaseManager();
                model = new ModelFacadeImpl(requestMakerOnline, requestMakerOffline, databaseManager);
                model.setIndicatedYear(indicatedYear);
                boolean isAfterIndicatedYear;
                if(isOnlineInput){
                    isAfterIndicatedYear = model.compareToUpdatedYearOnline(macAddressInput);
                }else{
                    isAfterIndicatedYear = model.compareToUpdatedYearOffline(macAddressInput);
                }
                content.isAfterIndicatedYear(isAfterIndicatedYear);
                return content.toString();
            }else{
                return "---------------------------------------------------------------------------------\n\n"  +
                        "Some errors caught when search for " + macAddressInput +
                        "\nThe reason: MAC Address is invalid.\n\n" +
                        "---------------------------------------------------------------------------------\n\n" +
                        "Next Mac Address Information will be after here\n\n";
            }
        }else{
            return "---------------------------------------------------------------------------------\n\n"  +
                    "Some errors caught when search for " + macAddressInput +
                    "\nThe reason: " + jsonObject.getString("error") + "\n\n" +
                    "---------------------------------------------------------------------------------\n\n" +
                    "Next Mac Address Information will be after here\n\n";
        }
    }

    /**
     * inner class OnlineCreditService
     * to create a thread for concurrency to get value and set the content
     */
    private class OnlineCreditService extends Service {

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * Get the credit balance online
                 * @return JSONObject that returned by model
                 * @throws Exception
                 */
                @Override
                protected JSONObject call() throws Exception {
                    return model.getCreditBalanceOnline();
                }

                /**
                 * refresh the GUI to display information
                 */
                @Override
                protected void succeeded() {
                    JSONObject jsonObject = (JSONObject) getValue();
                    Text text = new Text(checkCreditResult(jsonObject));
                    creditsBalance.setContent(text);
                }
            };
        }
    }

    /**
     * inner class OnlineSearchService
     * to create a thread for concurrency to get value and set the content
     */
    private class OnlineSearchService extends Service {
        private String macAddressInputString;

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * Search the mac address online
                 * @return JSONObject that returned by model
                 * @throws Exception
                 */
                @Override
                protected JSONObject call() throws Exception {
                    return model.getMacAddressInfoOnline(macAddressInputString);
                }

                /**
                 * refresh the GUI to display information and update the database
                 */
                @Override
                protected void succeeded() {
                    JSONObject jsonObject = (JSONObject) getValue();
                    String result = null;
                    try {
                        result = checkAPIResult(jsonObject, macAddressInputString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    model.updateInfo(macAddressInputString, result);
                    contentString += result;
                    Text text = new Text(contentString);
                    macAddressInfo.setContent(text);
                    macAddressInfo.setVvalue(1.0);
                }
            };
        }
    }

    /**
     * inner class OnlineOutputService
     * to create a thread for concurrency to get value and set the content
     */
    private class OnlineOutputService extends Service {
        private String outputSenderString, outputReceiverString, outputSubjectString, outputContentString;

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * send the email online
                 * @return JSONObject that returned by model
                 * @throws Exception
                 */
                @Override
                protected JSONObject call() throws Exception {
                    return model.sendEmailOnline(outputSenderString, outputReceiverString, outputSubjectString, outputContentString);
                }

                /**
                 * refresh the GUI to display information
                 */
                @Override
                protected void succeeded() {
                    JSONObject jsonObject = (JSONObject) getValue();
                    Text text = new Text(checkOutputResult(jsonObject, outputSenderString, outputReceiverString));
                    OutputInfo.setContent(text);
                }
            };
        }
    }

    /**
     * inner class OfflineCreditService
     * to create a thread for concurrency to get value and set the content
     */
    private class OfflineCreditService extends Service {

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * get credit balance offline
                 * @return JSONObject that returned by model
                 * @throws Exception
                 */
                @Override
                protected JSONObject call() throws Exception {
                    Thread.sleep(500);
                    return model.getCreditBalanceOffline();
                }

                /**
                 * refresh the GUI to display information
                 */
                @Override
                protected void succeeded() {
                    JSONObject jsonObject = (JSONObject) getValue();
                    Text text = new Text(checkCreditResult(jsonObject));
                    creditsBalance.setContent(text);
                }
            };
        }
    }

    /**
     * inner class OfflineSearchService
     * to create a thread for concurrency to get value and set the content
     */
    private class OfflineSearchService extends Service {
        private String macAddressInputString;

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * search the mac address offline
                 * @return JSONObject that returned by model
                 * @throws Exception
                 */
                @Override
                protected JSONObject call() throws Exception {
                    Thread.sleep(500);
                    return model.getMacAddressInfoOffline(macAddressInputString);
                }

                /**
                 * refresh the GUI to display information
                 */
                @Override
                protected void succeeded() {
                    JSONObject jsonObject = (JSONObject) getValue();
                    String result = null;
                    try {
                        result = checkAPIResult(jsonObject, macAddressInputString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    contentString += result;
                    Text text = new Text(contentString);
                    macAddressInfo.setContent(text);
                    macAddressInfo.setVvalue(1.0);
                }
            };
        }
    }

    /**
     * inner class OfflineOutputService
     * to create a thread for concurrency to get value and set the content
     */
    private class OfflineOutputService extends Service {
        private String outputSenderString, outputReceiverString, outputSubjectString, outputContentString;

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * send the email offline
                 * @return JSONObject that returned by model
                 * @throws Exception
                 */
                @Override
                protected JSONObject call() throws Exception {
                    Thread.sleep(500);
                    return model.sendEmailOffline(outputSenderString, outputReceiverString, outputSubjectString, outputContentString);
                }

                /**
                 * refresh the GUI to display information
                 */
                @Override
                protected void succeeded() {
                    JSONObject jsonObject = (JSONObject) getValue();
                    Text text = new Text(checkOutputResult(jsonObject, outputSenderString, outputReceiverString));
                    OutputInfo.setContent(text);
                }
            };
        }
    }

    /**
     * inner class InitializeService
     * to create a thread for concurrency to get value and set the content
     * that will never access by the user
     */
    private class InitializeService extends Service {

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * initialize the database
                 * @return boolean that returned by model
                 * @throws Exception
                 */
                @Override
                protected Object call() throws Exception {
                    return model.initialize();
                }
            };
        }
    }

    /**
     * inner class InsertService
     * to create a thread for concurrency to get value and set the content
     */
    private class InsertService extends Service {
        private String macAddressInputString;

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * insert the information
                 * @return boolean that returned by model
                 * @throws Exception
                 */
                @Override
                protected Object call() throws Exception {
                    String result = checkAPIResult(model.getMacAddressInfoOnline(macAddressInputString), macAddressInputString);
                    return model.insertInfo(macAddressInputString, result);
                }
            };
        }
    }

    /**
     * inner class SelectService
     * to create a thread for concurrency to get value and set the content
     */
    private class SelectService extends Service {
        private String macAddressInputString;

        /**
         * Overrides createTask for own usage
         * @return a concurrent task
         */
        @Override
        protected Task createTask() {
            return new Task() {

                /**
                 * select the information from database
                 * @return String that returned by model
                 * @throws Exception
                 */
                @Override
                protected String call() throws Exception {
                    Thread.sleep(500);
                    return model.selectInfo(macAddressInputString);
                }

                /**
                 * refresh the GUI to display information
                 */
                @Override
                protected void succeeded() {
                    String info = (String) getValue();
                    String result = checkSelectResult(info, macAddressInputString);
                    contentString += result;
                    Text text = new Text(contentString);
                    macAddressInfo.setContent(text);
                    macAddressInfo.setVvalue(1.0);
                }
            };
        }
    }
}