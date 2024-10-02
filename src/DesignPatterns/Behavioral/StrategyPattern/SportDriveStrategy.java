package DesignPatterns.Behavioral.StrategyPattern;

public class SportDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Sports Drive Strategy");
    }
}
