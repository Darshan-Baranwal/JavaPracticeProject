package DesignPatterns.Behavioral.StrategyPattern;

public class Main {

    public static void main(String[] args) {
        SportVehicle sport = new SportVehicle();
        sport.drive();
        PaxVehicle paxVehicle = new PaxVehicle();
        paxVehicle.drive();
    }
}
