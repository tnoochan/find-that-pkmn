package ftp.View;

import ftp.Controller.GameOverController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the game over screen
 */
public class GameOverView implements WindowView {

    private final FXMLLoader loader;

    /**
     * Constructs this view with the supplied GameOverController
     *
     * @param control - the GameOverController to operate this view
     */
    public GameOverView(GameOverController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/game-over.fxml"));
        this.loader.setController(control);
    }

    /**
     * Attempts to load the game over screen
     *
     * @return - the parent node for the game over screen
     * @throws IllegalStateException if the scene could not be loaded
     */
    @Override
    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("unable to load");
        }
    }
}
