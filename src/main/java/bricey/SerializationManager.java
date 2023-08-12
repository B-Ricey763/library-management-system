package bricey;

import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class SerializationManager {
    private static final String FILE_PATH = "items.json";
    
    public static void saveItems(Map<Integer, LibraryItem> items) {
        Map<Integer, LibraryItem> map = new HashMap<Integer, LibraryItem>();
        Gson gson = new GsonBuilder()
                        .registerTypeAdapter(map.getClass(), new LibraryItemSerializer())
                        .registerTypeAdapter(map.getClass(), new LibraryItemDeserializer())
                        .create();

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
        Map<Integer, LibraryItem> items = new HashMap<Integer, LibraryItem>();
        TypeToken<Map<Integer, LibraryItem>> mapType = new TypeToken<Map<Integer, LibraryItem>>(){};
        Gson gson = new GsonBuilder()
                        .registerTypeAdapter(items.getClass(), new LibraryItemSerializer())
                        .registerTypeAdapter(items.getClass(), new LibraryItemDeserializer())
                        .create();
        
        try (FileReader reader = new FileReader(FILE_PATH)) {
            items = gson.fromJson(reader, items.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }    

        return items;
    }
}

// In the name of polymorphism, I had to add this
// crapload of boilerplate to get the serialization
// working right and not lose type info. 

class LibraryItemSerializer implements JsonSerializer<Map<Integer, LibraryItem>> {

    @Override
    public JsonElement serialize(Map<Integer, LibraryItem> items, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        items.forEach((id, item) -> {
            JsonObject itemJson = context.serialize(item).getAsJsonObject();
            itemJson.addProperty("type", item.getClass().getSimpleName().toLowerCase());

            jsonObject.add(id.toString(), itemJson);
        });

        return jsonObject;
    }
}

class LibraryItemDeserializer implements JsonDeserializer<Map<Integer, LibraryItem>> {

    @Override
    public Map<Integer, LibraryItem> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Map<Integer, LibraryItem> items = new HashMap<Integer, LibraryItem>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            int id = Integer.parseInt(entry.getKey());
            JsonObject itemJson = entry.getValue().getAsJsonObject();

            String type = itemJson.get("type").getAsString();
            switch (type) {
                case "book":
                    Book book = context.deserialize(itemJson, Book.class);
                    items.put(id, book);
                    break;
                default:
                    throw new JsonParseException("Unknown item type: " + type);
            }
        }
        return items;
    }
}
