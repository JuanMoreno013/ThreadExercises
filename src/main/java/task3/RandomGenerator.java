package task3;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
public class RandomGenerator {

    private final AtomicLong seed;

    public RandomGenerator(AtomicLong seed) {
        this.seed = seed;
    }

    protected int numberGenerator(int boundNumber) {
        long oldSeed, nextSeed;

        while (true) {

            oldSeed = seed.get();
            nextSeed = calculateNext(oldSeed);
            if (seed.compareAndSet(oldSeed, nextSeed)) {

                long remainder = oldSeed % boundNumber;

                return (int) (remainder > 0 ? remainder : remainder + boundNumber);
            }

        }

    }

    private long calculateNext(long seedNumber) {
        seedNumber ^= seedNumber << 6;
        seedNumber ^= seedNumber >>> 21;
        seedNumber ^= (seedNumber << 7);

        return seedNumber;

    }
}
