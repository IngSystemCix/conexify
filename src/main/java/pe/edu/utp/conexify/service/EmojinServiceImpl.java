package pe.edu.utp.conexify.service;

import jakarta.enterprise.context.Dependent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static pe.edu.utp.conexify.config.EnvironmentVariables.TOKEN_EMOJIN;

@Dependent
public class EmojinServiceImpl implements EmojinService {

    private static final Logger LOGGER = Logger.getLogger(EmojinServiceImpl.class.getName());
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    @Override
    public HashMap<String, List<String>> getAllEmojis(String category) {
        String URL = "https://emoji-api.com/categories/" + category + "?access_key=" + TOKEN_EMOJIN;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(URL))
                .build();

        // Creamos un HashMap con listas
        HashMap<String, List<String>> emojiMap = new HashMap<>();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());

                // Iterar sobre el array JSON para agregar los emojis a las listas dentro del HashMap
                IntStream.range(0, jsonArray.length()).forEach(i -> {
                    JSONObject emoji = jsonArray.getJSONObject(i);
                    String group = emoji.getString("group");
                    String character = emoji.getString("character");

                    // Si el grupo no existe en el mapa, lo inicializamos
                    emojiMap.putIfAbsent(group, new ArrayList<>());
                    emojiMap.get(group).add(character);
                });
                LOGGER.info("Emojis cargados correctamente");
            } else {
                LOGGER.warning("Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.severe("Error al consumir la API: " + e.getMessage());
        }

        return emojiMap;
    }
}
