package ftp.View;

import ftp.Controller.GameOverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class GameOverView implements WindowView {

    FXMLLoader loader;

    public GameOverView(GameOverController g) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/game-over.fxml"));
        this.loader.setController(g);
    }

    @Override
    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("unable to load");
        }
    }
}
