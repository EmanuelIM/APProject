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
import java.util.Random;

public class FifthSceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int id = 0;


    public void switchToScene4(ActionEvent event) throws IOException {
        FileWriter fstream = new FileWriter("src/sample/Cars.txt");
        BufferedWriter info = new BufferedWriter(fstream);
        ObservableList<Car> cars = car.getItems();
        for (Car cartmp : cars) {
            info.write(Integer.toString(cartmp.getId()));
            info.write(" ");
            info.write(Double.toString(cartmp.getOriginalCapacity()));
            info.write("\n");
        }
        info.close();

        FileWriter fstreamD = new FileWriter("src/sample/Destinations.txt");
        BufferedWriter infoDes = new BufferedWriter(fstreamD);
        ObservableList<Destination> destinations = destination.getItems();
        for (Destination destination : destinations) {
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
        Reader r = new Reader();
        r.readValues();
        VRPAlg vrp = new VRPAlg(r.getDimension(), r.getNumberOfCars(), r.getD(), r.getC());
        ObservableList<Solution> solution = FXCollections.observableArrayList();
        System.out.println("Ruleaza....");
        vrp.solveVRP(solution);
        System.out.println("A rulat");
        FileWriter ostream = new FileWriter("src/sample/Solution.txt");
        BufferedWriter writer = new BufferedWriter(ostream);
        ObservableList<Solution> solutions = solution;
        for (Solution solution1 : solutions) {
            writer.write(Integer.toString(solution1.getIdCar()));
            writer.write(" ");
            for (String s : solution1.getLocationName()) {
                writer.write(s);
                writer.write(" ");
            }
            writer.write(Double.toString(solution1.getTotalCost()));
            writer.write(" ");
            writer.write(Double.toString(solution1.getTotalAmount()));
            writer.write("\n");
        }
        writer.close();
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
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

    ObservableList<Destination> getDestinations() {
        String[] names = new String[]{"Ion", "Mirel", "Giorgel", "Alexandru", "Sorin", "Emi", "Ioana", "Cristina", "Ana", "Nicu"};
        boolean[] visited = new boolean[11];
        Random random = new Random();
        ObservableList<Destination> destinations = FXCollections.observableArrayList();
        for (int i = 0; i < 8; i++) {
            boolean ok = false;
            while (!ok) {
                int j = random.nextInt(10);
                if (!visited[j]) {
                    visited[j] = true;
                    ok = true;
                    double x = 10 + (200 - 10) * random.nextDouble();
                    double y = 10 + (200 - 10) * random.nextDouble();
                    double weight;
                    if (i == 0) {
                        weight = 0.0;
                        destinations.add(new Destination(x, y, weight, "Depot(Start)"));
                    } else {
                        weight = 1 + (100 - 1) * random.nextDouble();
                        destinations.add(new Destination(x, y, weight, names[j]));
                    }

                }
            }
        }
        return destinations;
    }


    ObservableList<Car> getCars() {
        ObservableList<Car> cars = FXCollections.observableArrayList();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            double cap = 1 + (150 - 1) * random.nextDouble();
            cars.add(new Car(cap, i + 1));
        }
        return cars;
    }


}
