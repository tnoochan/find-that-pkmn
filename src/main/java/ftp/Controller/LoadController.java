package ftp.Controller;

import ftp.Model.Json.GameStateJson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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


    public LoadController(SetupManager s) {
        this.sceneMaster = s;
    }

    public void run() {
        this.initButtons();
        this.findFiles();
    }

    private void findFiles() {
        try {
            this.listOfSaveFiles = new File("savefiles").list();
            assert this.listOfSaveFiles != null;
            for (String str : this.listOfSaveFiles) {
                Label label = new Label(str);
                label.setOnMouseClicked(e -> this.handleSelect(label));
                this.entries.getChildren().add(label);
            }
        } catch (Exception e) {
            throw new RuntimeException("Issue accessing files in savefile directory");
        }
    }

    private void handleSelect(Label l) {
        this.confirmFile.setVisible(true);
        this.confirmFile.setText(l.getText());
        this.start.setVisible(true);

    }

    private void initButtons() {
        this.confirmFile.setVisible(false);
        this.start.setVisible(false);
        this.returnFromLoad.setOnAction(e -> this.sceneMaster.swapMainScreen());
        this.start.setOnAction(e -> this.handleStart());
    }

    private void handleStart() {
        Path path = Path.of("savefiles/" + this.confirmFile.getText());
        GameStateJson record = TxtFileOperator.read(path);
        this.sceneMaster.loadContent(record);
    }


}
