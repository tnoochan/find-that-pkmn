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
    }

    @Test
    void testEquals() {
        assertEquals(c2, c3);
        assertEquals(c3, c2);
        assertNotEquals(c1, c2);
    }

}
