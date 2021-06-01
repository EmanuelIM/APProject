package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FifthSceneController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int id=0;


    public void switchToScene4(ActionEvent event)throws IOException {
        FileWriter fstream = new FileWriter ("C:\\Users\\Iacob Emanuel\\Documents\\GitHub\\APProject\\src\\sample\\Cars.txt");
        BufferedWriter info = new BufferedWriter(fstream);
        ObservableList<Car> cars = car.getItems();
        for(Car cartmp : cars){
            info.write(Integer.toString(cartmp.getId()));
            info.write(" ");
            info.write(Double.toString(cartmp.getOriginalCapacity()));
            info.write("\n");
        }
        info.close();

        FileWriter fstreamD = new FileWriter ("C:\\Users\\Iacob Emanuel\\Documents\\GitHub\\APProject\\src\\sample\\Destinations.txt");
        BufferedWriter infoDes = new BufferedWriter(fstreamD);
        ObservableList<Destination> destinations = destination.getItems();
        for(Destination destination : destinations){
            infoDes.write(destination.getName());
            infoDes.write(" ");
            infoDes.write(Double.toString(destination.getX()));
            infoDes.write(" ");
            infoDes.write(Double.toString(destination.getY()));
            infoDes.write(" ");
            infoDes.write(Double.toString(destination.getWeightOfPackages()));
            infoDes.write("\n");
        }
        infoDes.close();
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableView<Destination> destination;

    @FXML
    private TableColumn<Destination, String> name;

    @FXML
    private TableColumn<Destination, String> xcoordinate;

    @FXML
    private TableColumn<Destination, String> ycoordinate;

    @FXML
    private TableColumn<Destination, String> weight;

    @FXML
    private TableView<Car> car;

    @FXML
    private TableColumn<Car, String> carId;

    @FXML
    private TableColumn<Car, String> weight1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        xcoordinate.setCellValueFactory(new PropertyValueFactory<>("x"));
        ycoordinate.setCellValueFactory(new PropertyValueFactory<>("y"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weightOfPackages"));
        carId.setCellValueFactory(new PropertyValueFactory<>("id"));
        weight1.setCellValueFactory(new PropertyValueFactory<>("originalCapacity"));
        car.setItems(getCars());
        destination.setItems(getDestinations());
    }

     ObservableList<Destination> getDestinations(){
        ObservableList<Destination> destinations = FXCollections.observableArrayList();
         destinations.add(new Destination(21.2,21.3,321,"Mirel"));
         destinations.add(new Destination(42.3,24.1,2,"Sorin"));
         destinations.add(new Destination(232.2,552,42,"Dorel"));
         destinations.add(new Destination(241.4,241.2,21,"Emi"));
         destinations.add(new Destination(521.2,24.1,15,"Jon"));
         return destinations;
    }

    ObservableList<Car> getCars(){
        ObservableList<Car> cars = FXCollections.observableArrayList();
        cars.add(new Car(23.2,1));
        cars.add(new Car(343,2));
        cars.add(new Car(543,3));
        cars.add(new Car(21,4));
        return cars;
    }
}
