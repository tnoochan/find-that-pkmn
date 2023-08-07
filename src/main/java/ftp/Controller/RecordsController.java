package ftp.Controller;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ftp.View.BackgroundUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Pair;


/**
 * Represents controller for the records view
 */
public class RecordsController {

    private static final int MAX_NUM_DISPLAY = 5;

    private SetupManager sceneMaster;

    // game file name : score
    private List<Pair<String, Integer>> records;

    @FXML
    private Button returnFromRecords;
    @FXML
    private VBox rankBox;
    @FXML
    private VBox nameBox;
    @FXML
    private VBox scoreBox;
    @FXML
    private AnchorPane backdrop;

    /**
     * Constructs this controller with the supplied records initialized
     *
     * @param records - list of previous game records
     */
    @JsonCreator
    public RecordsController(
            @JsonProperty("records") List<Pair<String, Integer>> records) {
        this.records = records;
    }

    /**
     * Constructs this controller with the supplied setup manager
     *
     * @param control - the setup manager that controls what scene to display
     */
    public RecordsController(SetupManager control) {
        this.sceneMaster = control;
        this.records = new ArrayList<>();
    }

    /**
     * Runs this controller
     */
    public void run() {
        this.initBackground();
        this.initButtons();
        this.displayRecords();
    }

    /**
     * Initializes the background
     */
    private void initBackground() {
        this.backdrop.setBackground(
                BackgroundUtil.loadImage("src/main/resources/BackgroundFiles/secondary-bkgrd.png"));
    }

    /**
     * Initializes the event action handlers for each button
     */
    private void initButtons() {
        this.returnFromRecords.setOnAction(e ->
            this.sceneMaster.swapMainScreen());
    }

    /**
     * Displays the records by sorting by score, and displaying in a rank system
     */
    private void displayRecords() {
        this.records.sort((pair1, pair2) -> pair2.getValue() - pair1.getValue());
        for (int i = 0; i < Math.min(MAX_NUM_DISPLAY, this.records.size()); i++) {
            Label rank = new Label(String.valueOf(i + 1));
            rank.setFont(Font.font("Kefa Regular"));
            Label score = new Label(String.valueOf(this.records.get(i).getValue()));
            score.setFont(Font.font("Kefa Regular"));
            Label name = new Label(this.records.get(i).getKey());
            name.setFont(Font.font("Kefa Regular"));
            this.rankBox.getChildren().add(rank);
            this.scoreBox.getChildren().add(score);
            this.nameBox.getChildren().add(name);
        }
    }

    /**
     * Adds the score and game file name to the list of records
     *
     * @param score - the score
     * @param name - the associating name
     */
    public void addEntry(int score, String name) {
        this.records.add(new Pair<>(name, score));
    }

    /**
     * Gets the list of records of this controller (for json)
     *
     * @return - the list of game records
     */
    public List<Pair<String, Integer>> getRecords() {
        return this.records;
    }

    /**
     * Initializes the setup manager
     *
     * @param control - the setup manager that manages the scenes of the game
     */
    public void initSceneMaster(SetupManager control) {
        this.sceneMaster = control;
    }

}
