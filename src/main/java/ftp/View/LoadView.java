package ftp.View;

import ftp.Controller.LoadController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the load game screen
 */
public class LoadView implements WindowView {

    private final FXMLLoader loader;

    /**
     * Constructs this view with the supplied LoadController
     *
     * @param control - the LoadController to operate this view
     */
    public LoadView(LoadController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/load-game.fxml"));
        this.loader.setController(control);
    }

    /**
     * Attempts to load the load-game screen
     *
     * @return - the parent node for the load-game screen
     * @throws IllegalStateException if the scene could not be loaded
     */
    @Override
    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load");
        }
    }
}
