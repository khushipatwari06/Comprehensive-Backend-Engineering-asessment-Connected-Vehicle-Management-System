package com.example.connectedvehiclemanagementsystem.controller;

import com.example.connectedvehiclemanagementsystem.ConnectedVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class ConnectedVehicleManagementSystem {

    @Autowired
    private ConnectedVehicleService connectedVehicleService;

    public ConnectedVehicleManagementSystem() {
    }

    // API 1: Retrieve Total Distance Traveled by Each Vehicle in the Last 30 Days
    @GetMapping("/distance_traveled")
    public VehicleDistanceResponse getTotalDistanceTraveled(@RequestParam String vehicleId) {
        validateVehicleId(vehicleId);
        return connectedVehicleService.getTotalDistanceTraveled(vehicleId);
    }

    // API 2: Detect Sensor Anomalies
    @GetMapping("/sensor_anomalies")
    public List<SensorAnomalyResponse> detectSensorAnomalies(@RequestParam String vehicleId) {
        validateVehicleId(vehicleId);
        return connectedVehicleService.detectSensorAnomalies(vehicleId);
    }

    // API 3: Get Maintenance History for a Specific Vehicle
    @GetMapping("/{vehicleId}/maintenance-history")
    public List<MaintenanceResponse> getMaintenanceHistory(@PathVariable String vehicleId) {
        validateVehicleId(vehicleId);
        return connectedVehicleService.getMaintenanceHistory(vehicleId);
    }

    // API 4: Find Vehicles with Frequent Trips in the Last 7 Days
    @GetMapping("/frequent-trips")
    public List<FrequentTripResponse> getVehiclesWithFrequentTrips() {
        return connectedVehicleService.getVehiclesWithFrequentTrips();
    }

    // Validation method for Vehicle ID
    private void validateVehicleId(String vehicleId) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
    }
}
