package ftp.View;

import ftp.Controller.RecordsController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the records screen
 */
public class RecordsView extends WindowView {

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

}
