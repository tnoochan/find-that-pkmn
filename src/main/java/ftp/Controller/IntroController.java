package ftp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class IntroController {

    private SetupManager sceneMaster;

    @FXML
    private Label warning;
    @FXML
    private TextField textArea;
    @FXML
    private Button startButton;

    public IntroController(SetupManager s) {
        this.sceneMaster = s;
    }

    public void run() {
        this.warning.setVisible(false);
        this.initButtons();
    }

    private void initButtons() {
        this.startButton.setOnAction(e -> this.handleStart());
    }

    private void handleStart() {
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

    private void showWarning() {
        this.warning.setVisible(true);
        this.warning.setText("Please enter a name");
    }

}
