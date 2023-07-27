package ftp.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RecordsController {
    private SetupManager sceneMaster;

    @FXML
    private Button returnFromRecords;

    public RecordsController(SetupManager s) {
        this.sceneMaster = s;
    }

    public void run() {
        this.initButtons();
    }

    private void initButtons() {
        this.returnFromRecords.setOnAction(e ->
            this.sceneMaster.swapMainScreen());
    }

}
