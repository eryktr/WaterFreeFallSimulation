package waterfreefallsimulation;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PopupController
{
    private Model model;
    private Controller controllerInstance;

    @FXML
    private Rectangle waterRectangle, object;
    @FXML
    private Pane simulationPane;


    private double paneWidth, paneHeight;

    private class Timer extends Thread
    {
        public synchronized void run()
        {

            while(true)
            {
                try
                {
                    wait(100);
                    runSimulation();
                    System.out.println("I work");

                }
                catch (InterruptedException ex)
                {

                }
            }
        }
    }

    public void initialize()
    {
        model = Controller.getModel();
        updateModelData();
        setUpPane();
        initializeWater();
        initializeObject();
        Timer timer = new Timer();
        timer.start();
    }

    public void setControllerInstance(Controller controllerInstance)
    {
        this.controllerInstance = controllerInstance;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setUpPane()
    {
        simulationPane.setPrefHeight(1.1 * (model.getWaterDepth() + model.getInitialHeight() + model.getSide()));
    }
    public void initializeWater()
    {
        waterRectangle.setHeight(model.getWaterDepth());
        waterRectangle.setY(simulationPane.getPrefHeight() - model.getWaterDepth());
    }

    public void initializeObject()
    {
        object.setWidth(model.getSide());
        object.setHeight(model.getSide());
        object.setX(waterRectangle.getWidth() / 2 - object.getWidth()/2);
        object.setY(simulationPane.getPrefHeight() - model.getWaterDepth() - model.getInitialHeight() - object.getHeight() /2);
    }

    public void updateModelData()
    {
        model.setPaneWidth(simulationPane.getPrefWidth());
        model.setPaneHeight(simulationPane.getPrefHeight());
        model.setWaterLevel(waterRectangle.getY());
    }
    public void runSimulation()
    {
        model.update();
        Platform.runLater(() -> updateSimulation());
    }

    public void updateSimulation()
    {
        object.setY(simulationPane.getPrefHeight() - model.getWaterDepth() -model.getCurrentHeight() - model.getSide() / 2);
    }



}
