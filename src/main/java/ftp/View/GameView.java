package ftp.View;

import ftp.Controller.GameController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the main game screen
 */
public class GameView implements WindowView {

    private final FXMLLoader loader;

    /**
     * Constructs this view with the supplied GameController
     *
     * @param control - the GameController to operate this view
     */
    public GameView(GameController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/game-screen.fxml"));
        this.loader.setController(control);
    }

    /**
     * Attempts to load the game screen
     *
     * @return - the parent node for the game screen
     * @throws IllegalStateException if the scene could not be loaded
     */
    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load");
        }
    }

}
