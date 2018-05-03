package waterfreefallsimulation;

import javafx.scene.control.TextField;

public class Utility
{
    public static double textFieldToDouble(TextField field)
    {
        return Double.parseDouble(field.getText());
    }
}
