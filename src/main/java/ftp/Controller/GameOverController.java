package ftp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Represents a controller for the game over scene
 */
public class GameOverController {

    private SetupManager sceneMaster;

    @FXML
    private Button exitButton;
    @FXML
    private Label finalScore;

    /**
     * Constructs this controller with the supplied setup manager
     *
     * @param control - the setup manager that manages the scenes
     */
    public GameOverController(SetupManager control) {
        this.sceneMaster = control;

    }

    /**
     * Sets the final score to be displayed
     *
     * @param finalScore - the final score of the game
     */
    public void setFinalScore(int finalScore) {
        this.finalScore.setText("Final score: " + Integer.toString(finalScore));
    }

    /**
     * Runs this controller
     */
    public void run() {
        this.initButtons();
    }

    /**
     * Initializes the button's event action handlers
     */
    private void initButtons() {
        this.exitButton.setOnAction(e -> this.handleExit());
    }

    /**
     * Handles when the user presses exit
     */
    private void handleExit() {
        this.sceneMaster.endApplication();
    }

}
