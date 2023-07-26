package ftp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoadController {

    private SetupManager sceneMaster;

    @FXML
    private Button returnFromLoad;

    public LoadController(SetupManager s) {
        this.sceneMaster = s;
    }

    public void run() {
        this.initButtons();
    }

    private void initButtons() {
        this.returnFromLoad.setOnAction(e -> this.sceneMaster.swapMainScreen());
    }
}
