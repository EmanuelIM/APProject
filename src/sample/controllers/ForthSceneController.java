package sample.controllers;

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
import sample.objects.Solution;
import sample.objects.TotalCost;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ForthSceneController implements Initializable {

    private double totalCost = 0;
    private double totalAmmount = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Solution> car;

    @FXML
    private TableColumn<Solution, String> carIdRez;

    @FXML
    private TableColumn<Solution, String> locname;

    @FXML
    private TableView<TotalCost> costTable;

    @FXML
    private TableColumn<TotalCost, String> numKm;

    @FXML
    private TableColumn<TotalCost, String> price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carIdRez.setCellValueFactory(new PropertyValueFactory<>("idCar"));
        locname.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        numKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        price.setCellValueFactory(new PropertyValueFactory<>("cost"));
        car.setItems(addSol());
        costTable.setItems(getAllCosts());
    }

    public ObservableList<Solution> addSol() {
        ObservableList<Solution> solutions = FXCollections.observableArrayList();
        try {
            File reader2 = new File("src/sample/txtFiles/Solution.txt");
            Scanner readCar = new Scanner(reader2);
            while (readCar.hasNextLine()) {
                String line = readCar.nextLine();
                String[] data = line.split(" ");
                Solution solution = new Solution();
                solution.setIdCar(Integer.parseInt(data[0]));
                for (int i = 1; i < data.length - 2; ++i) {
                    solution.setLocationName(data[i]);
                }
                totalCost += Double.parseDouble(data[data.length - 2]);
                totalAmmount += Double.parseDouble(data[data.length - 1]);
                solutions.add(solution);
            }
            readCar.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return solutions;
    }

    public ObservableList<TotalCost> getAllCosts() {
        ObservableList<TotalCost> totalCosts = FXCollections.observableArrayList();
        DecimalFormat ft;
        double price = ((totalCost) / 10) * 3;
        ft = new DecimalFormat("$###,###.##");
        String finalPrice = ft.format(price);
        totalCosts.add(new TotalCost(totalCost, finalPrice));
        return totalCosts;
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../scenes/Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
