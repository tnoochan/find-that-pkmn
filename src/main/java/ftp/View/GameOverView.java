package ftp.View;

import ftp.Controller.GameOverController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Represents the view for the game over screen
 */
public class GameOverView extends WindowView {

    /**
     * Constructs this view with the supplied GameOverController
     *
     * @param control - the GameOverController to operate this view
     */
    public GameOverView(GameOverController control) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/game-over.fxml"));
        this.loader.setController(control);
    }

}
