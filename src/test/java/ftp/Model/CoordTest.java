package ftp.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoordTest {

    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(-3, 7);
    Coord c3 = new Coord(-3, 7);
    Coord c4 = new Coord(1, 0);
    Coord c5 = new Coord(0, 1);

    @Test
    void testGetX() {
        assertEquals(0, c1.getX());
        assertEquals(-3, c2.getX());
    }

    @Test
    void testGetY() {
        assertEquals(0, c1.getY());
        assertEquals(7, c2.getY());
    }

    @Test
    void testSameCoord() {
        assertTrue(c2.sameCoord(c3));
        assertTrue(c3.sameCoord(c2));
        assertTrue(c2.sameCoord(c2));
        assertFalse(c1.sameCoord(c2));
        assertFalse(c1.sameCoord(c4));
        assertFalse(c1.sameCoord(c5));
    }

    @Test
    void testEquals() {
        assertEquals(c2, c3);
        assertEquals(c3, c2);
        assertNotEquals(c1, c2);
        assertFalse(c2.equals(new Arcanine()));
    }

}
