package ftp.View;

import ftp.Controller.RecordsController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the records screen
 */
public class RecordsView implements WindowView {

    private final FXMLLoader loader;

    /**
     * Constructs this view with the supplied RecordsController
     *
     * @param control - the RecordsController to operate this view
     */
    public RecordsView(RecordsController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/top-records.fxml"));
        this.loader.setController(control);
    }

    /**
     * Attempts to load the records screen
     *
     * @return - the parent node for the records screen
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
