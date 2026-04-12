import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        System.out.println("Room Allocation Processing");

        while (bookingQueue.hasPendingRequests()) {
            Reservation reservation = bookingQueue.getNextRequest();
            allocationService.allocateRoom(reservation, inventory);
        }
    }
}

class Reservation {

    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public boolean isAvailable(String roomType) {
        return roomAvailability.containsKey(roomType) && roomAvailability.get(roomType) > 0;
    }

    public void decrementRoom(String roomType) {
        roomAvailability.put(roomType, roomAvailability.get(roomType) - 1);
    }

    public int getAvailableCount(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }
}

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;
    private Map<String, Integer> roomCounters;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
        roomCounters = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        if (!inventory.isAvailable(roomType)) {
            System.out.println(
                    "No rooms available for Guest: " + reservation.getGuestName() + ", Room Type: " + roomType);
            return;
        }

        String roomId = generateRoomId(roomType);

        reservation.setRoomId(roomId);
        allocatedRoomIds.add(roomId);
        assignedRoomsByType.computeIfAbsent(roomType, key -> new HashSet<>()).add(roomId);
        inventory.decrementRoom(roomType);

        System.out.println("Booking confirmed for Guest: " + reservation.getGuestName()
                + ", Room ID: " + reservation.getRoomId());
    }

    private String generateRoomId(String roomType) {

        int nextNumber = roomCounters.getOrDefault(roomType, 0) + 1;
        String roomId = roomType + "-" + nextNumber;

        while (allocatedRoomIds.contains(roomId)) {
            nextNumber++;
            roomId = roomType + "-" + nextNumber;
        }

        roomCounters.put(roomType, nextNumber);
        return roomId;
    }
}
