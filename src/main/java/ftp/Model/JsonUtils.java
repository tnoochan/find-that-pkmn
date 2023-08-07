package ftp.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json uiltity calss for serializing records
 */
public class JsonUtils {

    /**
     * Serializes the supplied record into a JsonNode
     *
     * @param record - the record to serialize
     * @return - a JsonNode with serialized contents from record
     * @throws IllegalArgumentException if record cannot be serialized
     */
    public static JsonNode serializeRecord(Record record)
            throws IllegalArgumentException {
        try {
            ObjectMapper mapper = new ObjectMapper();;
            return mapper.convertValue(record, JsonNode.class);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Given record cannot be serialized");
        }
    }
}
