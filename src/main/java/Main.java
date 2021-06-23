import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        Wather wather = new Wather();
        try {
            wather.run();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
