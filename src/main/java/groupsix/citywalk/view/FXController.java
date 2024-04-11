//package groupsix.citywalk.view;
//import groupsix.citywalk.maingame.*;
//import groupsix.citywalk.service.Game.PlayerScore;
//import javafx.beans.property.ReadOnlyObjectWrapper;
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.shape.Path;
//import javafx.stage.Stage;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.event.ActionEvent;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Objects;
//import java.util.Scanner;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//
//public class FXController {
//    @FXML
//    TextField nameTextField;
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//
//    @FXML
//    private Label playerScoreLabel;
//
//
//    @FXML
//    private TableView<PlayerScore> leaderboardTable;
//
//    @FXML
//    private TableColumn<PlayerScore, Integer> rankColumn;
//
//    @FXML
//    private TableColumn<PlayerScore, String> playerNameColumn;
//
//    @FXML
//    private TableColumn<PlayerScore, Number> scoreColumn;
//
//
//    public void switchToScene1(ActionEvent event) throws IOException {
//
//        String username = (String) nameTextField.getText();
//
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @FXML
//
//
//    public void switchToGameMain(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void switchToScene3(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene3.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    public void switchToScene4(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene4.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void switchToSceneGameFinish(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameFinish.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//
//    // Update playerScoreLabel in gameFinish
//    public void updatePlayerScoreLabel(String playerName, int score) {
//        playerScoreLabel.setText(playerName + ", your total score is " + score);
//    }
//
//    @FXML
//    private void initialize() {
//        // Initialize ranking list
//        rankColumn.setCellValueFactory(cellData -> {
//            Integer rank = cellData.getValue() != null ? leaderboardTable.getItems().indexOf(cellData.getValue()) + 1 : null;
//            return new ReadOnlyObjectWrapper<>(rank);
//        });
//
//        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
//        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("scoreSum"));
//
//        // 加载排行榜数据...
//        loadLeaderboardData();
//    }
//
//    // 从Game中save的文件获取排行榜数据
//    private void loadLeaderboardData() {
//        ObservableList<PlayerScore> data = FXCollections.observableArrayList();
//        Path file = Path.get("players_and_scores.txt");
//        if (Files.exists(file)) {
//            try (Scanner scanner = new Scanner(((java.nio.file.Path) file).toFile())) {
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine();
//                    String[] parts = line.split(",");
//                    if (parts.length == 2) { // 确保每行都有两个部分
//                        String name = parts[0];
//                        int score;
//                        try {
//                            score = Integer.parseInt(parts[1].trim());
//                            data.add(new PlayerScore(name, score));
//                        } catch (NumberFormatException e) {
//                            System.err.println("Error parsing score for player: " + name);
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        leaderboardTable.setItems(data);
//    }
//
//
//}
//
