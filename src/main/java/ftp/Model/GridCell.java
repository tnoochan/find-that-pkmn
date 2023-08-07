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

/**
 * Represents a grid cell shown on a grid
 */
public class GridCell extends Label {

    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 60;
    private static final int DEFAULT_INSET = 3;
    private static final Background BASE =
            new Background(new BackgroundFill(Color.rgb(211, 211, 211, .8),
                    CornerRadii.EMPTY, new Insets(DEFAULT_INSET)));
    private Coord pos;
    private Color curColor;

    /**
     * Constructs a grid cell with the associated coordinate
     *
     * @param pos - the position of this cell
     */
    @JsonCreator
    public GridCell(
            @JsonProperty("pos") Coord pos) {
        this.pos = pos;
    }

    /**
     * Gets this cell's position
     *
     * @return - this cell's coordinate
     */
    public Coord getPos() {
        return this.pos;
    }

    /**
     * Initializes this grid cell's visual
     */
    public void init() {
        this.setDefaultHighlight();
        this.setMinHeight(LABEL_HEIGHT);
        this.setMinWidth(LABEL_WIDTH);
        this.setDefaultHighlight();
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the background of this cell to default
     */
    public void setDefaultHighlight() {
        this.curColor = Color.rgb(211, 211, 211, .3);
        this.setBackground(BASE);
    }

    /**
     * Highlights this cell by changing the background color
     */
    public void highlight() {
        // repeated calls to highlight darkens the opacity
        this.curColor = Color.rgb(32, 178, 170, Math.min(this.curColor.getOpacity() + .2, 1));
        this.setBackground(
                new Background(
                        new BackgroundFill(this.curColor,
                CornerRadii.EMPTY, new Insets(DEFAULT_INSET))));
    }
}
