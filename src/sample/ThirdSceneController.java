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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThirdSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int id=0;


    public void switchToScene4(ActionEvent event)throws IOException {
        FileWriter fstream = new FileWriter ("D:/AAProject/src/sample/Cars.txt");
        BufferedWriter info = new BufferedWriter(fstream);
        ObservableList<Car> cars = car.getItems();
        for(Car cartmp : cars){
            info.write(Integer.toString(cartmp.getId()));
            info.write(" ");
            info.write(Double.toString(cartmp.getOriginalCapacity()));
            info.write("\n");
        }
        info.close();
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private TableView<Car> car;

    @FXML
    private TableColumn<Car, String> carId;

    @FXML
    private TableColumn<Car, String> weight;

    @FXML
    private TextField carweight;

    @FXML
    private Button addloc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carId.setCellValueFactory(new PropertyValueFactory<>("id"));
        weight.setCellValueFactory(new PropertyValueFactory<>("originalCapacity"));
    }

    public void addRecord(){
        Car newCar = new Car();
        id++;
        newCar.setId(id);
        newCar.setOriginalCapacity(Double.parseDouble(carweight.getText()));
        car.getItems().add(newCar);
        carweight.clear();
    }

}
