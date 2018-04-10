/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.TreeModel;

/**
 *
 * @author whb108
 *
 * Modified Class Template
 */
public abstract class DBConnector 
{

    Connection myConnection;

    /**
     * DBConnector provides the base (abstract) class as a common framework
     * for connecting to a database and some standard functions such as
     * getting meta data about the Connection, listings of all of the
     * databases, tables in the databases and ResultSet meta data.
     */
    public DBConnector() 
    {

    } // constructor   
//    
//    public DBConnector(String strDB) throws ClassNotFoundException, SQLException
//    {
//        connectToDatabase(strDB);
//    }

    /**
     *
     * @param strInDatabaseName
     * @return java.sql.Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public abstract void connectToDatabase(String strInDatabaseName)
            throws ClassNotFoundException, SQLException;
    
    public abstract void connectToDatabase()
            throws ClassNotFoundException, SQLException;
    
    public abstract TreeModel getDatabaseAndTableTreeModel() 
               throws SQLException, ClassNotFoundException, SQLException;
    
    public Connection getConnection()
    {
        return myConnection;
    }

    /**
     *
     * @param myC
     * @return String containing DB product and version
     * @throws SQLException
     */
    public String getDatabaseMetaData() throws SQLException {
        StringBuilder sbDBMD = new StringBuilder();
        DatabaseMetaData myDMD = myConnection.getMetaData();
        sbDBMD.append("Database Product:  " + myDMD.getDatabaseProductName() + "\n");
        sbDBMD.append("Version:  " + myDMD.getDatabaseMajorVersion()
                + "." + myDMD.getDatabaseMinorVersion());
        
        return sbDBMD.toString();

    } // getDatabaseMetaData

    /**
     *
     * @param rsIn
     * @return
     * @throws SQLException
     */
    public String getRSMetaData(ResultSet rsIn) throws SQLException {
        StringBuilder sbRSMD = new StringBuilder();
        ResultSetMetaData rsmd = rsIn.getMetaData();

        int intColCount = rsmd.getColumnCount();

        for (int intLCV = 1; intLCV <= intColCount; intLCV++) {
            sbRSMD.append("Column " + intLCV + "\n");
            sbRSMD.append("  Label: " + rsmd.getColumnLabel(intLCV) + "\n");
            sbRSMD.append("  Name:  " + rsmd.getColumnName(intLCV) + "\n");
            sbRSMD.append("  Type:  " + rsmd.getColumnTypeName(intLCV) + "\n");
            sbRSMD.append("\n\n");
        } // for

        return sbRSMD.toString();
    } // displayRSMetaData
    
    
    public String getRSData(ResultSet rsIn) throws SQLException
    {
        StringBuilder mySB = new StringBuilder();
        ResultSetMetaData rsmd = rsIn.getMetaData();
        int intColCount = rsmd.getColumnCount();
        
        // get the column labels
        for(int intLCV = 1; intLCV <= intColCount; intLCV++)
        {
            mySB.append(rsmd.getColumnLabel(intLCV) + "\t");
        } // for
        mySB.append("\n\n");
        
       
        while(rsIn.next())
        {
            for(int intCol = 1; intCol < intColCount; intCol++)
            {
              mySB.append(rsIn.getString(intCol)+ "\t");
                   } // for
            mySB.append("\n");
        } // while
        return mySB.toString();
    } // getRSData
    
    public void closeConnection()
    {
        try 
        {
            myConnection.close();
        } 
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error - " + ex.getLocalizedMessage(), "Error Closing Connection",  JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // closeConnection
    

    /**
     *
     * @param cIn
     * @param strWild
     * @return
     * @throws SQLException
     */
    public abstract String getTableNames() throws SQLException ;

        public String getServerInfo() throws SQLException
    {
       StringBuilder sbTemp = new StringBuilder();
       DatabaseMetaData myDMD = myConnection.getMetaData();
       
       sbTemp.append(myDMD.getDatabaseProductName());
       sbTemp.append(" - " + myDMD.getURL());
       return sbTemp.toString();
    } // getServerInfo
} // class
