package ftp.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import ftp.Controller.GameController;
public record GameStateJson(
        @JsonProperty("mainController") GameController mainController) {
}
