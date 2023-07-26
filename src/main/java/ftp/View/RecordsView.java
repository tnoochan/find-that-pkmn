package ftp.View;

import ftp.Controller.RecordsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class RecordsView implements WindowView{
    private FXMLLoader loader;

    public RecordsView(RecordsController r) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("top-records.fxml"));
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
