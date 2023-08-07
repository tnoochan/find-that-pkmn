package ftp.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import ftp.Controller.RecordsController;

/**
 * Record for the game's records
 *
 * @param recordsController the controller for the records,
 *                          which contains the top records
 */
public record GameRecordsJson(
        @JsonProperty("recordsController") RecordsController recordsController) {
}
