package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class Product  {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    public Product(int id, String name, double price, int stock, int min, int max){


        this.id = id;
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    /**
     * sets id of product
     * @param id value of id
     */

    public void setId(int id){
        this.id = id;
    }

    /**
     * sets name of product
     * @param name value of name
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * set price of product
     * @param price value of price
     */

    public void setPrice(double price){
        this.price = price;
    }

    /**
     * sets stock of product
     * @param stock value of stock
     */

    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * set min of product
     * @param min value of min
     */

    public void setMin(int min){
        this.min = min;
    }

    /**
     * set max amount of product
     * @param max value of max
     */

    public void setMax(int max){
        this.max = max;
    }

    /**
     * @return id
     */

    public int getId() {
        return id;
    }

    /**
     * @return  name
     */

    public String getName(){
        return name;
    }

    /**
     * @return name
     */

    public double getPrice() {
        return price;
    }

    /**
     * @return min
     */

    public int getMin() {
        return min;
    }

    /**
     * @return max
     */

    public int getMax() {
        return max;
    }

    /**
     * @return stock
     */

    public int getStock() {
        return stock;
    }

    /**

     * @param part adds part to Associated Part list
     */

    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * @return all associated parts
     */

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public int getAssociatedPartsSize(){
        return associatedParts.size();
    }
}
