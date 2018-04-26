package IST261Project;

public class RoomTime {

    private int RoomTimeID;

    private int Time_Time_ID;

    private int Room_idRoom;

    
    //RoomeTime Constructor
    public RoomTime(int RoomTimeID, int Time_Time_ID, int Room_idRoom)
    {
        this.RoomTimeID = RoomTimeID;
        this.Time_Time_ID = Time_Time_ID;
        this.Room_idRoom = Room_idRoom;
 
    }

    /**
     * @return the Time_Time_ID
     */
    public int getTime_Time_ID() {
        return Time_Time_ID;
    }

    /**
     * @param Time_Time_ID the Time_Time_ID to set
     */
    public void setTime_Time_ID(int Time_Time_ID) {
        this.Time_Time_ID = Time_Time_ID;
    }

    /**
     * @return the Room_idRoom
     */
    public int getRoom_idRoom() {
        return Room_idRoom;
    }

    /**
     * @param Room_idRoom the Room_idRoom to set
     */
    public void setRoom_idRoom(int Room_idRoom) {
        this.Room_idRoom = Room_idRoom;
    }

    /**
     * @return the RoomTimeID
     */
    public int getRoomTimeID() {
        return RoomTimeID;
    }

    /**
     * @param RoomTimeID the RoomTimeID to set
     */
    public void setRoomTimeID(int RoomTimeID) {
        this.RoomTimeID = RoomTimeID;
    }
    
}
