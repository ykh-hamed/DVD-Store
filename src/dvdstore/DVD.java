package dvdstore;

import javax.swing.JOptionPane;

/**
 *
 * @author Group13
 */
public class DVD 
{
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String genre;
    private int year;
    private boolean availability;
    
    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getGenre(){
        return genre;
    }
    public int getYear(){
        return year;
    }
    public boolean getAvailability(){
        return availability;
    }
    
    public void setID(int i){
        id=i;
    }
    public void setName(String n){
        name=n;
    }
    public void setPrice(double p){
        price=p;
    }
    public void setQuantity(int q){
        quantity=q;
    }
    public void setGenre(String g){
        genre=g;
    }
    public void setYear(int y){
        year=y;
    }
    public void setAvailability(boolean a){
        availability=a;
    }
    
    public DVD(){};
    
    public DVD(int i, String n, double p, int q, String g, int y, boolean a)
    {
        id = i;
        name = n;
        price = p;
        quantity = q;
        genre = g;
        year = y;
        availability = a;
    }
    
    /**
     * Displays DVD info into a dialog
     * @param x DVD
     */
    static public void DisplayDVD(DVD x)
    {    
        JOptionPane.showMessageDialog(null, "ID: "+x.id+"\nName: "+x.name+"\nPrice: "+x.price
        +"\nQuantity: "+x.quantity+"\nGenre: "+x.genre+"\nYear: "+x.year+"\nAvailability: "+x.availability);
    }

}
