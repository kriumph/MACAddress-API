package MacAddress;

import MacAddress.Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class App extends Application{
    public static boolean isOnlineInput, isOnlineOutput;
    public DatabaseManager databaseManager = new DatabaseManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        databaseManager.initialize();
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("prework.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mac Address");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static void main(String[] args){
        if(args.length != 2){
            System.out.println("Run with \"gradle run --args=\"[inputStatus outputStatus]\"\",\n" +
                    "[status] could only be \"offline\" or \"online\"");
            System.exit(0);
        }else if(args[0].equalsIgnoreCase("offline") && args[1].equalsIgnoreCase("offline")){
            isOnlineInput = false;
            isOnlineOutput = false;
            launch();
        }else if(args[0].equalsIgnoreCase("online") && args[1].equalsIgnoreCase("offline")){
            isOnlineInput = true;
            isOnlineOutput = false;
            launch();
        }else if(args[0].equalsIgnoreCase("offline") && args[1].equalsIgnoreCase("online")){
            isOnlineInput = false;
            isOnlineOutput = true;
            launch();
        }else if(args[0].equalsIgnoreCase("online") && args[1].equalsIgnoreCase("online")){
            isOnlineInput = true;
            isOnlineOutput = true;
            launch();
        }else{
            System.out.println("Run with \"gradle run --args=\"[inputStatus outputStatus]\"\",\n" +
                    "[status] could only be \"offline\" or \"online\"");
            System.exit(0);
        }
    }


}
