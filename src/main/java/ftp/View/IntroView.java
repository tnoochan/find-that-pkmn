package ftp.View;

import ftp.Controller.IntroController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class IntroView implements WindowView {
    private FXMLLoader loader;

    public IntroView(IntroController intro) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/intro-screen.fxml"));
        this.loader.setController(intro);
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
