/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IST261Project;

import java.sql.*;

import java.util.logging.*;

import javax.swing.*;
import javax.swing.tree.*;

/**
 *
 * @author whb108
*
* Modified Class Template
 */

/*****************   MODIFICATION LOG ***************************

****************************************************************/
public class MySQLDBConnector extends DBConnector
{
    public final String strSQLGetDatabaseNames = "SELECT SCHEMA_NAME as DatabaseName FROM INFORMATION_SCHEMA.SCHEMATA order by SCHEMA_NAME";

    String strHost;
    String strPort;
    String strUser;
    String strPassword;
    String strDB;        
    
    
    // Adapted from https://stackoverflow.com/questions/789517/java-how-to-create-a-custom-dialog-box
    public void getCredentials()
    {
        JTextField jtfHost = new JTextField();
        JTextField jtfPort = new JTextField();
        JTextField jtfUser = new JTextField();
        JPasswordField jpfPassword = new JPasswordField();
         JTextField jtfDB = new JTextField();
        final JComponent[] inputs = new JComponent[] 
        {
           new JLabel("Host URL"),
           jtfHost,
           new JLabel("Port"),
           jtfPort,
           new JLabel("User"),
           jtfUser,
           new JLabel("Password"),
           jpfPassword,
           new JLabel("Database"),
           jtfDB
        };
        
int result = JOptionPane.showConfirmDialog(null, inputs, "MySQL Connection Information", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) 
{
   strHost = jtfHost.getText();
   strPort = jtfPort.getText();
   strUser = jtfUser.getText();
   strPassword = new String(jpfPassword.getPassword());
   strDB = jtfDB.getText();
    System.out.println("You entered \n" 
            + "Host  "  + strHost + "\n"
            + "Port  "  + strPort + "\n"
            + "User  "  +  strUser + "\n"
            + "PW    "  + strPassword + "\n"
            + "DB    "  + strDB + "\n"
    );
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
    } // getCredentials
     
    
    
    public void connectToDatabase() throws ClassNotFoundException, SQLException
    {
        getCredentials();
        connectToDatabase(strHost, strPort, strUser, strPassword, strDB);
        
    }
    
    

    public void connectToDatabase(String strInHost, String strInPort, 
            String strInUser, String strInPW, String strInDB) 
       throws ClassNotFoundException, SQLException 
    {
      StringBuilder sbConnection = new StringBuilder("jdbc:mysql://" + strInHost);
      if(strInPort.length() > 0)
         sbConnection.append(":" + strInPort);
      
      sbConnection.append("/");
      
      if(strInDB.length() > 0)
         sbConnection.append("" + strInDB);
      
      if(strInUser.length() > 0)
        sbConnection.append("?user=" + strInUser);
      
      if(strInPW.length() > 0)
        sbConnection.append("&password=" + strInPW);

      //JOptionPane.showMessageDialog(null, "Connection string = " + sbConnection.toString());

     /*
        parameters - 
           replace localhost with server URL and port number if other
              than the default of 3306 - "localhost:4300"
           replace test with database name
           replace name and PW as appropriate
        
           the only required one is server name or URL
          
        */
    //  myC = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
    //                               "user=minty&password=greatsqldb");
        myConnection = DriverManager.getConnection(sbConnection.toString());
      
       
        
    } // connectToDatabase

    
    
    @Override
    public void connectToDatabase(String strInDatabaseName) 
       throws ClassNotFoundException, SQLException 
    {
      String strConnection = "jdbc:mysql://" + strInDatabaseName
         + "/information_schema?user=ky733&password=berks6599";
       

     /*
        parameters - 
           replace localhost with server URL and port number if other
              than the default of 3306 - "localhost:4300"
           replace test with database name
           replace name and PW as appropriate
        
           the only required one is server name or URL
          
        */
    //  myC = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
    //                               "user=minty&password=greatsqldb");
        myConnection = DriverManager.getConnection(strConnection);
      
       
        
    } // connectToDatabase

        @Override
    public String getTableNames() throws SQLException 
    {
        Statement myS = myConnection.createStatement();
        ResultSet myRS = myS.executeQuery("SELECT * FROM information_schema.tables order by table_schema, table_name");
        String strTables = getRSData(myRS);
        return strTables;
    } // getTableNames
    
    
public String getDatabaseNames() throws SQLException
{
   Statement myS = myConnection.createStatement();
   ResultSet myRS = myS.executeQuery(strSQLGetDatabaseNames);
   String strDatabases = getRSData(myRS);
   return strDatabases;

} // getDatabases

public ResultSet getDatabaseNamesResultSet() throws SQLException
{
   Statement myS = myConnection.createStatement();
   ResultSet myRS = myS.executeQuery(strSQLGetDatabaseNames);
   return myRS;

} // getDatabases



public TreeModel getDatabaseAndTableTreeModel() 
   throws SQLException, ClassNotFoundException, SQLException
{
   ResultSet rsDB = getDatabaseNamesResultSet();
     
   PreparedStatement psGetTables = myConnection.prepareStatement("SELECT table_name FROM information_schema.tables "
      + " where table_schema = ? order by table_name");
   
   String strServerInfo = getServerInfo();
   DefaultMutableTreeNode root = new DefaultMutableTreeNode(strServerInfo);
           
   while(rsDB.next())
   {
      String strDB = rsDB.getString(1);
      DefaultMutableTreeNode nDB = new DefaultMutableTreeNode(strDB);
      root.add(nDB);
      
      // Get the tables for this database
      psGetTables.setString(1,strDB);
      ResultSet rsTables = psGetTables.executeQuery();
      while(rsTables.next())
      {
         DefaultMutableTreeNode nT = new DefaultMutableTreeNode(rsTables.getString(1));
         nDB.add(nT);
      } // while there are more tables
   } // while there are more databases
   
   DefaultTreeModel treeModel = new DefaultTreeModel(root);
   return treeModel;
       

        

} // getDatabaseAndTableTreeModel

    public static void main(String[] args) 
    {
        try {
            MySQLDBConnector myS = new MySQLDBConnector();
           
     

      
           myS.connectToDatabase("istdata.bk.psu.edu","3306","kds5314","berks6599","ctg5117");
            
            String strDBMD = myS.getDatabaseMetaData();
        System.out.println("Database MetaData");
        System.out.println(strDBMD);
        
        String strTableNames = myS.getTableNames();
        System.out.println("Table Names");
        System.out.println(strTableNames);
      
        
        myS.myConnection.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    
} // end of class MySQLDBConnector

