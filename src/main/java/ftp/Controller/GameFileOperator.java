package ftp.Controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ftp.Model.Json.GameStateJson;
import ftp.Model.Json.PokemonDeserializer;
import ftp.Model.JsonUtils;
import ftp.Model.Pokemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameFileOperator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleModule module = new SimpleModule();

    public static void write(GameStateJson gsj, Path path) {
        JsonNode contents = JsonUtils.serializeRecord(gsj);
        String str = contents.toString();
        try {
            Files.write(path, str.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("could not write to path");
        }
    }

    public static GameStateJson read(Path path) {
        module.addDeserializer(Pokemon.class, new PokemonDeserializer());
        mapper.registerModule(module);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Could not read from supplied path");
        }
        try {
            JsonParser parser = mapper.getFactory().createParser(path.toFile());
            return parser.readValueAs(GameStateJson.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }



}
