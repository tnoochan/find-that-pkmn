package ftp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class VoltorbTest {

    Pokemon p1;
    Pokemon p2;
    Pokemon p3;
    List<Coord> expected1 = new ArrayList<>(List.of(
            new Coord(2, 4), new Coord(2, 5),
            new Coord(2, 4), new Coord(3, 4)));
    List<Coord> expected2 = new ArrayList<>(List.of(
            new Coord(0, 0), new Coord(0, 0),
            new Coord(0, 0), new Coord(0, 0),
            new Coord(0, 0), new Coord(0, 0)));

    @BeforeEach
    void setup() {
        p1 = new Voltorb(new Random(5));
        p2 = new Voltorb();
        p3 = new Voltorb(new Random(1234));
    }

    @Test
    void testTakeSteps() {
        assertEquals(0, p1.getSteps().size());
        assertEquals(0, p3.getSteps().size());
        p1.takeSteps(4, 5, 6);
        assertEquals(expected1, p1.getSteps());
        // tighter constraints
        p3.takeSteps(6, 1, 1);
        assertEquals(expected2, p3.getSteps());
    }

    @Test
    void testGetName() {
        assertEquals("No name", p1.getName());
        assertEquals("Voltorb", p2.getName());
    }

    @Test
    void testGetVisible() {
        assertFalse(p1.getVisible());
        p1.setVisible(true);
        assertTrue(p1.getVisible());
    }

    @Test
    void testGetDescription() {
        assertEquals("No description", p1.getDescription());
        assertEquals("Loves to roll around in adjacent squares", p2.getDescription());
    }
}