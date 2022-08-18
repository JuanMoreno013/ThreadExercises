package task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MainAverage {

    public static <T> List<T[]> splitArrayInto4(T[] original, int size) {
        List<T[]> result = new ArrayList<>();
        if (original == null) return result;

        int curPos = 0;
        while (curPos < original.length) {
            int remaining = original.length - curPos + 1;
            if (remaining <= size) {
                result.add(Arrays.copyOfRange(original, curPos, original.length));
            } else {
                result.add(Arrays.copyOfRange(original, curPos, curPos + size));
            }
            curPos += size;
        }

        return result;
    }

    public static void main(String[] args) {

//                                    10,        36
//        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        Random rand = new Random();
        Integer[] array = new Integer[1_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1000);
        }


        ExecutorService poolExecutor = Executors.newFixedThreadPool(4);
        List<Integer[]> listArrayNumbers = splitArrayInto4(array, array.length / 4);

        for (Integer[] number : listArrayNumbers) {
            System.out.println(Arrays.toString(number));
        }

        long initialTime = System.currentTimeMillis();

        List<AverageNumbers> taskList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AverageNumbers averageNumbers = new AverageNumbers(listArrayNumbers, i);
            taskList.add(averageNumbers);
        }

        List<Future<Integer>> resultList = null;


        try {
            resultList = poolExecutor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        poolExecutor.shutdown();

        long sum = 0;
        assert resultList != null;

        for (Future<Integer> future : resultList) {
            try {
                Integer result = future.get();
                sum = result + sum;

            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        float averageValue = (float) sum / array.length;

        long finalTime = System.currentTimeMillis();

        System.out.println("\n Total numbers " + array.length +
                "\n Average of " + sum + " value is " + averageValue);


        System.out.println("\n Time execution: " + (finalTime - initialTime) + " ms ");
    }
}
