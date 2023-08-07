package ftp.Model.Json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.util.Pair;
import java.io.IOException;

/**
 * Deserializer for Pair class
 */
public class PairDeserializer extends StdDeserializer<Pair<String, Integer>> {

    /**
     * Constructs deserializer
     */
    public PairDeserializer() {
        this(null);
    }

    /**
     * Constructs deserializer
     * @param vc -
     */
    public PairDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserializes with given arguments
     *
     * @param p Parsed used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *     this deserialization activity.
     *
     * @return a Pair mapping string to integer
     * @throws IOException - if io exception occurs
     * @throws JacksonException - if jackson exception occurs
     */
    @Override
    public Pair<String, Integer> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        return new Pair<>(node.get("key").asText(), node.get("value").asInt());
    }
}
