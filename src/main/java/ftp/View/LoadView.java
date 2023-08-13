package ftp.View;

import ftp.Controller.LoadController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the load game screen
 */
public class LoadView extends WindowView {

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
}
