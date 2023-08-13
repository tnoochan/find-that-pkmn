package ftp.View;

import ftp.Controller.StartController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the starting screen
 */
public class StartView extends WindowView {
    /**
     * Constructs this view with the supplied StartController
     *
     * @param control - the StartController to operate this view
     */
    public StartView(StartController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/starting-screen.fxml"));
        this.loader.setController(control);
    }

}
