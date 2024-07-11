interface Vehicle {
    String getId();
    String getModel();
    int getCapacity();
}

interface TransportationOperations {
    void boardPassenger(String passengerId);
    void alightPassenger(String passengerId);
}

class Bus implements Vehicle, TransportationOperations {
    private String id;
    private String model;
    private int capacity;
    private String[] passengers;
    private int passengerCount;

    public Bus(String id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.passengers = new String[capacity];
        this.passengerCount = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void boardPassenger(String passengerId) {
        if (passengerCount < capacity) {
            passengers[passengerCount++] = passengerId;
            System.out.println(passengerId + " boarded the bus.");
        } else {
            System.out.println("Bus is full. " + passengerId + " cannot board.");
        }
    }

    @Override
    public void alightPassenger(String passengerId) {
        boolean found = false;
        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i].equals(passengerId)) {
                passengers[i] = passengers[passengerCount - 1];
                passengers[--passengerCount] = null;
                found = true;
                System.out.println(passengerId + " alighted from the bus.");
                break;
            }
        }
        if (!found) {
            System.out.println(passengerId + " was not found on the bus.");
        }
    }

    public void listPassengers() {
        System.out.print("Passengers on the bus: ");
        for (int i = 0; i < passengerCount; i++) {
            System.out.print(passengers[i] + " ");
        }
        System.out.println();
    }
}

abstract class Person {
    private String id;
    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void boardBus(Bus bus);

    public abstract void alightBus(Bus bus);
}

class Passenger extends Person {

    public Passenger(String id, String name) {
        super(id, name);
    }

    @Override
    public void boardBus(Bus bus) {
        bus.boardPassenger(getId());
    }

    @Override
    public void alightBus(Bus bus) {
        bus.alightPassenger(getId());
    }
}

class Driver extends Person {

    public Driver(String id, String name) {
        super(id, name);
    }

    @Override
    public void boardBus(Bus bus) {
        System.out.println(getName() + " is driving the bus " + bus.getModel());
    }

    @Override
    public void alightBus(Bus bus) {
        System.out.println(getName() + " stopped driving the bus " + bus.getModel());
    }

    public void driveBus(Bus bus) {
        System.out.println(getName() + " is driving the bus " + bus.getModel());
    }

    public void stopBus(Bus bus) {
        System.out.println(getName() + " stopped the bus " + bus.getModel());
    }
}

public class BusManagementSystem {
    public static void main(String[] args) {
        Bus bus = new Bus("B1", "Volvo 9700", 3);
        Driver driver = new Driver("D1", "Alice");
        Passenger passenger1 = new Passenger("P1", "Bob");
        Passenger passenger2 = new Passenger("P2", "Charlie");
        Passenger passenger3 = new Passenger("P3", "Dave");

        driver.driveBus(bus);

        passenger1.boardBus(bus);
        passenger2.boardBus(bus);
        passenger3.boardBus(bus);
        bus.listPassengers();

        passenger1.alightBus(bus);
        bus.listPassengers();

        driver.stopBus(bus);
    }
}
