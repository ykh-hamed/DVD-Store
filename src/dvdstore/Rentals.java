package dvdstore;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author Group13
 */
public class Rentals
{
    private DVD rentedDVD;
    private Client renter;
    private Date dueDate;
    private double rentPrice;
    
    /**
     *  This method is the constructor of the class rentals with all its parameters.
     * @param d An object of the DVD rented.
     * @param c An object of the Client Which rented the DVD.
     * @param x The due date of the rental.
     * @param p The price of the rental.
     */
    public Rentals(DVD d, Client c, Date x, double p)
    {
        rentedDVD = d;
        renter = c;
        dueDate = x;
        rentPrice = p; 
    }
    
    public String getDVDName()
    {
        return (rentedDVD.getName());
    }
    
    public String getClientName()
    {
        return (renter.getUsername());
    }
    
    public double getRentalPrice()
    {
        return rentPrice;
    }
    
    public String getDueDate()
    {
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String d = formatter.format(dueDate);
        return d;
    }
    
}
