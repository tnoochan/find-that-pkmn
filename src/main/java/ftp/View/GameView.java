package ftp.View;

import ftp.Controller.GameController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the main game screen
 */
public class GameView extends WindowView {

    /**
     * Constructs this view with the supplied GameController
     *
     * @param control - the GameController to operate this view
     */
    public GameView(GameController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/game-screen.fxml"));
        this.loader.setController(control);
    }

}
