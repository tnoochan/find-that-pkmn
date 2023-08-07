package ftp.View;

import ftp.Controller.IntroController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 * Represents the view for the introduction screen
 */
public class IntroView implements WindowView {

    private final FXMLLoader loader;

    /**
     * Constructs this view with the supplied IntroController
     *
     * @param control - the IntroController to operate this view
     */
    public IntroView(IntroController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/intro-screen.fxml"));
        this.loader.setController(control);
    }

    /**
     * Attempts to load the introduction screen
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
