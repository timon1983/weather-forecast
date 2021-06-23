import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Connect {

    String getUrlContent(String urlAdress) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlAdress);
        URLConnection urlConnection = url.openConnection();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
        }catch (IOException e){
            System.out.println("No info about this city");
        }
        return content.toString();
    }
}
