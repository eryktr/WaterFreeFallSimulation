package waterfreefallsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    private Controller controllerInstance;
    private PopupController popupController;


    @Override
    public synchronized void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = loader.load();
        controllerInstance = loader.getController();
        primaryStage.setTitle("Water Free Fall Simulator");
        controllerInstance.getStartButton().setOnAction(event ->
        {

            try
            {
                controllerInstance.startSimulation();
                final Stage popup = new Stage();
                popup.initOwner(primaryStage);
                popup.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader popupLoader = new FXMLLoader(getClass().getResource("popup.fxml"));

                System.out.println(controllerInstance.getModel() == null);
                Parent popupRoot = popupLoader.load();
                popupController = popupLoader.getController();
                Scene popupScene = new Scene(popupRoot);
                popup.setScene(popupScene);
                popup.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
