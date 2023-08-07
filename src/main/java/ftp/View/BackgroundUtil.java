package ftp.View;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A utility class for producing Backgrounds
 */
public class BackgroundUtil {

    /**
     * Produces a background by loading the image at the supplied path
     *
     * @param path - the path to the image to create a background of
     * @return - a background of the image
     */
    public static Background loadImage(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            return new Background(new BackgroundImage(new Image(stream),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "Could not initialize background, supporting file not found");
        }
    }
}
