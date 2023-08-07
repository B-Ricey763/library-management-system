package bricey;

import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class SerializationManager {
    private static final String FILE_PATH = "items.json";
    
    public static void saveItems(Map<Integer, LibraryItem> items) {
        Gson gson = new Gson();

        // Thank you google, for making
        // such a simple package to convert to json!
        // Java may have hope after all...
        String json = gson.toJson(items);
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, LibraryItem> loadItems() {
        Gson gson = new Gson();
        Map<Integer, LibraryItem> items = new HashMap<Integer, LibraryItem>();
        
        try (FileReader reader = new FileReader(FILE_PATH)) {
            TypeToken<Map<Integer, LibraryItem>> mapType = new TypeToken<Map<Integer, LibraryItem>>(){};
            items = gson.fromJson(reader, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }    

        return items;
    }
}
