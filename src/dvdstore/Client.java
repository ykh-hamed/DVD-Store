package dvdstore;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Group13
 */
public class Client extends User
{
    public Client(){};
    
    public Client(String u, String p) 
    {
        super(u, p);
    }
    /**
     * This methods rents a DVD for a client.
     * @param dvdID The Id of the DVD that will be rented.
     * @param dueDate The due date of the rental.
     * @param rentalPrice the price of  the rental.
     * @return  Boolean indicating whether the rental was successful or not.
     */
    public boolean rentDVD(int dvdID,Date dueDate, double rentalPrice)
    {
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            PreparedStatement ps = con.prepareStatement("insert into DVDRENTALS values (?,?,?,?)");
            ps.setInt(1, dvdID);
            ps.setString(2, getUsername());
            ps.setDouble(3, rentalPrice);
            ps.setDate(4, dueDate);
            ps.executeUpdate();
            ps.close();
//            
//            String sql = "INSERT INTO DVDRENTALS (DVD_ID, USERNAME, RENTAL_PRICE, DUE_DATE) VALUES ("+dvdID+", '"+userName+"', "+rentalPrice+", '"+dueDate+"')";
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//            st.close();  
            
            String sql2 = " UPDATE DVDTABLE SET QUANTITY = QUANTITY - 1 WHERE DVD_ID= "+dvdID+" ";
            Statement st2 = con.createStatement();
            st2.executeUpdate(sql2);
            st2.close();
            
            con.close();
            
            return true;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }   


    }
    
    /**
     * This methods returns a client's rented DVD.
     * @param dvdID The id of the DVD
     * @return The price of the penalty on the DVD.
     */
    public int returnDVD(int dvdID)
    {
        java.sql.Date dueDate = null;
        int penalty;
        int daysDiff = 0;
        long diff, diffDays;      
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "SELECT DUE_DATE FROM DVDRENTALS WHERE DVD_ID="+dvdID+" AND USERNAME='"+getUsername()+"' ";
            Statement st = con.createStatement();
            ResultSet rs=null;
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                dueDate = rs.getDate("DUE_DATE");
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "DELETE FROM DVDRENTALS WHERE DVD_ID="+dvdID+" AND USERNAME='"+getUsername()+"' ";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            
            String sql2 = "UPDATE DVDTABLE SET QUANTITY = QUANTITY + 1 WHERE DVD_ID= "+dvdID+"";
            Statement st2 = con.createStatement();
            st2.executeUpdate(sql2);
            st2.close();
            
            con.close();
            
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        } 
        
        java.util.Date temp = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(temp.getTime());
        diff = currentDate.getTime() - dueDate.getTime();
        diffDays = diff / (24 * 60 * 60 * 1000)+1;
        daysDiff = (int) diffDays;
        
        if (daysDiff <= 0)
            return 0;
        else
        {
            penalty = daysDiff * 5;
            return penalty;
        }

    }
    /**
     * This method validates the clients credentials.
     * @param uname Username of the client.
     * @param pass Password of the client.
     * @return Boolean indicating whether the login was successful or not.
     */
    public static boolean clientLogin(String uname, String pass)
    {
        String type = "Client";
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "SELECT * FROM USERTABLE WHERE UPPER(USERNAME)='"+uname.toUpperCase()+"' AND PASSWORD='"+pass+"' AND UPPER(TYPE) ='"+type.toUpperCase()+"' ";
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
     * This method browses the clients rented DVDs.
     * @return ArrayList of the rented DVDs.
     */
    public ArrayList<Rentals> browseRented()
    {
       ArrayList <Rentals> rentalsArr= new ArrayList<>();
       try
       {    
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM DVDRENTALS WHERE USERNAME= '"+getUsername()+"' ";
           Statement st = con.createStatement();
           ResultSet rs=null;

           rs=st.executeQuery(sql);

           while(rs.next())
           {
              int dvdID=rs.getInt("DVD_ID");
              String username=rs.getString("USERNAME");
              double rentalPrice = rs.getDouble("RENTAL_PRICE");
              Date due = rs.getDate("DUE_DATE");
              DVD d = User.retrieveDVD(dvdID);
              Client c = Admin.retrieveClient(username);
              Rentals r = new Rentals(d, c, due, rentalPrice);
              rentalsArr.add(r);
           }
            rs.close();
            st.close();
            con.close();
        }
       catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }   
        return rentalsArr;
    }
}
