package groupsix.citywalk.api;
import groupsix.citywalk.service.Game.PlayerScore;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField playerNameField;

    @FXML
    private Label playerScoreLabel;

    @FXML
    private Button startGameButton;

    private Stage primaryStage;

    @FXML
    private TableView<PlayerScore> leaderboardTable;

    @FXML
    private TableColumn<PlayerScore, Integer> rankColumn;

    @FXML
    private TableColumn<PlayerScore, String> playerNameColumn;

    @FXML
    private TableColumn<PlayerScore, Number> scoreColumn;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    // 需绑定登录界面上的Start按钮
    private void handleStartGame(ActionEvent event) {
        String playerName = playerNameField.getText();
        if (playerName.isEmpty()) {
            // 如果用户名为空，可以显示一个警告信息或者提醒用户输入用户名
            System.out.println("Please enter your username.");
            return;
        }

        // 这里可以进行一些初始化游戏的操作，例如加载游戏数据等
        switchToGameScene(playerName);
    }

    private void switchToGameScene(String playerName) {
        // 切换到游戏界面的逻辑

    }

    // Update playerScoreLabel in gameFinish
    public void updatePlayerScoreLabel(String playerName, int score) {
        playerScoreLabel.setText(playerName + ", your total score is " + score);
    }

    @FXML
    private void initialize() {
        // Initialize ranking list
        rankColumn.setCellValueFactory(cellData -> {
            Integer rank = cellData.getValue() != null ? leaderboardTable.getItems().indexOf(cellData.getValue()) + 1 : null;
            return new ReadOnlyObjectWrapper<>(rank);
        });

        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("scoreSum"));

        // 加载排行榜数据...
        loadLeaderboardData();
    }

    // 从Game中save的文件获取排行榜数据
    private void loadLeaderboardData() {
        ObservableList<PlayerScore> data = FXCollections.observableArrayList();
        Path file = Paths.get("players_and_scores.txt");
        if (Files.exists(file)) {
            try (Scanner scanner = new Scanner(file.toFile())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 2) { // 确保每行都有两个部分
                        String name = parts[0];
                        int score;
                        try {
                            score = Integer.parseInt(parts[1].trim());
                            data.add(new PlayerScore(name, score));
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing score for player: " + name);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        leaderboardTable.setItems(data);
    }


}