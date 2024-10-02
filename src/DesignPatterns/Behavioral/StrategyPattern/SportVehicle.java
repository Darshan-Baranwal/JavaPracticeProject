package DesignPatterns.Behavioral.StrategyPattern;

public class SportVehicle extends Vehicle{

    SportVehicle() {
        super(new SportDriveStrategy());
    }
}
