package dvdstore;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Group13
 */
public class Admin extends User
{
    /**
     *
     * Overloaded Constructor
     * @param u username
     * @param p password
     */
    public Admin(String u, String p)
    {
        super(u, p);
    }
    
    /**
    *
    * Takes an object of DVD and add it to the DVD Table in the database
     * @param x DVD Object
     * @return true if added, false if not
    */
    public static boolean addDVD(DVD x)
    {
      try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "INSERT INTO DVDTABLE (DVD_ID, NAME, PRICE, QUANTITY, GENRE, DVDYEAR) VALUES ("+x.getID()+", '"+x.getName()+"',"+x.getPrice()+","+x.getQuantity()+",'"+x.getGenre()+"',"+x.getYear()+")";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close(); 
            con.close(); 
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }   
      return true;
    }
    
    /**
    *
    * Retrieves all clients from the database and store them in an Arraylist
     * @return Arraylist of all clients 
    */
    public static ArrayList<Client> browseClients()
    {
        ArrayList <Client> clientList = new ArrayList<Client>();
       
       String username,password;
       
       try
       {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "SELECT * FROM USERTABLE WHERE UPPER(TYPE) = 'CLIENT'";
            Statement st = con.createStatement();
            ResultSet rs=null;

            rs=st.executeQuery(sql);

            while(rs.next())
             {
                 username=rs.getString("USERNAME");
                 password=rs.getString("PASSWORD");
                 clientList.add(new Client(username,password));
             }

            rs.close();
            st.close();
            con.close();
       }
       catch (SQLException ex) 
       {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
       }  
        return clientList;
    }
    
    /**
    *
    * Retrieves all Admins from the database and store them in an Arraylist
     * @return Arraylist of all Admins 
    */
    public static ArrayList<Admin> browseAdmins()
    {
        ArrayList <Admin> AdminList = new ArrayList<Admin>();
       
       String username,password;
       
       try
       {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "SELECT * FROM USERTABLE WHERE UPPER(TYPE) = 'ADMIN'";
            Statement st = con.createStatement();
            ResultSet rs=null;

            rs=st.executeQuery(sql);

            while(rs.next())
             {
                 username=rs.getString("USERNAME");
                 password=rs.getString("PASSWORD");
                 AdminList.add(new Admin(username,password));
             }

            rs.close();
            st.close();
            con.close();
       }
       catch (SQLException ex) 
       {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
       }  
        return AdminList;
    }
    
    /**
    *
    * Takes an object of Client and add it to the Users Table in the database with type Client
     * @param x Client Object
     * @return true if added, false if not
    */
    public static boolean addClient(Client x)
    {
      try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "INSERT INTO USERTABLE (USERNAME, PASSWORD, TYPE) VALUES ('"+x.getUsername()+"', '"+x.getPassword()+"','Client')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close(); 
            con.close(); 
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }   
      return true;
    }
    
    /**
    *
    * Takes an object of Admin and add it to the Users Table in the database with type Admin
     * @param x Admin Object
     * @return true if added, false if not
    */
    public static boolean addAdmin(Admin x)
    {
      try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "INSERT INTO USERTABLE (USERNAME, PASSWORD, TYPE) VALUES ('"+x.getUsername()+"', '"+x.getPassword()+"','Admin')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close(); 
            con.close(); 
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }   
      return true;
    }
    
    /**
    *
    * Takes a client's username, searches for it first, if found then delete the record where this username occurs
     * @param userName client's username
     * @return true if deleted, false if not
    */
    public static boolean deleteClient(String userName)
    {
       if(!searchClient(userName))
           return false;
       
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "DELETE FROM USERTABLE WHERE UPPER(USERNAME)='"+userName.toUpperCase()+"'";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close(); 
            con.close(); 
        }
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
         } 
        
        return true;    
    }
    
    /**
    *
    * Takes a DVD name, searches for it first, if found then delete the record where this DVD name occurs
     * @param dvdName DVD Name
     * @return true if deleted, false if not
    */
    public static boolean deleteDVD(String dvdName)
    {
       if(!User.searchDVD(dvdName))
           return false;
       
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "DELETE FROM DVDTABLE WHERE NAME='"+dvdName+"'";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close(); 
            con.close(); 
        }
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
         } 
        
        return true;   
    }
    
    /**
    *
    * Takes the updated clients new information and updates it in the database
     * @param oldName old username
     * @param newName updated username
     * @param newPass updated password
     * @param newType updated type
     * @return true if updated successfully, false if not
    */
    public static boolean updateClient(String oldName,String newName,String newPass,String newType)
    {
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "UPDATE USERTABLE SET USERNAME='"+newName+"' , PASSWORD='"+newPass+"' , TYPE='"+newType+"' WHERE UPPER(USERNAME)='"+oldName.toUpperCase()+"' ";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
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
    *
    * Takes the updated clients new information and updates it in the database
     * @param oldName old name
     * @param newID updated ID
     * @param newName updated name
     * @param newPrice updated Price
     * @param newQuantity updated Quantity
     * @param newGenre updated Genre
     * @param newYear updated Year of release
     * @return true if updated successfully, false if not
    */
    public static boolean updateDVD(String oldName, int newID, String newName, double newPrice, int newQuantity, String newGenre, int newYear)
    {
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "UPDATE DVDTABLE SET DVD_ID="+newID+" , NAME='"+newName+"' , PRICE="+newPrice+" , QUANTITY="+newQuantity+", GENRE='"+newGenre+"', DVDYEAR="+newYear+" WHERE UPPER(NAME)='"+oldName.toUpperCase()+"' ";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
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
    *
    * Takes the client's userName and searches the database for it with ignoring case
     * @param userName The Username you want to search for 
     * @return true if done successfully, false if not
    */    
    static public boolean searchClient(String userName)
    {
        try
        {
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM USERTABLE WHERE UPPER(USERNAME)='"+userName.toUpperCase()+"'";
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
    *
    * Takes the client's userName and searches the database for it with ignoring case, and stores it in an object of Client
     * @param userName The Username you want to retrieve 
     * @return Client Object with the user's information
    */    
    static public Client retrieveClient(String userName)
    {
        Client x = new Client();
        
        try
        {
           final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
           String sql = "SELECT * FROM USERTABLE WHERE UPPER(USERNAME)='"+userName.toUpperCase()+"'";
           Statement st = con.createStatement();
           ResultSet rs=null;

           rs=st.executeQuery(sql);

           while(rs.next())
           {
               x.setUsername(rs.getString("USERNAME"));
               x.setPassword(rs.getString("PASSWORD"));
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
    *
    * Takes the admin's userName and password, check for them in the database while making sure the type is Admin
     * @param uname Username
     * @param pass Password
     * @return true if the information passed matches the database, false otherwise
    */    
    public static boolean adminLogin(String uname, String pass)
    {
        String type = "Admin";
        try
        {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DVDStoreDB","bue","bue");
            String sql = "SELECT * FROM USERTABLE WHERE UPPER(USERNAME)='"+uname.toUpperCase()+"' AND PASSWORD='"+pass+"' AND UPPER(TYPE) ='"+type.toUpperCase()+"'";
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
            System.out.println(ex.toString());
            return false;
        }   
    }
}
