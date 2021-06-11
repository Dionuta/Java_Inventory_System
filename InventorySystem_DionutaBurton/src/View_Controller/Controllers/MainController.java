package View_Controller.Controllers;

import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Main Controller
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class MainController implements Initializable {

    public TableView<Part> partsTable; // The whole part table
    public TableColumn<Part, Integer> partIdCol; // part id column
    public TableColumn <Part, String> partNameCol; // Name column
    public TableColumn <Part, Integer> partInvCol; // Inventory column
    public TableColumn <Part, Double>  partPriceCol; // Price column
    public TableView<Product> productsTable; // The whole product table
    public TableColumn <Part, Integer>  productIdCol; // product ID column
    public TableColumn <Part, String>  productNameCol; // name column
    public TableColumn  <Part, Integer>  productInvCol; // Inventory column
    public TableColumn <Part, Double>productPriceCol; // Price column
    public TextField partsSearch; //Parts search field
    public TextField productSearch; // Product search field

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Alert a = new Alert(Alert.AlertType.NONE);

    private static Part partToModify; // holds the part you want to modify
    private static Product productToModify; // holds the product you want to modify



    /**
     *  Transfers user to add parts screen
     *
     *   @param event linked to Add button on parts side.
     *   @throws IOException from FXML Loader.
     */

    public void addPartHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View_Controller/Views/AddPartScreen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Passes selected item to parts modify Screen
     * @return passToModify is item being passed
     */
    public static Part passModifyPart(){
        return partToModify;
    }

    public static Product passModifyProduct(){
        return productToModify;
    }

    /**
     *  Transfers user to modify parts screen and pass info from main.
     *
     *   @param event linked to Modify button on parts side.
     *   @throws IOException from FXML Loader.
     */

    public void modifyPartHandler(ActionEvent event) throws IOException {

       partToModify = partsTable.getSelectionModel().getSelectedItem();

       if (partToModify == null)
           return;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View_Controller/Views/ModifyPartScreen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Delete Part but ask for confirmation.
     */

    public void deletePartHandler(ActionEvent actionEvent){


            Part part = partsTable.getSelectionModel().getSelectedItem();

            if (part == null)
                return;
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure?");
        a.showAndWait();
            if(a.getResult() == ButtonType.OK) {
            Inventory.deletePart(part);
        }


    }



        /**
         *  Transfers user to add product screen
         *
         *   @param event linked to Add button on product side.
         *   @throws IOException from FXML Loader.
         */

    public void addProductHandler(ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/AddProductScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


        /**
         *  Transfers user to modify parts screen
         *
         *   @param event linked to Modify button on product side.
         *   @throws IOException from FXML Loader.
         */

    public void modifyProductHandler(ActionEvent event) throws IOException{
        productToModify = productsTable.getSelectionModel().getSelectedItem();

        if (productToModify == null) // Prevents error when no item is selected
            return;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View_Controller/Views/ModifyProductScreen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Delete Product but ask for confirmation and checks associated items list.
     *
     */
    public void deleteProductHandler(){


            Product product = productsTable.getSelectionModel().getSelectedItem();

             if (product == null) // Prevents error when no item is selected
                return;

            if(product.getAssociatedPartsSize() > 0){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setHeaderText("Please remove Associated Parts from item.");
                a.showAndWait();
            }

            else {

                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setContentText("Are you sure?");
                a.showAndWait();
                if (a.getResult() == ButtonType.OK) {
                    Inventory.deleteProduct(product);
                }
            }
    }

    /**
     * This Searches for part by name or id.
     *
     */

    public void getPartTextHandler(ActionEvent actionEvent) {
        String text = partsSearch.getText(); //get text from search

        ObservableList<Part> part = Inventory.lookupPart(text); // hold the name of the part we are looking for


        try{
        if(part.size() == 0){ //only if part Observable list began looking by id
            int partID = Integer.parseInt(text);
            Part p = Inventory.lookupPart(partID);
            if (p != null){
                part.add(p);
            }
        }
        } catch (NumberFormatException e){
            //ignore
        }

        partsTable.setItems(part); // show what we are looking for
    }

    /**
     * This Searches for product by name or id.
     *
     */

    public void getProductTextHandler(ActionEvent actionEvent) {
        String text = productSearch.getText(); //get text from search

        ObservableList<Product> product = Inventory.lookupProduct(text);  // hold the name of the product we are looking
        // for


        try{
            if(product.size() == 0){ //only if product Observable list began looking by id
                int productID = Integer.parseInt(text);
                Product p = Inventory.lookupProduct(productID);
                if (p != null){
                    product.add(p);
                }
            }
        } catch (NumberFormatException e){
            //ignore
        }

        productsTable.setItems(product); // show what we are looking for
    }

    /**
     *  Closes the application.
     */
    public void ExitHandler(){

        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure?");
        a.showAndWait();
        if(a.getResult() == ButtonType.OK)
            System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        partsTable.setItems(Inventory.getAllParts());

        productsTable.setItems(Inventory.getAllProduct());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }



}

