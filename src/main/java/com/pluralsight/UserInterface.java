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
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List All Vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("99 - Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    processGetByPriceRequest(scanner);
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
                case 6:
                    processGetByVehicleTypeRequest(scanner);
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest(scanner);
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
    private void processGetByVehicleTypeRequest(Scanner scanner) {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.next();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    private void processGetByPriceRequest(Scanner scanner) {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest(Scanner scanner) {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.next();
        System.out.print("Enter color: ");
        String color = scanner.next();
        System.out.print("Enter mileage: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
    }
}
