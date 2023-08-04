package ftp.View;

import ftp.Controller.RecordsController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class RecordsView implements WindowView {
    private FXMLLoader loader;

    public RecordsView(RecordsController r) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/top-records.fxml"));
        this.loader.setController(r);
    }

    @Override
    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load");
        }
    }

}
