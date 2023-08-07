package ftp.View;

import javafx.scene.Parent;

/**
 * Represents view for an application window
 */
public interface WindowView {

    /**
     * Loads the scene associated with this view
     *
     * @return - a Parent node of which the scene is a part of
     * @throws IllegalStateException if the scene cannot be loaded
     */
    Parent load() throws IllegalStateException;

}
