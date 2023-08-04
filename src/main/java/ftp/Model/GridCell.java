package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class GridCell extends Label {

    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 60;
    private static final int DEFAULT_INSET = 3;
    private static final Background BASE =
            new Background(new BackgroundFill(Color.rgb(211, 211, 211, .5),
                    CornerRadii.EMPTY, new Insets(DEFAULT_INSET)));
    private Coord pos;
    private Color curColor;

    @JsonCreator
    public GridCell(
            @JsonProperty("pos") Coord pos) {
        this.pos = pos;
        this.init();
    }

    public Coord getPos() {
        return this.pos;
    }

    public void init() {
        this.setDefaultHighlight();
        this.setMinHeight(LABEL_HEIGHT);
        this.setMinWidth(LABEL_WIDTH);
        this.setDefaultHighlight();
        this.setAlignment(Pos.CENTER);
    }

    public void setDefaultHighlight() {
        this.curColor = Color.rgb(211, 211, 211, .3);
        this.setBackground(BASE);
    }

    public void highlight() {
        this.curColor = Color.rgb(32, 178, 170, Math.min(this.curColor.getOpacity() + .2, 1));
        this.setBackground(
                new Background(
                        new BackgroundFill(this.curColor,
                CornerRadii.EMPTY, new Insets(DEFAULT_INSET))));
    }
}
