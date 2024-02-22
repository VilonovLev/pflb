package org.task3;

import com.google.gson.*;

import java.io.*;
import java.nio.file.*;
import java.util.Optional;


public class Task3 {

    public static void main(String[] args) throws IOException {
        Path valuesPath = Path.of(args[0]);
        Path testPath = Path.of(args[1]);
        Path reportPath = Path.of(args[2]);

        Gson gson = new Gson();
        String testsJson = Files.readString(testPath);
        String valuesJson = Files.readString(valuesPath);

        JsonObject tests = gson.fromJson(testsJson, JsonObject.class);
        JsonArray valuesArray = gson.fromJson(valuesJson, JsonObject.class).getAsJsonArray("values");

        JsonObject result = extracted(tests, valuesArray);
        Files.writeString(reportPath,gson.toJson(result));
    }

    private static JsonObject extracted(JsonObject obj, JsonArray valuesArray) {
        if (obj.has("tests")) {
             extracted(obj.get("tests").getAsJsonArray(),valuesArray);
        }
        return obj;
    }

    private static JsonArray extracted(JsonArray tests, JsonArray valuesArray) {
        for (JsonElement element : tests) {
            JsonObject test = ((JsonObject) element);
            long id = test.get("id").getAsLong();
            if (test.has("value")) {
                test.add("value",getValue(id, valuesArray).get());
            }
            if (test.has("values")) {
                extracted(test.getAsJsonArray("values"),valuesArray);
            }
        }
        return tests;
    }

    private static Optional<JsonElement> getValue(long id, JsonArray valuesArray) {
        for (JsonElement element : valuesArray) {
            if (element instanceof JsonObject el) {
                if (id == el.get("id").getAsLong()) {
                    return Optional.of(el.get("value"));
                }
            }
        }
        return Optional.empty();
    }


}