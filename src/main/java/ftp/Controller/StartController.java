package ftp.Controller;

import ftp.View.BackgroundUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Represents the controller for the starting scene
 */
public class StartController {

    SetupManager sceneMaster;

    @FXML
    private Button newGame;
    @FXML
    private Button topRecords;
    @FXML
    private Button loadGame;
    @FXML
    private AnchorPane backdrop;

    /**
     * Constructs this controller with the supplied setup manager
     *
     * @param control - the setup manager that manages the scenes
     */
    public StartController(SetupManager control) {
        this.sceneMaster = control;
    }

    /**
     * Runs this controller
     */
    public void run() {
        this.initButtons();
        this.initBackground();
    }

    /**
     * Initialize the event action handlers for each button
     */
    private void initButtons() {
        this.newGame.setOnAction(e -> this.sceneMaster.swapToIntro());
        this.topRecords.setOnAction(e -> this.sceneMaster.swapToRecords());
        this.loadGame.setOnAction(e -> this.sceneMaster.swapToLoadGame());
    }

    /**
     * Initializes the background
     */
    private void initBackground() {
        this.backdrop.setBackground(
                BackgroundUtil.loadImage("src/main/resources/BackgroundFiles/main-bkgrd.png"));
    }

}
