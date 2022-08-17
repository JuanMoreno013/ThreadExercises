package task4.version2;

import lombok.Getter;
import task4.version1.ParallelMergeSort;

@Getter
public class MergeSortParallel implements Runnable {

    private final int[] array;

    private int firstLengthArray=0;


    public MergeSortParallel(int[] array, int firstLengthArray) {
        this.array = array;
        this.firstLengthArray = firstLengthArray;
    }

    public MergeSortParallel(int[] array) {
        this.array = array;
    }

    public static void merge(int[] firstHalf, int[] secondHalf, int[] array) {
        int currentIndexFirst = 0;
        int currentIndexSecond = 0;
        int currentIndexActualArray = 0;

        while (currentIndexFirst < firstHalf.length && currentIndexSecond < secondHalf.length) {
            if (firstHalf[currentIndexFirst] < secondHalf[currentIndexSecond]) {

                array[currentIndexActualArray] = firstHalf[currentIndexFirst];
                currentIndexActualArray++;
                currentIndexFirst++;

            } else {
                array[currentIndexActualArray] = secondHalf[currentIndexSecond];
                currentIndexActualArray++;
                currentIndexSecond++;
            }
        }

        while (currentIndexFirst < firstHalf.length) {
            array[currentIndexActualArray] = firstHalf[currentIndexFirst];
            currentIndexActualArray++;
            currentIndexFirst++;
        }

        while (currentIndexSecond < secondHalf.length) {
            array[currentIndexActualArray] = secondHalf[currentIndexSecond];
            currentIndexActualArray++;
            currentIndexSecond++;
        }

    }


    public void recursiveMergeSort(int[] array) {
//        while (array.length != firstLengthArray) {
            if (array.length > 1) {

                int mid = array.length / 2;
                int[] firstHalf = new int[mid];
                System.arraycopy(array, 0, firstHalf, 0, mid);

                int[] secondHalf = new int[array.length - mid];
                System.arraycopy(array, mid, secondHalf, 0, array.length - mid);




                recursiveMergeSort(firstHalf);
                recursiveMergeSort(secondHalf);

//                MergeSortParallel firstHalfTask = new MergeSortParallel(firstHalf);
//                MergeSortParallel secondHalfTask = new MergeSortParallel(secondHalf);

                merge(firstHalf, secondHalf, array);
            }
//        }

    }

    @Override
    public void run() {
        synchronized (this) {
            recursiveMergeSort(array);
        }

    }
}
