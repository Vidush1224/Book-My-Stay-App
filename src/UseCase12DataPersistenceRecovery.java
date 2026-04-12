import java.io.*;
import java.util.*;

class PersistenceService {
    private static final String FILE_NAME = "inventory.dat";

    public Map<String, Integer> loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, Integer>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return getDefaultInventory();
        }
    }

    public void saveInventory(Map<String, Integer> inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Integer> getDefaultInventory() {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
        return inventory;
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("System Recovery");

        PersistenceService service = new PersistenceService();

        Map<String, Integer> inventory = service.loadInventory();

        System.out.println();
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));

        service.saveInventory(inventory);
    }
}