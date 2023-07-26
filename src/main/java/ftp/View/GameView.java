package ftp.View;

import java.io.IOException;

import ftp.Controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GameView implements WindowView {

    FXMLLoader loader;

    public GameView(GameController g) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("game-screen.fxml"));
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
