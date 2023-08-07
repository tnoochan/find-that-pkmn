package ftp.Controller;

import ftp.Model.Json.GameStateJson;
import ftp.View.BackgroundUtil;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/**
 * Represents the controller for the load screen
 */
public class LoadController {

    private SetupManager sceneMaster;
    String[] listOfSaveFiles;

    @FXML
    private Button returnFromLoad;
    @FXML
    private Label confirmFile;
    @FXML
    private Button start;
    @FXML
    private VBox entries;
    @FXML
    private AnchorPane backdrop;

    /**
     * Constructs this controller with the supplied setup manager
     *
     * @param control - the setup manager that manages the scenes
     */
    public LoadController(SetupManager control) {
        this.sceneMaster = control;
    }

    /**
     * Runs this controller
     */
    public void run() {
        this.initBackground();
        this.initButtons();
        this.findFiles();
    }

    /**
     * Attempts to remove the supplied game file name from savefiles
     *
     * @param str - the game file name to remove
     */
    public void remove(String str) {
        try {
            Files.delete(Path.of("savefiles/" + str));
        } catch (Exception e) {

        }
    }

    /**
     * Initializes the background
     */
    private void initBackground() {
        this.backdrop.setBackground(
                BackgroundUtil.loadImage("src/main/resources/BackgroundFiles/main-bkgrd.png"));
    }

    /**
     * Loads the savefiles by searching the file tree, and instructing
     * view elements to display their relevant data
     */
    private void findFiles() {
        try {
            this.listOfSaveFiles = new File("savefiles").list();
            assert this.listOfSaveFiles != null;
            for (String str : this.listOfSaveFiles) {
                Label label = new Label(str);
                label.setFont(Font.font("Kefa Regular"));
                label.setOnMouseClicked(e -> this.handleSelect(label));
                this.entries.getChildren().add(label);
            }
        } catch (Exception e) {
            throw new RuntimeException("Issue accessing files in savefile directory");
        }
    }

    /**
     * Handles what happens when the user selects a label
     *
     * @param label - the label the user clicked
     */
    private void handleSelect(Label label) {
        this.confirmFile.setVisible(true);
        this.confirmFile.setText(label.getText());
        this.start.setVisible(true);

    }

    /**
     * Initializes the buttons' action events
     */
    private void initButtons() {
        this.confirmFile.setVisible(false);
        this.start.setVisible(false);
        this.returnFromLoad.setOnAction(e -> this.sceneMaster.swapMainScreen());
        this.start.setOnAction(e -> this.handleStart());
    }

    /**
     * Handles when user presses start, loads the game data
     */
    private void handleStart() {
        Path path = Path.of("savefiles/" + this.confirmFile.getText());
        GameStateJson record = GameFileOperator.read(path);
        this.sceneMaster.loadContent(record);
    }


}
