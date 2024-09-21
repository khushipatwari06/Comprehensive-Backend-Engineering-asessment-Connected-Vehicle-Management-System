package com.example.connectedvehiclemanagementsystem.service;

import com.example.connectedvehiclemanagementsystem.dto.ConnectedVehicleResponse.*;
import com.example.connectedvehiclemanagementsystem.entity.*;
import com.example.connectedvehiclemanagementsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectedVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    // API 1: Retrieve Total Distance Traveled by Each Vehicle in the Last 30 Days
    public List<VehicleDistanceResponse> getTotalDistanceTraveled(String vehicleId) {
        Integer id = Integer.parseInt(vehicleId);
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        Double totalDistance = tripRepository.findTotalDistanceTraveled(id, thirtyDaysAgo);

        Vehicle vehicle = vehicleRepository.findByVehicleId(id);
        return List.of(new VehicleDistanceResponse(vehicle.getMake(), vehicle.getModel(), vehicle.getOwner().getName(),
                totalDistance));
    }

    // API 2: Detect Sensor Anomalies
    public List<SensorAnomalyResponse> detectSensorAnomalies(String vehicleId) {
        Integer id = Integer.parseInt(vehicleId);
        List<Sensor> anomalies = sensorRepository.findAnomalies(id);

        List<SensorAnomalyResponse> response = new ArrayList<>();
        for (Sensor sensor : anomalies) {
            response.add(new SensorAnomalyResponse(vehicleId, sensor.getSensorType(), sensor.getSensorReading(),
                    sensor.getTimestamp().toString()));
        }
        return response;
    }

    // API 3: Get Maintenance History for a Specific Vehicle
    public List<MaintenanceResponse> getMaintenanceHistory(String vehicleId) {
        Integer id = Integer.parseInt(vehicleId);
        List<Maintenance> maintenanceRecords = maintenanceRepository.findByVehicleId(id);

        List<MaintenanceResponse> response = new ArrayList<>();
        for (Maintenance maintenance : maintenanceRecords) {
            response.add(new MaintenanceResponse(maintenance.getMaintenanceType(),
                    maintenance.getMaintenanceDate().toString(), maintenance.getMaintenanceCost()));
        }
        return response;
    }

    // API 4: Find Vehicles with Frequent Trips in the Last 7 Days
    public List<FrequentTripResponse> getVehiclesWithFrequentTrips() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<Vehicle> vehicles = vehicleRepository.findAll();

        List<FrequentTripResponse> response = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            Long tripCount = tripRepository.countTrips(vehicle.getVehicleId(), sevenDaysAgo);
            if (tripCount > 5) {
                response.add(new FrequentTripResponse(vehicle.getVehicleId().toString(), vehicle.getMake(),
                        vehicle.getModel(), tripCount.intValue()));
            }
        }
        return response;
    }
}
