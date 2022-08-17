package task4.version1;

public class MergeSort {

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
}
