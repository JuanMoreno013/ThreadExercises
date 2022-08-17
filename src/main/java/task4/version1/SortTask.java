package task4.version1;

import java.util.concurrent.ForkJoinPool;

public class SortTask {

    public static void parallelMergeSort(int[] array) {
        ParallelMergeSort mainTask = new ParallelMergeSort(array);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    public static void main(String[] args) {

        int[] array = {1, 23, 34, 2, 4, 5, 5, 9, 80, 3, 10};

        long startTime = System.currentTimeMillis();
        parallelMergeSort(array);

        long endTime = System.currentTimeMillis();

        System.out.println("Total computation time = " + (endTime - startTime) + " ms.");

        for (int a : array) {
            System.out.print(" " + a);
        }

    }
}
