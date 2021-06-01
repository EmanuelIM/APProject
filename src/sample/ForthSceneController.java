package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
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
    private double totalAmmount =0;

    @FXML
    private TableView<Solution> car;

    @FXML
    private TableColumn<Solution, String> carIdRez;

    @FXML
    private TableColumn<Solution, String> locname;

    @FXML
    private TableView<TotalCost> costTable;

    @FXML
    private TableColumn<TotalCost,String> numKm;

    @FXML
    private TableColumn<TotalCost, String> price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        carIdRez.setCellValueFactory(new PropertyValueFactory<>("idCar"));
        locname.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        numKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        price.setCellValueFactory(new PropertyValueFactory<>("cost"));
        car.setItems(addSol());
        costTable.setItems(getAllCosts());
    }

    public ObservableList<Solution> addSol(){
        ObservableList<Solution> solutions = FXCollections.observableArrayList();
        try{
            File reader2 = new File("C:\\xampp\\htdocs\\BetRat\\APProject\\src\\sample\\Solution.txt");
            Scanner readCar = new Scanner(reader2);
            while(readCar.hasNextLine()){
                String line = readCar.nextLine();
                String[] data = line.split(" ");
                Solution solution = new Solution();
                solution.setIdCar(Integer.parseInt(data[0]));
                for(int i = 1 ; i < data.length - 2; ++i) {
                    solution.setLocationName(data[i]);
                }
                totalCost += Double.parseDouble(data[data.length - 2]);
                totalAmmount += Double.parseDouble(data[data.length-1]);
                solutions.add(solution);
            }
            readCar.close();
        }catch (FileNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        return solutions;
    }

    public ObservableList<TotalCost> getAllCosts(){
        ObservableList<TotalCost> totalCosts = FXCollections.observableArrayList();
        totalCosts.add(new TotalCost(totalCost,totalAmmount));
        return totalCosts;
    }

}
