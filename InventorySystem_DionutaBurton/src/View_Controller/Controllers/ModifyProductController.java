package View_Controller.Controllers;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

/**
 * Modify Product Controller
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class ModifyProductController implements Initializable {

    public TextField partsSearch;
    private Alert a = new Alert(Alert.AlertType.NONE);
    private Stage stage;
    private Scene scene;
    private Parent root;

    public TextField productIdText;
    public TextField productNameText;
    public TextField productInvText;
    public TextField productPriceText;
    public TextField productMaxText;
    public TextField productMinText;
    public TableView partsTable;
    public TableColumn<Part, Integer> partIdCol;
    public TableColumn <Part, String> partNameCol;
    public TableColumn <Part, Integer> partInvCol;
    public TableColumn <Part, Double> partPriceCol;
    public TableView associatedTable;
    public TableColumn <Part, Integer>  associatedID;
    public TableColumn <Part, String> associatedName;
    public TableColumn <Part, Integer>  associatedInv;
    public TableColumn <Part, Double> associatedPrice;
    private Product modifyProduct;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProduct = MainController.passModifyProduct();


        associatedTable.setItems(modifyProduct.getAllAssociatedParts());


        partsTable.setItems(Inventory.getAllParts());


        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productNameText.setText(String.valueOf(modifyProduct.getName()));
        productIdText.setText((String.valueOf(modifyProduct.getId())));
        productInvText.setText(String.valueOf(modifyProduct.getStock()));
        productPriceText.setText(String.valueOf(modifyProduct.getPrice()));
        productMinText.setText(String.valueOf(modifyProduct.getMin()));
        productMaxText.setText(String.valueOf(modifyProduct.getMax()));





    }

    /**
     *  Transfers user to Main screen and confirms that user wants to cancel
     *
     *   @param event linked to Cancel button.
     *   @throws IOException from FXML Loader.
     */

    public void cancelPartHandler(ActionEvent event) throws IOException {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure?");
        a.showAndWait();

        if(a.getResult() == ButtonType.OK) {

            root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/MainScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Saves updated product
     *
     * @param event
     */
    public void saveHandler(ActionEvent event) {
        try {

            double price = Double.parseDouble(productPriceText.getText());
            int stock = Integer.parseInt(productInvText.getText());
            int max = Integer.parseInt(productMaxText.getText());
            int min = Integer.parseInt(productMinText.getText());
            int id = Integer.parseInt((productIdText.getText()));


            if (stock > max || stock < min) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Inventory can not be more than Max or less than MIN");
                a.show();



            }
            else if(max < min){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Max can not be less than min.");
                a.show();

            }
            else {
                Product newProduct = new Product(id, productNameText.getText(),price,stock,min,
                        max);


                for (Part p : associatedParts)
                    newProduct.addAssociatedPart(p);

                Inventory.deleteProduct(modifyProduct);
                Inventory.addProduct(newProduct);

            }

            root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/MainScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }catch(Exception e){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Check Input(s): " + e.getLocalizedMessage());
            a.showAndWait();
        }
    }

    /**
     * Add part from inventory to associated parts of a product
     * @param actionEvent on click event
     */

    public void addButton(ActionEvent actionEvent) {
        Part selectedItem = (Part) partsTable.getSelectionModel().getSelectedItem();



        if(selectedItem != null) {
            associatedParts.add(selectedItem);
            associatedTable.setItems(associatedParts);
        }
    }

    /**
     * Remove item from the associated table
     * @param actionEvent event
     */
    public void removeHandler(ActionEvent actionEvent) {
        Part selectedItem = (Part) associatedTable.getSelectionModel().getSelectedItem();
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure?");
        a.showAndWait();

        if(selectedItem == null)
            return;

        if (a.getResult() == ButtonType.OK){
            if(selectedItem != null) {
                associatedParts.remove(selectedItem);
            }
        }

    }

    /**
     * Search by id or name of part
     *
     * @param actionEvent
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
}
