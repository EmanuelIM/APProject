package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ForthSceneController implements Initializable {


    @FXML
    private TableView<Solution> car;

    @FXML
    private TableColumn<Solution, String> carIdRez;

    @FXML
    private TableColumn<Solution, String> locname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        carIdRez.setCellValueFactory(new PropertyValueFactory<>("idCar"));
        locname.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        car.setItems(addSol());
    }

    public ObservableList<Solution> addSol(){
        List<Destination> destinations = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        try{
            File reader = new File("D:/AAProject/src/sample/Destinations.txt");
            Scanner readDes = new Scanner(reader);
            while(readDes.hasNextLine()){
                String line = readDes.nextLine();
                String[] data = line.split(" ");
                destinations.add(new Destination(Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),data[0]));
                System.out.println(destinations);
            }
            readDes.close();
            File reader2 = new File("D:/AAProject/src/sample/Cars.txt");
            Scanner readCar = new Scanner(reader2);
            while(readCar.hasNextLine()){
                String line = readCar.nextLine();
                String[] data = line.split(" ");
                cars.add(new Car(Double.parseDouble(data[1]),Integer.parseInt(data[0])));
                System.out.println(cars);
            }
            readCar.close();
        }catch (FileNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        ObservableList<Solution> solutions = FXCollections.observableArrayList();
        for (Car cartmp : cars){
            Solution solution = new Solution();
            solution.setCarid(Integer.toString(cartmp.getId()));
            for (Destination destination : destinations){
                solution.setLocationName(destination.getName());
            }
            solutions.add(solution);
            System.out.println(solution);
        }
        return solutions;
    }

}
