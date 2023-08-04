package ftp.View;

import ftp.Controller.LoadController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class LoadView implements WindowView {

    FXMLLoader loader;

    public LoadView(LoadController r) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(
                getClass().getClassLoader().getResource("FXMLFiles/load-game.fxml"));
        this.loader.setController(r);
    }


    @Override
    public Parent load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load");
        }
    }
}
