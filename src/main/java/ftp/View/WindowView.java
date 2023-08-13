package ftp.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Represents view for an application window
 */
public abstract class WindowView {

    protected FXMLLoader loader;

    /**
     * Attempts to load the starting screen
     *
     * @return - the parent node for the starting screen
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
