package groupsix.citywalk.controller;

import groupsix.citywalk.controller.Controller;
import groupsix.citywalk.maingame.Main;
import groupsix.citywalk.service.Game;
import javafx.fxml.FXML;
import javafx.scene.control.cell.MapValueFactory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoardController  extends Controller {

    @FXML
    private TableView<Map<String, Object>> leaderboardTable;
    @FXML
    private TableColumn<Map, String> playerNameColumn;
    @FXML
    private TableColumn<Map, Number> scoreColumn;
    @FXML
    private TableColumn<Map, Number> rankColumn;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setUpGame(Game game) {
        this.game = game;

        playerNameColumn.setCellValueFactory(new MapValueFactory<>("Player"));
        scoreColumn.setCellValueFactory(new MapValueFactory<>("Score"));
        rankColumn.setCellValueFactory(new MapValueFactory<>("Rank"));
        loadTableData();

    }

    private void loadTableData() {
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("playerScores.txt"))) {
            String line;
            int rank = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Map<String, Object> row = new HashMap<>();
                row.put("Rank", rank++);
                row.put("Player", parts[0]);
                row.put("Score", Integer.parseInt(parts[1]));
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        leaderboardTable.setItems(data);
    }





    @Override
    public void nextScene() {
        try {
            main.closeApplication();
        } catch (Exception e) {
            System.err.println("Error closing the application: " + e.getMessage());
        }
    }
}

