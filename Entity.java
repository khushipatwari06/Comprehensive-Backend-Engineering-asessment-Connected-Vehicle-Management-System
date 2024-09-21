public class Entity {
    // Vehicle.java
    @Entity
    public class Vehicle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer vehicleId;
        private String make;
        private String model;
        private Integer year;
        private String fuelType;

        @ManyToOne
        @JoinColumn(name = "owner_id")
        private Owner owner;

        // Getters and Setters
    }

    // Owner.java
    @Entity
    public class Owner {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ownerId;
        private String name;
        private String contactInfo;

        // Getters and Setters
    }

    // Trip.java
    @Entity
    public class Trip {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer tripId;
        private Integer vehicleId;
        private LocalDateTime startTime;
        private String startLocation;
        private String endLocation;
        private Double distanceTraveled;

        // Getters and Setters
    }

    // Sensor.java
    @Entity
    public class Sensor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer sensorId;
        private Integer vehicleId;
        private String sensorType;
        private Double sensorReading;
        private LocalDateTime timestamp;

        // Getters and Setters
    }

    // Maintenance.java
    @Entity
    public class Maintenance {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer maintenanceId;
        private Integer vehicleId;
        private String maintenanceType;
        private LocalDate maintenanceDate;
        private Double maintenanceCost;

        // Getters and Setters
    }

}
