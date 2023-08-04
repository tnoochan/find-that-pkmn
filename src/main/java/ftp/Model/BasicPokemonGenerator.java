package ftp.Model;

import java.util.Random;

public class BasicPokemonGenerator implements PokemonGenerator {

    private final Random rand;
    private final int numSpecies = 3;

    public BasicPokemonGenerator(Random rand) {
        this.rand = rand;
    }

    public BasicPokemonGenerator() {
        this.rand = new Random();
    }


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
