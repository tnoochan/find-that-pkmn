package ftp.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import ftp.Controller.GameController;

/**
 * Record for the game state
 *
 * @param mainController the main game controller
 */
public record GameStateJson(
        @JsonProperty("mainController") GameController mainController) {
}
