package ftp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameOverController {

    private SetupManager sceneMaster;

    @FXML
    private Button exitButton;
    @FXML
    private Label finalScore;

    public GameOverController(SetupManager s) {
        this.sceneMaster = s;

    }

    public void setFinalScore(int finalScore) {
        this.finalScore.setText("Final score: " + Integer.toString(finalScore));
    }
    public void run() {
        this.initButtons();
    }

    private void initButtons() {
        this.exitButton.setOnAction(e -> this.handleExit());
    }

    private void handleExit() {
        this.sceneMaster.endGame();
    }

}
