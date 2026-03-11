import java.util.Map;
import java.util.HashMap;

public class UseCase3InventorySetup {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
    }
}

class Room {
    int beds;
    int size;
    double price;
    int availableRooms;

    public Room(int beds, int size, double price, int availableRooms) {
        this.beds = beds;
        this.size = size;
        this.price = price;
        this.availableRooms = availableRooms;
    }
}

class RoomInventory {

    private Map<String, Room> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {

        roomAvailability.put("Single Room", new Room(1, 250, 1500.0, 5));
        roomAvailability.put("Double Room", new Room(2, 400, 2500.0, 3));
        roomAvailability.put("Suite Room", new Room(3, 750, 5000.0, 2));
    }

    public void displayInventory() {

        System.out.println("Hotel Room Inventory Status\n");

        for (String roomType : roomAvailability.keySet()) {

            Room r = roomAvailability.get(roomType);

            System.out.println(roomType + ":");
            System.out.println("Beds: " + r.beds);
            System.out.println("Size: " + r.size + " sqft");
            System.out.println("Price per night: " + r.price);
            System.out.println("Available Rooms: " + r.availableRooms);
            System.out.println();
        }
    }
}