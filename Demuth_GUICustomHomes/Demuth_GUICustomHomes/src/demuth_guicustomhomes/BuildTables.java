
package demuth_guicustomhomes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BuildTables {
final String DB_URL = "jdbc:derby://localhost:1527/HomeOrders;create=true";
    
    public BuildTables()
    {
        try
        {
            //try this code
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);
            
            dropTables(conn);
            
            buildHomesTable(conn);
            conn.commit();
            
            //close the connection
            conn.close();
           
        }catch(Exception ex)
        {
           //if it fails do this
            System.out.println("Error: " + ex.getMessage());
        
        }
    }
    
    public static void dropTables(Connection conn)
    {
        System.out.println("Checking for existing tables");
        
        try
        {
            //get a statement object 
            Statement stmt = conn.createStatement();
            
            try
            {
                
                stmt.execute("DROP TABLE Homes ");
                System.out.println("Homes table dropped.");
            } catch(SQLException ex)
            {
                //No need to report anything here
                //The table did not exist      
            }
            
        }catch(SQLException ex)
        {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void buildHomesTable(Connection conn)
    {
        try
        {
            Statement stmt = conn.createStatement();
            
            stmt.execute("CREATE Table Homes (" +
                    "HomeID CHAR(7) NOT NULL PRIMARY KEY, " +
                    "FirstName CHAR(20), " +
                    "LastName CHAR(20), " +
                    "HomeType CHAR(15), " +
                    "HomePrice DOUBLE " + 
                    ")");
            
            System.out.println("created Homes Table");
            
            stmt.execute("INSERT INTO Homes Values ( " +
                         "'111-001', " +
                         "'Thomas', " +
                         "'Shelby', " +
                         "'Luxury', " +
                         "7999999)" );
            
            stmt.execute("INSERT INTO Homes Values ( " +
                         "'111-002', " +
                         "'Bruce', " +
                         "'Wayne', " +
                         "'Custom', " +
                         "1250000)" );
            
            stmt.execute("INSERT INTO Homes Values ( " +
                         "'111-003', " +
                         "'Arthur', " +
                         "'Apple', " +
                         "'Standard', " +
                         "175000)" );
            
            stmt.execute("INSERT INTO Homes Values ( " +
                         "'111-004', " +
                         "'Peter', " +
                         "'Piper', " +
                         "'Luxury', " +
                         "550000)" );
            System.out.println("inserted Homes data");  
        }catch(SQLException ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}




