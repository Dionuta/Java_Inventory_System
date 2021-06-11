package View_Controller.Controllers;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ModifyPart Controller
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class ModifyPartController   implements  Initializable{

    public TextField idText;
    public TextField nameText;
    public TextField invText;
    public TextField priceText;
    public TextField maxText;
    public TextField minText;
    public TextField madeByText;
    public RadioButton outsourcedRadio;
    public RadioButton inHouseRadio;
    public Label sourceOfId;

    private Alert a = new Alert(Alert.AlertType.NONE);
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Part modifyPart;


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

            Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/MainScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    /**
     * Changes label
     * @param event action
     */
    public void inHouseHandle(ActionEvent event) {
        sourceOfId.setText("Machine ID");
    }

    /**
     * Changes label
     * @param event action
     */

    public void outsourcedHandle(ActionEvent event) {
        sourceOfId.setText("Company Name");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyPart = MainController.passModifyPart();


            if(modifyPart instanceof InHouse){
                inHouseRadio.setSelected(true);
                sourceOfId.setText("Machine ID");
                madeByText.setText(String.valueOf(((InHouse) modifyPart).getMachineId()));

            }

            if(modifyPart instanceof Outsourced){
                outsourcedRadio.setSelected(true);
                sourceOfId.setText("Company Name");
                madeByText.setText(String.valueOf(((Outsourced) modifyPart).getCompanyName()));
            }

        nameText.setText(String.valueOf(modifyPart.getName()));
        idText.setText((String.valueOf(modifyPart.getId())));
        invText.setText(String.valueOf(modifyPart.getStock()));
        priceText.setText(String.valueOf(modifyPart.getPrice()));
        minText.setText(String.valueOf(modifyPart.getMin()));
        maxText.setText(String.valueOf(modifyPart.getMax()));


    }


    /**
     * saves updated Item
     *
     * @param event
     */
    public void saveDataHandler(ActionEvent event) {
        try {
            if(inHouseRadio.isSelected()) {
                int min = Integer.parseInt(minText.getText());
                int max = Integer.parseInt(maxText.getText());
                double price = Double.parseDouble(priceText.getText());
                int stock = Integer.parseInt(invText.getText());
                int madeBy = Integer.parseInt(madeByText.getText());
                int id = Integer.parseInt(idText.getText());


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
                    InHouse newPart = new InHouse(id, nameText.getText(), price, stock, min, max, madeBy);
                    Inventory.addPart(newPart);
                }
            }
            else {
                int min = Integer.parseInt(minText.getText());
                int max = Integer.parseInt(maxText.getText());
                double price = Double.parseDouble(priceText.getText());
                int stock = Integer.parseInt(invText.getText());
                String macID = madeByText.getText();
                int id = Integer.parseInt(idText.getText());

                if (stock > max || stock < min) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Inventory can not be more than Max or less than MIN");
                    a.show();

                } else {
                    Inventory.deletePart(modifyPart);
                    Outsourced newPart = new Outsourced(id, nameText.getText(), price, stock, min, max, macID);
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
}
