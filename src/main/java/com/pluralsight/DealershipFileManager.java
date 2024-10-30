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
                    Vehicle vehicle = new Vehicle(
                            Integer.parseInt(vehicleParts[0]),
                            Integer.parseInt(vehicleParts[1]),
                            vehicleParts[2],
                            vehicleParts[3],
                            vehicleParts[4],
                            vehicleParts[5],
                            Integer.parseInt(vehicleParts[6]),
                            Double.parseDouble(vehicleParts[7])
                    );
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}

