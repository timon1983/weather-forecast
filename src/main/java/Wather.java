
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Wather {

    private Connect connect = new Connect();

    void run() throws IOException, ParseException {

        List<Integer> pressures = new ArrayList<>();
        Map<String,Double> temperatureDifference = new HashMap<>();
        String content = connect.getUrlContent("https://api.openweathermap.org/data/2.5/forecast?q=Ufa&appid=4e48d1efabbd2aea568608385ec44833");

        if(!content.isEmpty()){
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(int i=0; i<jsonArray.length(); i++){
                pressures.add(jsonArray.getJSONObject(i).getJSONObject("main").getInt("pressure"));
                double tempMax = jsonArray.getJSONObject(i).getJSONObject("main").getDouble("temp_max") - 273.16;
                double tempMin = jsonArray.getJSONObject(i).getJSONObject("main").getDouble("temp_min") - 273.16;
                String day = jsonArray.getJSONObject(i).getString("dt_txt");
                double difTemp = tempMax - tempMin;
                temperatureDifference.put(day,difTemp);
            }
            Map.Entry<String, Double> minEntry = null;
            for (Map.Entry<String, Double> entry : temperatureDifference.entrySet()) {
                if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                    minEntry = entry;
                }
            }
            String dayWithMinDifTemp = minEntry.getKey();
            double temp = minEntry.getValue();
            double lat = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lat");
            double lon = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lon");

            System.out.println(content);
            System.out.println("День с минимальной разницей между ночной (night) и утренней (morn) температурой: " + dayWithMinDifTemp + " ,разница: " + temp +" градусов Цельсия");
            System.out.println("Максимальное давление за предстоящие 5 дней: " + Collections.max(pressures));
            System.out.println("Долгота: " + lat + " / " + "Широта: " + lon);
        }
    }
}
