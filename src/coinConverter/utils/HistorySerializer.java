package coinConverter.utils;

import coinConverter.models.Conversion;
import coinConverter.models.Conversor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import javax.swing.text.html.parser.Parser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HistorySerializer {

    private List<Conversion> conversionList = new ArrayList();

    public void recordCreation(Conversion conversion){
        conversionList.add(conversion);
    }

    public void saveRecords() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter writer = new FileWriter("conversionHistory.json");

        writer.append(gson.toJson(conversionList));

        writer.flush();
        writer.close();
    }
}
