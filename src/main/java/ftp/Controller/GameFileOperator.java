package ftp.Controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ftp.Model.Json.GameStateJson;
import ftp.Model.Json.PairDeserializer;
import ftp.Model.Json.PokemonDeserializer;
import ftp.Model.JsonUtils;
import ftp.Model.Pokemon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.util.Pair;

/**
 * Represents an object that operates the game files
 */
public class GameFileOperator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleModule module = new SimpleModule();

    /**
     * Writes the game data to the path
     *
     * @param gsj - the game data to be saved
     * @param path - the path to write the data to
     */
    public static void write(GameStateJson gsj, Path path) {
        JsonNode contents = JsonUtils.serializeRecord(gsj);
        String str = contents.toString();
        try {
            Files.write(path, str.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("could not write to path");
        }
    }

    /**
     * Reads from the path and returns the game's data
     *
     * @param path - the path to read from
     * @return - the game's data as a GameStateJson
     */
    public static GameStateJson read(Path path) {
        // custom deserializes for abstract/interfaces
        module.addDeserializer(Pokemon.class, new PokemonDeserializer());
        module.addDeserializer(Pair.class, new PairDeserializer());
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
