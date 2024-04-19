package groupsix.citywalk.controller;

import groupsix.citywalk.controller.Controller;
import groupsix.citywalk.maingame.Main;
import groupsix.citywalk.model.TransportMode;
import groupsix.citywalk.model.Trip;
import groupsix.citywalk.service.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;


public class EducationWindowController extends Controller {
    @FXML
    private Label educationLabel;
    @FXML
    private Label funFactLabel;
    @FXML
    private ImageView transportModeView;
    @FXML
    private Button CloseButton;


    public void setMain(Main main) {
        this.main = main;
    }

    public void setUpGame(Game game) {
        this.game = game;
        updateInfo();
    }

    // 暂存当前的Stage，当窗口被显示时会被设置
    private Stage currentStage;

    // 设置当前Stage的方法
    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
    }

    private void closeStage() {
        if (currentStage != null) {
            currentStage.close();
        }
    }

    // 用于一次性解析出所有TransportMode的方法
    private void updateInfo() {
        List<Trip> levelTrips = game.getCurrentLevel().getLevelTrips();
        if (!levelTrips.isEmpty()) {
            // 获取最新的Trip
            Trip latestTrip = levelTrips.get(levelTrips.size() - 1);
            // 获取这个Trip中的所有TransportMode
            List<TransportMode> transportModes = latestTrip.getTransportTaken();
            //一次性更新三个元素
            updateTransportModeView(transportModes);
            updateEducationLabel(transportModes);
            updateFunFactLabel(transportModes);

        }
    }


    // 更新交通方式图片，传入交通方式列表
    private void updateTransportModeView(List<TransportMode> transportModes) {
        // 这里只显示最后一个选择的交通方式
        if (!transportModes.isEmpty()) {
            TransportMode lastMode = transportModes.get(transportModes.size() - 1);
            String imageName = getImageNameForTransportMode(lastMode.getName());
            Image image = new Image(getClass().getResourceAsStream("/groupsix/citywalk/pics/" + imageName));
            transportModeView.setImage(image);
        }
    }


    // 根据交通方式名称返回对应的图片文件名
    private String getImageNameForTransportMode(String transportModeName) {
        String imageName = "";
        switch (transportModeName) {
            case "Walk":
                imageName = "walk.png";
                break;
            case "Bike":
                imageName = "bike.png";
                break;
            case "Bus":
                imageName = "bus.png";
                break;
            case "Luas":
                imageName = "luas.png";
                break;
            case "Dart":
                imageName = "train.png";
                break;
            case "Taxi":
                imageName = "taxi.png";
                break;
            default:
                imageName = "building_a.png"; // 如果没有匹配，使用默认图片
                break;
        }
        return imageName;
    }




    // 更新教育标签，传入交通方式列表
    private void updateEducationLabel(List<TransportMode> transportModes) {
        // 使用StringBuilder来构建关于每种交通方式的描述
        StringBuilder sb = new StringBuilder();
        for (TransportMode tm : transportModes) {
            sb.append("You chose ").append(tm.getName())
                    .append(", which is ")
                    .append(tm.isEcoFriendly() ? "eco-friendly.\nGreat choice for the planet!" : "not so eco-friendly.\nConsider greener options next time.")
                    .append(".\n");
        }
        educationLabel.setText(sb.toString());
    }

    // 更新趣味事实标签，传入交通方式列表,但是只需要显示最后一个对应的
    private void updateFunFactLabel(List<TransportMode> transportModes) {
        if (!transportModes.isEmpty()) {
            // 获取最后一个选择的交通方式
            TransportMode lastMode = transportModes.get(transportModes.size() - 1);
            // 获取对应的趣味事实
            String funFact = getFunFactForTransportMode(lastMode.getName(), new Random());
            // 设置趣味事实到Label上
            funFactLabel.setText(funFact);
        }
    }

    // 根据传入的交通方式名称，返回一个随机的fun fact
    private String getFunFactForTransportMode(String transportModeName, Random random) {
        switch (transportModeName) {
            case "Walk":
                return (random.nextBoolean() ?
                        "Did you know? \nWalking not only reduces environmental pollution but walking for 30 minutes can burn approximately 150 calories, \nhelping you maintain a healthy lifestyle." :
                        "Did you know? \nStudies show that people who walk to work burn an extra 124 calories per day on average compared to those who drive, \nwhich helps reduce urban traffic congestion and air pollution.");

            case "Bike":
                return (random.nextBoolean() ?
                        "Did you know?\n Cycling can reduce about 260 grams of CO2 emissions per kilometer,\nmaking it much more eco-friendly than driving!" :
                        "Did you know?\nCycling not only helps protect the environment, \nit can also burn up to 400 calories per hour, \nwhile enhancing your cardiovascular health.");

            case "Bus":
                return (random.nextBoolean() ?
                        "Did you know?\nChoosing the bus for your commute can significantly reduce the amount of personal vehicle emissions. Did you know that each bus trip can reduce carbon dioxide emissions by an average of 45%?" :
                        "Did you know?\nBuses are an effective way to reduce urban traffic. A full bus can take about 30 cars off the road.");

            case "Luas":
                return (random.nextBoolean() ?
                        "Did you know?\nRiding the Luas can help decrease urban air pollution—indeed, compared to personal cars, each trip on the Luas reduces greenhouse gas emissions by 65%." :
                        "Did you know?\nLuas users can save significant amounts on fuel and maintenance costs annually compared to driving, making it a more economical and eco-friendly option.");

            case "Dart":
                return (random.nextBoolean() ?
                        "Did you know?\nThe Dart, as an efficient public transport option, operates at three times the energy efficiency of a car. This means for the same distance, Dart's carbon emissions are only a third of a car's." :
                        "Did you know?\nPeople who use the Dart daily can reduce their carbon emissions by about one ton per year—this is crucial for combating climate change.");

            case "Taxi":
                return (random.nextBoolean() ?
                        "Did you know?\nChoosing a taxi once can generate up to five times more carbon emissions than a bus ride. This difference is even more pronounced in urban areas." :
                        "Did you know?\nTaxis in Dublin often idle in traffic jams, which not only increases fuel consumption but also significantly raises the carbon footprint of each trip.");

            default:
                return "No fun fact available for this mode of transport.";
        }
    }



    // 当nextScene方法被调用时，可以关闭模态窗口
    @Override
    public void nextScene() {
        try {
            closeStage(); // 关闭当前模态窗口
        } catch (Exception e) {
            System.out.println("Error transitioning to the score board screen: " + e.getMessage());
        }

    }

}
