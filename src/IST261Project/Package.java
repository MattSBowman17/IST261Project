package IST261Project;

import java.util.Scanner;

public class Package {

    private int Package_ID;

    private boolean Package_Computers;

    private boolean Package_WhiteBoard;

    private boolean Package_Projector;

    private int Package_Major;
     
   //Package Constructor
    public Package(int Package_ID, int Package_Major){
        this.Package_ID = Package_ID;
        this.Package_Major = Package_Major;
    }

    /**
     * @return the Package_ID
     */
    public int getPackage_ID() {
        return Package_ID;
    }

    /**
     * @param Package_ID the Package_ID to set
     */
    public void setPackage_ID(int Package_ID) {
        this.Package_ID = Package_ID;
    }

    /**
     * @return the Package_Computers
     */
    public boolean isPackage_Computers() {
        return Package_Computers;
    }

    /**
     * @param Package_Computers the Package_Computers to set
     */
    public void setPackage_Computers(boolean Package_Computers) {
        this.Package_Computers = Package_Computers;
    }

    /**
     * @return the Package_WhiteBoard
     */
    public boolean isPackage_WhiteBoard() {
        return Package_WhiteBoard;
    }

    /**
     * @param Package_WhiteBoard the Package_WhiteBoard to set
     */
    public void setPackage_WhiteBoard(boolean Package_WhiteBoard) {
        this.Package_WhiteBoard = Package_WhiteBoard;
    }

    /**
     * @return the Package_Projector
     */
    public boolean isPackage_Projector() {
        return Package_Projector;
    }

    /**
     * @param Package_Projector the Package_Projector to set
     */
    public void setPackage_Projector(boolean Package_Projector) {
        this.Package_Projector = Package_Projector;
    }

    /**
     * @return the Package_Major
     */
    public int getPackage_Major() {
        return Package_Major;
    }

    /**
     * @param Package_Major the Package_Major to set
     */
    public void setPackage_Major(int Package_Major) {
        this.Package_Major = Package_Major;
    }
    
}
