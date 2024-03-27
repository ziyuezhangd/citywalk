package groupsix.citywalk;
import java.util.ArrayList;
import java.util.List;

public class PublicTransportMode extends TransportMode {
    private List<Station> stations; // 存储该公共交通方式的经停站列表

    // 构造方法
    public PublicTransportMode(String name, int timeFactor, double carbonFactor) {
        super(name, timeFactor, carbonFactor);
        this.stations = new ArrayList<>();

        // 从 MapConfig 类中获取站点列表并转换为 Station 实例
        String[] stationNames = MapConfig.transportStops.get(name);
        if (stationNames != null) {
            for (String stationName : stationNames) {
                Station station = Station.allStations.get(stationName);
                if (station != null) {
                    this.stations.add(station);
                }
            }
        }
    }
    // 返回经停站列表
    public List<Station> listStations() {
        return stations;
    }

    // 检查某个站点是否在公共交通线路上
    public boolean checkStation(String stationName) {
        for (Station station : stations) {
            if (station.getStationName().equalsIgnoreCase(stationName)) {
                return true;
            }
        }
        return false;
    }
    public void showStationsList() {
        System.out.println("Stations List for " + this.getType() + ":");
        for (Station station : stations) {
            System.out.println(station.getName());
        }
    }

}

