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

    private double totalCost = 0;

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
        ObservableList<Solution> solutions = FXCollections.observableArrayList();
        try{
            File reader2 = new File("C:\\Users\\Iacob Emanuel\\Documents\\GitHub\\APProject\\src\\sample\\Solution.txt");
            Scanner readCar = new Scanner(reader2);
            while(readCar.hasNextLine()){
                String line = readCar.nextLine();
                String[] data = line.split(" ");
                Solution solution = new Solution();
                solution.setIdCar(Integer.parseInt(data[0]));
                for(int i = 1 ; i < data.length - 1; ++i) {
                    solution.setLocationName(data[i]);
                }
                totalCost += Double.parseDouble(data[data.length - 1]);
                solutions.add(solution);
            }
            readCar.close();
        }catch (FileNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        return solutions;
    }

}
