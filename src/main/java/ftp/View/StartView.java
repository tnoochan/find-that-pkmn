package ftp.View;

import ftp.Controller.StartController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class StartView implements WindowView {
    private FXMLLoader loader;

    public StartView(StartController g) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
            getClass().getClassLoader().getResource("starting-screen.fxml"));
        this.loader.setController(g);
    }

    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load");
        }
    }
}
