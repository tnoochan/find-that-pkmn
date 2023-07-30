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
    private TextField textArea;
    @FXML
    private Label warning;

    public GameOverController(SetupManager s) {
        this.sceneMaster = s;
    }

    public void run() {
        this.initButtons();
    }

    private void initButtons() {
        this.exitButton.setOnAction(e -> this.handleExit());
    }

    private void handleExit() {
        if (textArea.getText().isEmpty()) {
            System.out.println("don't save");
        } else {
            System.out.println(textArea.getText());
            this.sceneMaster.endGame();
        }
    }

}
