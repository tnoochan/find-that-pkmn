package ftp.Model.Json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ftp.Model.Arcanine;
import ftp.Model.Pokemon;
import ftp.Model.Scorbunny;
import ftp.Model.Voltorb;
import java.io.IOException;

// Help from:
// https://stackoverflow.com/questions/44374706/jackson-deserialize-abstract-classes

/**
 * Deserializer for Pokemon abstract class
 */
public class PokemonDeserializer extends StdDeserializer<Pokemon> {

    /**
     * Constructs deserializer
     */
    public PokemonDeserializer() {
        this(null);
    }

    /**
     * Constructs deserializer
     *
     * @param vc -
     */
    public PokemonDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserializes with given arguments
     *
     * @param p Parsed used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *     this deserialization activity.
     *
     * @return the Pokemon extracted
     * @throws IOException - if an io exception occurs
     * @throws JacksonException - if a jackson exception occurs
     */
    @Override
    public Pokemon deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        if (node.get("name").asText().equals("Scorbunny")) {
            return mapper.treeToValue(node, Scorbunny.class);
        } else if (node.get("name").asText().equals("Voltorb")){
            return mapper.treeToValue(node, Voltorb.class);
        } else if (node.get("name").asText().equals("Arcanine")) {
            return mapper.treeToValue(node, Arcanine.class);
        } else {
            throw new UnsupportedOperationException(
                    "Could not deserialize, unable to map node to a pokemon");
        }
    }
}

