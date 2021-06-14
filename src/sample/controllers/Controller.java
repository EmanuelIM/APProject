package sample.controllers;


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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.objects.Destination;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean isFirst = true;


    public void switchToScene3(ActionEvent event) throws IOException {
        FileWriter fstream = new FileWriter("src/sample/txtFiles/Destinations.txt");
        BufferedWriter info = new BufferedWriter(fstream);
        ObservableList<Destination> destinations = destination.getItems();
        for (Destination destination : destinations) {
            info.write(destination.getName());
            info.write(" ");
            info.write(Double.toString(destination.getX()));
            info.write(" ");
            info.write(Double.toString(destination.getY()));
            info.write(" ");
            info.write(Double.toString(destination.getWeightOfPackages()));
            info.write("\n");
        }
        info.close();
        root = FXMLLoader.load(getClass().getResource("../scenes/Scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    private TextField namefield;

    @FXML
    private TextField xcoor;

    @FXML
    private TextField ycoor;

    @FXML
    private TextField weightfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        xcoordinate.setCellValueFactory(new PropertyValueFactory<>("x"));
        ycoordinate.setCellValueFactory(new PropertyValueFactory<>("y"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weightOfPackages"));
    }

    public void addRecord() {
        Destination newDestination = new Destination();
        if (isFirst) {
            newDestination.setName(namefield.getText() + "(Depot)");
        } else newDestination.setName(namefield.getText());
        newDestination.setX(Double.parseDouble(xcoor.getText()));
        newDestination.setY(Double.parseDouble(ycoor.getText()));
        if (isFirst) {
            newDestination.setWeightOfPackages(0.0);
            isFirst = false;
        } else newDestination.setWeightOfPackages(Double.parseDouble(weightfield.getText()));
        destination.getItems().add(newDestination);
        namefield.clear();
        xcoor.clear();
        ycoor.clear();
        weightfield.clear();
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../scenes/Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
