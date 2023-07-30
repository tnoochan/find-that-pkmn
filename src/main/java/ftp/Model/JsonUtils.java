package ftp.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static JsonNode serializeRecord(Record record)
            throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();;
        return mapper.convertValue(record, JsonNode.class);
//        try {
//
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Given record cannot be serialized");
//        }
    }
}
