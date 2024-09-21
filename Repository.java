package in.zeta.bundles.fusion.controller.v3;

public class Repository {
    // VehicleRepository.java
    @Repository
    public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
        @Query("SELECT v FROM Vehicle v JOIN v.owner o WHERE v.vehicleId = :vehicleId")
        Vehicle findByVehicleId(@Param("vehicleId") Integer vehicleId);
    }

    // TripRepository.java
    @Repository
    public interface TripRepository extends JpaRepository<Trip, Integer> {
        @Query("SELECT SUM(t.distanceTraveled) FROM Trip t WHERE t.vehicleId = :vehicleId AND t.startTime >= :startDate")
        Double findTotalDistanceTraveled(@Param("vehicleId") Integer vehicleId,
                @Param("startDate") LocalDateTime startDate);

        @Query("SELECT COUNT(t) FROM Trip t WHERE t.vehicleId = :vehicleId AND t.startTime >= :startDate")
        Long countTrips(@Param("vehicleId") Integer vehicleId, @Param("startDate") LocalDateTime startDate);
    }

    // SensorRepository.java
    @Repository
    public interface SensorRepository extends JpaRepository<Sensor, Integer> {
        @Query("SELECT s FROM Sensor s WHERE s.vehicleId = :vehicleId AND (s.sensorReading > 120 OR s.sensorReading < 10)")
        List<Sensor> findAnomalies(@Param("vehicleId") Integer vehicleId);
    }

    // MaintenanceRepository.java
    @Repository
    public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
        List<Maintenance> findByVehicleId(Integer vehicleId);
    }

}
