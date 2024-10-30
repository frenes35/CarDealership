package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
        init();
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - List All Vehicles");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("99 - Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    processGetAllVehiclesRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest(scanner);
                    break;
                case 3:
                    processGetByYearRequest(scanner);
                    break;
                case 4:
                    processGetByColorRequest(scanner);
                    break;
                case 5:
                    processGetByMileageRequest(scanner);
                    break;
                case 99:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println("VIN: " + vehicle.getVin() + ", Make: " + vehicle.getMake() + ", Model: " + vehicle.getModel() + ", Price: " + vehicle.getPrice());
        }
    }

    private void processGetByMakeModelRequest(Scanner scanner) {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYearRequest(Scanner scanner) {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByColorRequest(Scanner scanner) {
        System.out.print("Enter color: ");
        String color = scanner.next();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }
    private void processGetByMileageRequest(Scanner scanner) {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }
}
