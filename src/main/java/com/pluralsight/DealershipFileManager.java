package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    public Dealership getDealership() {

        Dealership dealership = null;
        try (BufferedReader br = new BufferedReader(new FileReader("dealership.csv"))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                dealership = new Dealership(parts[0], parts[1], parts[2]);
                String vehicleLine;
                while ((vehicleLine = br.readLine()) != null) {
                    String[] vehicleParts = vehicleLine.split("\\|");
                    Vehicle vehicle = new Vehicle(Integer.parseInt(vehicleParts[0]), Integer.parseInt(vehicleParts[1]), vehicleParts[2], vehicleParts[3], vehicleParts[4], vehicleParts[5], Integer.parseInt(vehicleParts[6]), Double.parseDouble(vehicleParts[7]));
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dealership.csv" ))) {
            // Write dealership info
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bufferedWriter.newLine();

            // Write each vehicle
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bufferedWriter.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" + vehicle.getMake() +
                        "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() +
                        "|" + vehicle.getColor() + "|" + vehicle.getOdometer() +
                        "|" + vehicle.getPrice());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Dealership data saved successfully.");
        } catch (IOException e) {
            System.err.println("There was an error saving dealership data.");
            e.printStackTrace();
        }
    }

}

