package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a coordinate in a grid
 */
public class Coord {

    private final int x;
    private final int y;

    /**
     * Constructs a coordinate
     *
     * @param x - the x position
     * @param y - the y position
     */
    @JsonCreator
    public Coord(
            @JsonProperty("x") int x,
            @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x position of this coordinate
     *
     * @return the x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y position of this coordinate
     *
     * @return the y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Determines if two coordinates are the same
     *
     * @param that - the coordinate to compare to this
     * @return true, if they are the same, false otherwise
     */
    public boolean sameCoord(Coord that) {
        return (this.x == that.x && this.y == that.y);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Coord)) {
            return false;
        }
        Coord that = (Coord) other;
        return this.sameCoord(that);
    }

    //issue.
    @Override
    public int hashCode() {
        return this.x * 100 + this.y * 100;
    }


}
