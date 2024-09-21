package com.example.connectedvehiclemanagementsystem.dto;

public class ConnectedVehicleResponse {

    // Response for total distance traveled by a vehicle
    public static class VehicleDistanceResponse {
        private String make;
        private String model;
        private String ownerName;
        private double totalDistance;
    }

    // Response for sensor anomalies
    public static class SensorAnomalyResponse {
        private String vehicleId;
        private String sensorType;
        private double sensorValue;
        private String timestamp;
    }

    // Response for maintenance history
    public static class MaintenanceResponse {
        private String maintenanceType;
        private String maintenanceDate;
        private double maintenanceCost;
    }

    // Response for frequent trips
    public static class FrequentTripResponse {
        private String vehicleId;
        private String make;
        private String model;
        private int tripCount;
    }
}
