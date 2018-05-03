package waterfreefallsimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller
{
    @FXML
    private TextField waterDepthTextField, initialHeightTextField,
            massTextField, sideTextField, initialSpeedTextField;
    @FXML
    private Button startButton;

    private static Model model;
    private PopupController popupController;


    public void startSimulation()
    {
        double waterDepth, initialHeight, mass, side, initialSpeed;
        waterDepth = Utility.textFieldToDouble(waterDepthTextField);
        initialHeight = Utility.textFieldToDouble(initialHeightTextField);
        mass = Utility.textFieldToDouble(massTextField);
        side = Utility.textFieldToDouble(sideTextField);
        initialSpeed = Utility.textFieldToDouble(initialSpeedTextField);
        model = new Model(waterDepth, initialHeight, mass, side, initialSpeed);
    }

    public Button getStartButton()
    {
        return startButton;
    }

    public void setPopupController(PopupController popupController)
    {
        this.popupController = popupController;
    }

    public static Model getModel()
    {
        return model;
    }
}
