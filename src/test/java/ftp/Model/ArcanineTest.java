package ftp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArcanineTest {

    Pokemon p1;
    Pokemon p2;
    Pokemon p3;
    Pokemon p4;
    Pokemon p5;
    List<Coord> expected1 =
            new ArrayList<>(List.of(new Coord(2, 4), new Coord(4, 4),
                    new Coord(4, 3), new Coord(4, 2)));
    List<Coord> expected2 =
            new ArrayList<>(List.of(new Coord(1, 0), new Coord(2, 0), new Coord(3, 0),
                    new Coord(4, 0)));
    List<Coord> expected3 = new ArrayList<>(List.of(
            new Coord(0, 0), new Coord(0, 1), new Coord(1, 1), new Coord(1, 0)));
    List<Coord> expected4 = new ArrayList<>(List.of(
            new Coord(1, 1), new Coord(1, 1), new Coord(0, 1), new Coord(0, 1)));

    @BeforeEach
    void setup() {
        p1 = new Arcanine(new Random(5));
        p2 = new Arcanine();
        p3 = new Arcanine(new Random(12345));
        p4 = new Arcanine(new Random(5));
        p5 = new Arcanine(new Random(12345));

    }

    @Test
    void testTakeSteps() {
        assertEquals(0, p1.getSteps().size());
        assertEquals(0, p3.getSteps().size());
        p1.takeSteps(4, 5, 5);
        p3.takeSteps(4, 5, 5);
        // vertical movement
        assertEquals(expected1, p1.getSteps());
        // horizontal
        assertEquals(expected2, p3.getSteps());
        // tighter constraints for vertical
        p4.takeSteps(4, 2, 2);
        assertEquals(expected3, p4.getSteps());
        // tighter constraints for horizontal
        p5.takeSteps(4, 2, 2);
        assertEquals(expected4, p5.getSteps());
    }

    @Test
    void testGetName() {
        assertEquals("No name", p1.getName());
        assertEquals("Arcanine", p2.getName());
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
        assertEquals("Loves to run in straight lines", p2.getDescription());
    }
}