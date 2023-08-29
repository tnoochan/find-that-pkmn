package ftp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ScorbunnyTest {

    Pokemon p1;
    Pokemon p2;
    List<Coord> expected1 = new ArrayList<>(List.of(new Coord(2, 0), new Coord(0, 2)));
    List<Coord> expected2 = new ArrayList<>(List.of(new Coord(2, 0), new Coord(0, 2), new Coord(6, 5)));

    @BeforeEach
    void setup() {
        p1 = new Scorbunny(new Random(5));
        p2 = new Scorbunny();
    }
    @Test
    void testTakeSteps() {
        assertEquals(0, p1.getSteps().size());
        p1.takeSteps(2, 4, 4);
        assertEquals(expected1, p1.getSteps());
        p1.takeSteps(1, 10, 20);
        assertEquals(expected2, p1.getSteps());
        p1.takeSteps(-1, 2, 3);
        assertEquals(expected2, p1.getSteps());
    }

    @Test
    void testGetName() {
        assertEquals("No name", p1.getName());
        assertEquals("Scorbunny", p2.getName());
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
        assertEquals("Loves to jump around", p2.getDescription());
    }

}