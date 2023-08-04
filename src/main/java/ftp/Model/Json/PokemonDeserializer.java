package ftp.Model.Json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ftp.Model.*;

import java.io.IOException;
import java.util.List;

public class PokemonDeserializer extends StdDeserializer<Pokemon> {

    public PokemonDeserializer() {
        this(null);
    }

    public PokemonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Pokemon deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
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
// https://stackoverflow.com/questions/44374706/jackson-deserialize-abstract-classes
