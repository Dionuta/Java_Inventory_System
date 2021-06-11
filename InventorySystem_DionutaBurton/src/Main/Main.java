package Main;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        addDummyData();//Dummy Data

        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/MainScreen.fxml"));
        primaryStage.setTitle("Dionuta's: Inventory System");
        primaryStage.setResizable(false);
        Image icon = new Image("file:src/Main/myLogo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Dummy Data
     */
    public void addDummyData(){
        Outsourced DummyData1 = new Outsourced(Inventory.getCountPartID(), "Swivel", 120.47, 5, 1, 20, "Husky");
        InHouse DummyData2 = new InHouse(Inventory.getCountPartID(), "Head Gasket", 550.66, 1, 10, 50, 4580);

        Product DummyData3 = new Product(Inventory.getCountProductID(), "Mustang",1999.99, 3,0,20);

        Inventory.addPart(DummyData1);
        Inventory.addPart(DummyData2);

        Inventory.addProduct(DummyData3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
