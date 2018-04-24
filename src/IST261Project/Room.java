package IST261Project;

public class Room {

    private int Room_ID;

    private int Building_BuildingID;
    
    private String Building_Name;

    private int Package_Package_ID;

    private int Room_Number;

    private int Room_Size;

    Room(int Room_ID, int Room_Size, int Package_Package_ID)
    {
        this.Room_ID = Room_ID;
        this.Room_Size = Room_Size;
        this.Package_Package_ID = Package_Package_ID;
    }

    public Room(String Building_Name, int Package_Package_ID, int Room_Number, int Room_Size) {
        this.Building_Name = Building_Name;
        this.Package_Package_ID = Package_Package_ID;
        this.Room_Number = Room_Number;
        this.Room_Size = Room_Size;
    }
    
    
    public String getBuilding_Name() {
        return Building_Name;
    }

    public void setBuilding_Name(String Building_Name) {
        this.Building_Name = Building_Name;
    }
    /**
     * @return the Room_ID
     */
    public int getRoom_ID() {
        return Room_ID;
    }

    /**
     * @param Room_ID the Room_ID to set
     */
    public void setRoom_ID(int Room_ID) {
        this.Room_ID = Room_ID;
    }

    /**
     * @return the Building_BuildingID
     */
    public int getBuilding_BuildingID() {
        return Building_BuildingID;
    }

    /**
     * @param Building_BuildingID the Building_BuildingID to set
     */
    public void setBuilding_BuildingID(int Building_BuildingID) {
        this.Building_BuildingID = Building_BuildingID;
    }

    /**
     * @return the Package_Package_ID
     */
    public int getPackage_Package_ID() {
        return Package_Package_ID;
    }

    /**
     * @param Package_Package_ID the Package_Package_ID to set
     */
    public void setPackage_Package_ID(int Package_Package_ID) {
        this.Package_Package_ID = Package_Package_ID;
    }

    /**
     * @return the Room_Number
     */
    public int getRoom_Number() {
        return Room_Number;
    }

    /**
     * @param Room_Number the Room_Number to set
     */
    public void setRoom_Number(int Room_Number) {
        this.Room_Number = Room_Number;
    }

    /**
     * @return the Room_Size
     */
    public int getRoom_Size() {
        return Room_Size;
    }

    /**
     * @param Room_Size the Room_Size to set
     */
    public void setRoom_Size(int Room_Size) {
        this.Room_Size = Room_Size;
    }
    
}
