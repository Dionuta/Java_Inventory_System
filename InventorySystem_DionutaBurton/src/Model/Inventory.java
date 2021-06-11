package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProduct = FXCollections.observableArrayList();
    private static int countPartID = 0;
    private static int countProductID = 0;

    /**
     *Return all parts
     *
     * @return allParts
     */

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * return all products
     * @return all products
     */
    public static ObservableList<Product> getAllProduct() {
        return allProduct;
    }

    /**
     * Appends part to list
     *
     * @param newPart item to append
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Appends product to list.
     * @param newProduct the item to append
     */
    public static void addProduct(Product newProduct) {
        allProduct.add(newProduct);
    }

    /**
     * Number used for id of part
     * @return integer for id
     */
    public static int getCountPartID() {
        countPartID++;
        return countPartID;
    }


    /**
     * Search by name of part
     *
     * @param partName name or partial name of part
     * @return the matching part
     */

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for(Part p: allParts){
            if(p.getName().contains(partName)){
                namedParts.add(p);
            }
        }

        return namedParts;
    }

    /**
     * Search by id of part
     *
     * @param partID id of part
     * @return the part
     */
    public static Part lookupPart(int partID){

        for(Part p: allParts){
            if(p.getId() == partID)
               return p;
            }
        return null;
    }

    /**
     * Search by name of product
     *
     * @param partName name or partial name of product
     * @return the matching product
     */
    public static ObservableList<Product> lookupProduct(String partName){
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();

        for(Product p: allProduct){
            if(p.getName().contains(partName)){
                namedProduct.add(p);
            }
        }

        return namedProduct;
    }

    /**
     * Search for product by id
     *
     * @param productID id of item
     * @return return matching product
     */
    public static Product lookupProduct(int productID){
        for(Product p: allProduct){
            if(p.getId() == productID)

                return p;
        }
        return null;
    }

    /**
     * Update part
     *
     * @param index of item
     * @param selectedPart the replacement item
     */

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * Update product
     *
     * @param index of item
     * @param newProduct the replacement item
     */

    public static void updateProduct(int index, Product newProduct){
            allProduct.set(index, newProduct);
    }

    /**
     * Delete selected part
     *
     * @param selectedPart item that will be deleted
     * @return boolean to confirm deletion
     */

    public static boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * Delete selected product
     *
     * @param selectedProduct item that will be deleted
     * @return boolean to confirm deletion
     */

    public static boolean deleteProduct(Product selectedProduct){
        if(allProduct.contains(selectedProduct)){
            allProduct.remove(selectedProduct);
            return true;
        }
        else
            return false;
    }


    /**
     * Return number used for ID.
     *
     * @return integer for id
     */
    public static int getCountProductID() {
        countProductID++;
        return countProductID;
    }



}
