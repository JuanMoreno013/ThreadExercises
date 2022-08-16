package task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class MainGenerator {
    public static void main(String[] args) throws InterruptedException {

        AtomicLong seedLong = new AtomicLong(13);

        ExecutorService executorService = Executors.newWorkStealingPool();
        RandomGenerator randomGenerator = new RandomGenerator(seedLong);

        List<Callable<Object>> callables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                System.out.println(Thread.currentThread().getName() + " --Number generated: " + randomGenerator.numberGenerator(120));
                return null;
            });
        }

        executorService.invokeAll(callables);
    }
}
