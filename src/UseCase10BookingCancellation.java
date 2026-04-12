import java.util.*;

class CancellationService {
    private Map<String, Integer> inventory;
    private Map<String, String> reservations;
    private Stack<String> rollbackStack;

    public CancellationService() {
        inventory = new HashMap<>();
        reservations = new HashMap<>();
        rollbackStack = new Stack<>();

        inventory.put("Single", 5);

        reservations.put("Single-1", "Single");
    }

    public void cancelBooking(String reservationId) {
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Invalid cancellation request.");
            return;
        }

        String roomType = reservations.get(reservationId);

        rollbackStack.push(reservationId);

        inventory.put(roomType, inventory.get(roomType) + 1);

        reservations.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void printRollbackHistory() {
        System.out.println();
        System.out.println("Rollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }
    }

    public void printInventory(String roomType) {
        System.out.println();
        System.out.println("Updated " + roomType + " Room Availability: " + inventory.get(roomType));
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");

        CancellationService service = new CancellationService();

        String reservationId = "Single-1";

        service.cancelBooking(reservationId);
        service.printRollbackHistory();
        service.printInventory("Single");
    }
}