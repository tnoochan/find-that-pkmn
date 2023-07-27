package ftp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    SetupManager sceneMaster;

    @FXML
    private Button newGame;
    @FXML
    private Button topRecords;
    @FXML
    private Button loadGame;

    public StartController(SetupManager s) {
        this.sceneMaster = s;
    }

    public void run() {
        this.initButtons();
    }

    private void initButtons() {
        this.newGame.setOnAction(e -> this.sceneMaster.swapToGame());
        this.topRecords.setOnAction(e -> this.sceneMaster.swapToRecords());
        this.loadGame.setOnAction(e -> this.sceneMaster.swapToLoadGame());
    }

}
