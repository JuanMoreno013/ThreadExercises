package task4.version2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainSort2 {

    public static void main(String[] args) {


        int[] array = { 23, 1, 34, 2, 4, 5, 5, 9, 80, 3, 10};
        ExecutorService executorService = Executors.newWorkStealingPool();

//        Random rand = new Random();
//        int[] original = new int[90];
//
//        for(int i = 0; i< original.length; i++){
//            original[i] = rand.nextInt(1000);
//        }
//
//        System.out.println(" \n Old Array with " + original.length + " randoms numbers");
//        for (int a : original) {
//            System.out.print(" " + a);
//        }

        System.out.println(" \n Old Array");
        for (int a : array) {
            System.out.print(" " + a);
        }

//        executorService.execute(new MergeSortParallel(original));
        executorService.execute(new MergeSortParallel(array));

//        System.out.println(" \n Sorted Array");
//        for (int a : original) {
//            System.out.print(" " + a);
//        }

        System.out.println(" \n Sorted Array");
        for (int nA : array) {
            System.out.print(" " + nA);
        }
    }
}
