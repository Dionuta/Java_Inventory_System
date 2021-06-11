package View_Controller.Controllers;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Add Part Controller
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class AddPartController {


    public TextField machineIdText;
    public TextField minText;
    public TextField nameText;
    public TextField invText;
    public TextField priceText;
    public TextField maxText;
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;
    public Label sourceOfId;
    public TextField madeByText;

    private Alert a = new Alert(Alert.AlertType.NONE);


    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     *  Transfers user to Main screen and confirms that user wants to cancel
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
     * saveDataHandler take data from text fields to create a new part.
     * Also, checks to see if inventory is greater than max or less than min.
     * Checks to see if part was made inHouse or Outsourced
     * @param event
     * @throws IOException
     */
    public void saveDataHandler(ActionEvent event) throws IOException {

        try {
            if(inHouseRadio.isSelected()) {
                int min = Integer.parseInt(minText.getText());
                int max = Integer.parseInt(maxText.getText());
                double price = Double.parseDouble(priceText.getText());
                int stock = Integer.parseInt(invText.getText());
                int madeBy = Integer.parseInt(madeByText.getText());


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
                    InHouse newPart = new InHouse(Inventory.getCountPartID(), nameText.getText(), price, stock, min, max, madeBy);
                    Inventory.addPart(newPart);
                }
            }
            else {
                int min = Integer.parseInt(minText.getText());
                int max = Integer.parseInt(maxText.getText());
                double price = Double.parseDouble(priceText.getText());
                int stock = Integer.parseInt(invText.getText());
                String macID = madeByText.getText();

                if (stock > max || stock < min) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Inventory can not be more than Max or less than MIN");
                    a.show();

                } else {
                    Outsourced newPart = new Outsourced(Inventory.getCountPartID(), nameText.getText(), price, stock, min, max, macID);
                    Inventory.addPart(newPart);
                }
            }

                root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/MainScreen.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


        }catch (Exception e)
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Check Input(s): " + e.getLocalizedMessage());
            a.show();
         }

}

    public void inHouseHandle(ActionEvent event) {
        sourceOfId.setText("Machine ID");
    }

    public void outsourcedHandle(ActionEvent event) {
        sourceOfId.setText("Company Name");
    }
}
