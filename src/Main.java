import coinConverter.UI.Menu;
import coinConverter.models.CoinExchange;
import coinConverter.utils.ApiConnector;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.startMenu();

    }
}
