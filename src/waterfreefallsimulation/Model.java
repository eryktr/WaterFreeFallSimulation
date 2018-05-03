package waterfreefallsimulation;

public class Model
{

    public final double ANIMATION_FPS = 60;
    private final double WATER_DENSITY = 1000;
    private final double GRAVITATIONAL_ACCELERATION = 10;
    private final double waterDepth, initialHeight, mass, side, initialSpeed;
    private final double TIME_PERIOD = 1 / ANIMATION_FPS;
    private double currentAcceleration, currentSpeed, currentHeight, currentHeightSubmerged, currentLowestHeight, waterLevel;
    private double currentVolumeSubmerged;
    private double paneWidth, paneHeight;

    public Model(double waterDepth, double initialHeight, double mass, double side, double initialSpeed)
    {
      this.waterDepth = waterDepth;
      this.initialHeight = initialHeight;
      this.mass = mass;
      this.side = side;
      this.initialSpeed = initialSpeed;
      currentSpeed = initialSpeed;
      currentAcceleration = GRAVITATIONAL_ACCELERATION;
      currentHeight = initialHeight;
      currentLowestHeight = initialHeight + side / 2;
      currentHeightSubmerged = (currentLowestHeight <= waterLevel) ? (Math.min(waterLevel - currentLowestHeight, side)) : 0;
      System.out.println(currentHeightSubmerged);
      currentVolumeSubmerged = side * side * currentHeightSubmerged;
    }

    public void update()
    {
        currentSpeed = currentSpeed + currentAcceleration * TIME_PERIOD;
        currentHeight = currentHeight + currentSpeed * TIME_PERIOD;
        currentLowestHeight = currentHeight - side/2;
        currentHeightSubmerged =  (currentLowestHeight <= waterLevel) ? (Math.min(waterLevel - currentLowestHeight, side)) : 0;
        currentVolumeSubmerged = side * side * currentHeightSubmerged;
        currentAcceleration = -GRAVITATIONAL_ACCELERATION + (WATER_DENSITY * GRAVITATIONAL_ACCELERATION * currentVolumeSubmerged) / mass;
        System.out.printf("Current lowest height: %f; current height submerged: %f; water level: %f; ACCELERATION: %f", currentLowestHeight, currentHeightSubmerged, waterLevel, currentAcceleration);

    }

    public double getWaterDepth()
    {
        return waterDepth;
    }

    public double getInitialHeight()
    {
        return initialHeight;
    }

    public double getInitialSpeed()
    {
        return initialSpeed;
    }

    public double getMass()
    {
        return mass;
    }

    public double getSide()
    {
        return side;
    }

    public double getCurrentHeight() {return currentHeight;}

    public void setPaneWidth(double paneWidth) {this.paneWidth = paneWidth; }

    public void setPaneHeight(double paneHeight) {this.paneHeight = paneHeight; }

    public void setWaterLevel(double waterLevel) {this.waterLevel = waterLevel; }

}
