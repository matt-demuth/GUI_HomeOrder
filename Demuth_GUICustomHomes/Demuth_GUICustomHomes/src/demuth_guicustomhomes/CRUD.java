
package demuth_guicustomhomes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class CRUD {
    final String DB_URL = "jdbc:derby://localhost:1527/HomeOrders;create=true";
    String SQLString;
    Connection conn;
    public PreparedStatement pstmt; 
    public Statement stmt; 
    public ResultSet rs;
    HomeOrderForm gui;
    
    public CRUD(HomeOrderForm gui)
    {
        System.out.println("In constructor");
        try {
          conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.conn != null)
        {
           System.out.println("***Connected To Database***");
        }
        this.gui = gui;      //assign gui object 
    }
   
    public void showTable()
    {
        ArrayList<Home> Homes = getHomes();
        
        DefaultTableModel model = (DefaultTableModel) gui.getJTable().getModel();   //
        String[] titles = new String[]{"Home ID Number", "First Name", "Last Name", "Type", "Price"};
        Object[][] myHomeArray = new Object[Homes.size()][5];     
        
        for(int i = 0; i < Homes.size(); i++)
        {
            myHomeArray[i][0] = Homes.get(i).getHomeID();    //get item from arraylist
            System.out.println(Homes.get(i).getHomeID());
            myHomeArray[i][1] = Homes.get(i).getHomeFirstName();
            System.out.println(Homes.get(i).getHomeFirstName());
            myHomeArray[i][2] = Homes.get(i).getHomeLastName();
            System.out.println(Homes.get(i).getHomeLastName());
            myHomeArray[i][3] = Homes.get(i).getHomeType();
            System.out.println(Homes.get(i).getHomeType());
            myHomeArray[i][4] = Homes.get(i).getHomePrice();
            System.out.println(Homes.get(i).getHomePrice());
        }
        
        model.setDataVector(myHomeArray, titles);
 
    }
            
    public void showRecord(Home obj)
    {
        ArrayList<Home> Homes = selectSpecificRecord(obj);
        
        DefaultTableModel model = (DefaultTableModel) gui.getJTable().getModel();   //
        String[] titles = new String[]{"Home ID Number", "First Name", "Last Name", "Type", "Price"};
        Object[][] myHomeArray = new Object[Homes.size()][5];        //num rows, num columns on coffee table
        
        for(int i = 0; i < Homes.size(); i++)
        {
            myHomeArray[i][0] = Homes.get(i).getHomeID();    //get item from arraylist
            System.out.println(Homes.get(i).getHomeID());
            myHomeArray[i][1] = Homes.get(i).getHomeFirstName();
            System.out.println(Homes.get(i).getHomeFirstName());
            myHomeArray[i][2] = Homes.get(i).getHomeLastName();
            System.out.println(Homes.get(i).getHomeLastName());
            myHomeArray[i][3] = Homes.get(i).getHomeType();
            System.out.println(Homes.get(i).getHomeType());
            myHomeArray[i][4] = Homes.get(i).getHomePrice();
            System.out.println(Homes.get(i).getHomePrice());
        }
        
        model.setDataVector(myHomeArray, titles);
 
    }
    
    
    
    public ArrayList<Home> getHomes()
    {
        ArrayList<Home> Homes = new ArrayList<Home>();
        String HomeQuery = "SELECT * FROM HOMES";
        
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(HomeQuery);
            Home h;
            
            while(rs.next())
            {
                h = new Home();       
                //set array elements to values from table
                h.setHomeID(rs.getString("HOMEID"));  //set the fields of the object
                h.setHomeFirstName(rs.getString("FIRSTNAME"));
                h.setHomeLastName(rs.getString("LASTNAME")); 
                h.setHomeType(rs.getString("HOMETYPE"));              
                h.setHomePrice(rs.getDouble("HOMEPRICE")); 
                Homes.add(h);

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
                
        return Homes;                      //return an array list of homes.
    }
         
    
    public void updateDb(Home obj)
    {
        String sql = "UPDATE HOMES SET FIRSTNAME = ?, LASTNAME = ?, HOMETYPE = ?, HOMEPRICE = ? WHERE HOMEID = ?";
 
        try{
            
            pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, obj.HomeFirstName);
            pstmt.setString(2, obj.HomeLastName);
            pstmt.setString(3, obj.HomeType);
            pstmt.setDouble(4, obj.HomePrice);
            pstmt.setString(5, obj.HomeID);

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public ArrayList<Home> selectSpecificRecord(Home h)
    {
        ArrayList<Home> Homes = new ArrayList<Home>();
        String sql = "SELECT * FROM HOMES WHERE HOMEID = ?";
        try
        {
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, h.HomeID); 
         
         ResultSet result = pstmt.executeQuery(); 
       
         if (result.next()) {
      
              //set array elements to values from database
                
            h.setHomeID(result.getString("HOMEID"));  //set the fields of the object
            h.setHomeFirstName(result.getString("FIRSTNAME"));
            h.setHomeLastName(result.getString("LASTNAME")); 
            h.setHomeType(result.getString("HOMETYPE"));              
            h.setHomePrice(result.getDouble("HOMEPRICE"));  
            Homes.add(h);           //add a Coffee object to array list
 
        }
  
       }catch (SQLException e) {
           System.out.println("There was a SQL error during SELECT; " + e);
       }
        
       try
       {
            pstmt.close();
       }
       catch (SQLException e)
       {
           System.out.println("Error closing SQL select statement " + e);
       }
       return Homes;
    }
    
    public void deleteDb(Home obj)
    {
        String sql = "DELETE FROM HOMES WHERE HOMEID = ?";
 
        try{
            
            pstmt = conn.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, obj.HomeID);
            // execute the delete statement
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    public void insertDb(Home obj)
    {
        System.out.println("About to insert into table");
    
        System.out.println(obj.HomeID);
        System.out.println(obj.HomeFirstName);
        System.out.println(obj.HomeLastName);
        System.out.println(obj.HomeType);
        System.out.println(obj.HomePrice);
                      
        try
        {
     
            SQLString = "INSERT INTO Homes Values"
                       + "(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(SQLString);
            pstmt.setString(1, obj.HomeID);
            pstmt.setString(2, obj.HomeFirstName);
            pstmt.setString(3, obj.HomeLastName);
            pstmt.setString(4, obj.HomeType);
            pstmt.setDouble(5, obj.HomePrice);
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close(); 
    
         }catch(SQLException ex)
        {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: " + ex.getMessage());
        }

    }
}


