package dvdstore;

import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Group13
 */
public class User 
{
    private String username;
    private String password;
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String u){
        username=u;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String p){
        password=p;
    }
    
    public User(){};
    public User(String u, String p)
    {
        username = u;
        password = p;
    }
    /**
     * This method prints the information of a user.
     * @param x An object of a user.
     */
    static public void DisplayUser(User x)
    {    
        JOptionPane.showMessageDialog(null, "Username: "+x.getUsername()+"\nPassword: "+x.getPassword());
    }
    
    /**
     * This method retrieves all the DVDs from the database.
     * @return ArrayList of all the DVDs.
     */
    static public ArrayList<DVD> browseDVD()
    {    
       ArrayList <DVD> dvdList = new ArrayList<DVD>();
       
       int id,quantity,year;
       double price;
       String name,genre;
       boolean availability;
       
       try
        {
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM DVDTABLE";
           Statement st = con.createStatement();
           ResultSet rs=null;

           rs=st.executeQuery(sql);

           while(rs.next())
            {
                id=rs.getInt("DVD_ID");
                quantity=rs.getInt("QUANTITY");
                year=rs.getInt("DVDYEAR");
                price=rs.getDouble("PRICE");
                name=rs.getString("NAME");
                genre=rs.getString("GENRE");
                
                if(quantity>0)
                    availability=true;
                else
                    availability=false;
                
                dvdList.add(new DVD(id,name,price,quantity,genre,year,availability));
           }
           
           rs.close();
           st.close();
           con.close();
       }
       catch (SQLException ex) 
       {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
       }  
        return dvdList;
    }
    
    /**
     * This method checks whether a DVD exists or not.
     * @param dvdName The title of the DVD .
     * @return boolean indicating the existence of the DVD.
     */
    static public boolean searchDVD(String dvdName)
    {
        try
        {
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM DVDTABLE WHERE UPPER(NAME)='"+dvdName.toUpperCase()+"'";
           Statement st = con.createStatement();
           ResultSet rs=null;

           rs=st.executeQuery(sql);
           
           while(rs.next())
            {
                return true;
            }
            rs.close();
            st.close();
            con.close();
            return false;
        }
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }   
    }
    
    /**
     * This method retrieves a DVD's attributes from the database.
     * @param dvdName The title of the DVD.
     * @return An object of DVD with all it's attributes retrieved from the database.
     */
    public static DVD retrieveDVD(String dvdName)
    {
        DVD x = new DVD();
        
        try
        {
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM DVDTABLE WHERE UPPER(NAME)='"+dvdName.toUpperCase()+"'";
           Statement st = con.createStatement();
           ResultSet rs=null;

           rs=st.executeQuery(sql);

           while(rs.next())
           {
               x.setGenre(rs.getString("genre"));
               x.setID(rs.getInt("dvd_id"));
               x.setName(rs.getString("name"));
               x.setPrice(rs.getDouble("price"));
               x.setQuantity(rs.getInt("quantity"));
               x.setYear(rs.getInt("dvdyear"));
               
               if(x.getQuantity()>0)
                   x.setAvailability(true);
               else
                   x.setAvailability(false);
            }
               rs.close();
               st.close();
               con.close();
        }
        
        catch (SQLException ex)
        {
           JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        return x;
    }
    /**
     * This method retrieves a DVD's attributes from the database.
     * @param dvdID The id of the DVD.
     * @return An object of DVD with all it's attributes retrieved from the database.
     */
    public static DVD retrieveDVD(int dvdID)
    {
        DVD x = new DVD();
        
        try
        {
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM DVDTABLE WHERE DVD_ID="+dvdID+"";
           Statement st = con.createStatement();
           ResultSet rs=null;

           rs=st.executeQuery(sql);

           while(rs.next())
           {
               x.setGenre(rs.getString("genre"));
               x.setID(rs.getInt("dvd_id"));
               x.setName(rs.getString("name"));
               x.setPrice(rs.getDouble("price"));
               x.setQuantity(rs.getInt("quantity"));
               x.setYear(rs.getInt("dvdyear"));
               
               if(x.getQuantity()>0)
                   x.setAvailability(true);
               else
                   x.setAvailability(false);
            }
               rs.close();
               st.close();
               con.close();
        }
        
        catch (SQLException ex)
        {
           JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        return x;
    }
    
}
