package ftp.Controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ftp.Model.Json.GameRecordsJson;
import ftp.Model.Json.PairDeserializer;
import ftp.Model.JsonUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.util.Pair;

/**
 * Represents an operator that reads/write data associated with top records
 */
public class TopRecordsOperator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleModule module = new SimpleModule();

    /**
     * Writes to path data contianing the game's records
     *
     * @param grj - the records data
     * @param path - the path to write to
     */
    public static void write(GameRecordsJson grj, Path path) {
        JsonNode contents = JsonUtils.serializeRecord(grj);
        String str = contents.toString();
        try {
            Files.write(path, str.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("could not write to path");
        }
    }

    /**
     * Attempts to read from the path and extract top records data
     *
     * @param path - the path to read from
     * @return the top records data
     */
    public static GameRecordsJson read(Path path) {
        // custom deserializer
        module.addDeserializer(Pair.class, new PairDeserializer());
        mapper.registerModule(module);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Could not read from supplied path");
        }
        try {
            JsonParser parser = mapper.getFactory().createParser(path.toFile());
            return parser.readValueAs(GameRecordsJson.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }



}
