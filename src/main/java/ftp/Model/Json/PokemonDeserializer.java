package ftp.Model.Json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ftp.Model.Coord;
import ftp.Model.Pokemon;
import ftp.Model.Scorbunny;

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
        } else {
            throw new RuntimeException("bad pokemon");
        }
    }
}
// https://stackoverflow.com/questions/44374706/jackson-deserialize-abstract-classes
