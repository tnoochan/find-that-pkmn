package ftp.Model;

import java.util.Random;

/**
 * A basic pokemon generator (bpg) that generates 3 Pokemons
 */
public class BasicPokemonGenerator implements PokemonGenerator {

    private final Random rand;
    private final int numSpecies = 3;

    /**
     * Constructs a bpg using a seed
     *
     * @param rand - a seeded random object
     */
    public BasicPokemonGenerator(Random rand) {
        this.rand = rand;
    }

    /**
     * Constructs a random bpg
     */
    public BasicPokemonGenerator() {
        this.rand = new Random();
    }


    /**
     * Randomly generates a Pokemon between Scorbunny, Voltorb, or Arcanine
     *
     * @return - the randomly generated Pokemon
     */
    @Override
    public Pokemon generate() {
        int val = this.rand.nextInt(numSpecies);
        if (val == 0) {
            return new Scorbunny();
        } else if (val == 1) {
            return new Voltorb();
        } else {
            return new Arcanine();
        }
    }
}
