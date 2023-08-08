package ftp.Controller;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import ftp.View.BackgroundUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Represents the controller for the intro screen
 */
public class IntroController {

    private SetupManager sceneMaster;

    @FXML
    private Label warning;
    @FXML
    private TextField textArea;
    @FXML
    private Button startButton;
    @FXML
    private Label instruction;

    /**
     * Constructs this controller with the supplied setup manager
     *
     * @param control - the setup manager that manages the scenes
     */
    public IntroController(SetupManager control) {
        this.sceneMaster = control;
    }

    /**
     * Runs this controller
     */
    public void run() {
        this.warning.setVisible(false);
        this.initButtons();
    }

    /**
     * Initializes the button's action handlers and background
     */
    private void initButtons() {
        this.initBackground();
        this.startButton.setOnAction(e -> this.handleStart());
    }

    /**
     * Initializes the background
     */
    private void initBackground() {
        this.instruction.setBackground(BackgroundUtil.loadImage("src/main/resources/BackgroundFiles/instructions.png"));
    }

    /**
     * Handles when the user presses start
     */
    private void handleStart() {
        // see if user inputted proper game file name
        String input = textArea.getText();
        if (input.isEmpty()) {
            this.showWarning();
        } else {
            try {
                Path test = Path.of(input);
                this.sceneMaster.swapToGame(input);
            } catch (InvalidPathException e) {
                this.showWarning();
            }
        }
    }

    /**
     * Shows a warning message to the user
     */
    private void showWarning() {
        this.warning.setVisible(true);
        this.warning.setText("Please enter a name");
    }
}
