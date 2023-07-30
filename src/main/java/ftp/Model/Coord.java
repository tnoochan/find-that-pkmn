package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {
    private final int x;
    private final int y;

    @JsonCreator
    public Coord(
            @JsonProperty("x") int x,
            @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

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

    @Override
    public int hashCode() {
        return this.x * 100 + this.y * 100;
    }


}
