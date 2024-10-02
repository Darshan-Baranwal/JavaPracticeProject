package DesignPatterns.Behavioral.StrategyPattern;

public class PaxVehicle extends Vehicle{
    PaxVehicle() {
        super(new NormalDriveStrategy());
    }
}
