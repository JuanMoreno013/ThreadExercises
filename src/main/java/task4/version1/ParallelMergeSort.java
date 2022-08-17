package task4.version1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.RecursiveAction;


@Getter
@AllArgsConstructor
public class ParallelMergeSort extends RecursiveAction {

    private int[] array;

    @Override
    protected void compute() {
        if (array.length > 1) {

            int mid = array.length / 2;
            int[] firstHalf = new int[mid];
            System.arraycopy(array, 0, firstHalf, 0, mid);

            int[] secondHalf = new int[array.length - mid];
            System.arraycopy(array, mid, secondHalf, 0, array.length - mid);

            ParallelMergeSort firstHalfTask = new ParallelMergeSort(firstHalf);
            ParallelMergeSort secondHalfTask = new ParallelMergeSort(secondHalf);

            invokeAll(firstHalfTask, secondHalfTask);

            MergeSort.merge(firstHalf, secondHalf, array);


        }
    }
}
