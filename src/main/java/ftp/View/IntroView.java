package ftp.View;

import ftp.Controller.IntroController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 * Represents the view for the introduction screen
 */
public class IntroView extends WindowView {

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
}
