public class UseCase2RoomInitialization{
    public static void main(String[] args){

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("Hotel Room Initialization");
        System.out.println();
        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        singleRoom.setAvailable(5);
        singleRoom.getAvailable();

        System.out.println();
        System.out.println("Double Room:");
        singleRoom.displayRoomDetails();
        singleRoom.setAvailable(3);
        singleRoom.getAvailable();

        System.out.println();
        System.out.println("Suite Room:");
        singleRoom.displayRoomDetails();
        singleRoom.setAvailable(2);
        singleRoom.getAvailable();
    }
}

abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    protected int available;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight){
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
        this.squareFeet = squareFeet;
    }

    public void displayRoomDetails(){
        System.out.println("Beds: "+numberOfBeds);
        System.out.println("Size: "+squareFeet);
        System.out.println("Price per Night: "+pricePerNight);
    }

    public void setAvailable(int val) {
        this.available = val;
    }
    public void getAvailable(){
        System.out.println("Available: "+available);
    }
}

class SingleRoom extends Room{
    public SingleRoom(){super(1,250,1500.0);}
}
class DoubleRoom extends Room{
    public DoubleRoom(){super(2,400,2500.0);}
}
class SuiteRoom extends Room{
    public SuiteRoom(){super(3,750,5000.0);}
}

