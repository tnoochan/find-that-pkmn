package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class GridCell extends Label {

    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 60;
    private static final int DEFAULT_INSET = 3;

    private Coord pos;

    @JsonCreator
    public GridCell(
            @JsonProperty("pos") Coord pos) {
        this.pos = pos;
    }

    public Coord getPos() {
        return this.pos;
    }

    public void init() {
        this.setMinHeight(LABEL_HEIGHT);
        this.setMinWidth(LABEL_WIDTH);
        this.setDefaultHighlight();
    }

    public void highlight() {
        this.setBackground(new Background(
                new BackgroundFill(Color.rgb(32, 178, 170, .5),
                        CornerRadii.EMPTY, new Insets(DEFAULT_INSET))));
    }

    public void setDefaultHighlight() {
        this.setBackground(new Background(
                new BackgroundFill(Color.rgb(211, 211, 211, .5),
                        CornerRadii.EMPTY, new Insets(DEFAULT_INSET))));
    }
}
