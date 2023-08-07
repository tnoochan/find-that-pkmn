package ftp.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.Test;


class BasicPokemonGeneratorTest {

    Random seed = new Random(3);
    PokemonGenerator gen = new BasicPokemonGenerator(seed);

    @Test
    void testGenerate() {
        Pokemon current = gen.generate();
        assertEquals("Arcanine", current.getName());
        current = gen.generate();
        assertEquals("Arcanine", current.getName());
        current = gen.generate();
        assertEquals("Scorbunny", current.getName());
        current = gen.generate();
        assertEquals("Voltorb", current.getName());
    }
}