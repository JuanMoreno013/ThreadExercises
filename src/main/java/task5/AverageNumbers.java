package task5;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class AverageNumbers implements Callable<Integer> {

    private final List<Integer[]> listArrayNumbers;

    private final int numberIndex;

    private final AtomicInteger sumValues = new AtomicInteger();


    public AverageNumbers(final List<Integer[]> listArrayNumbers, final int numberIndex) {
        this.listArrayNumbers = listArrayNumbers;
        this.numberIndex = numberIndex;
    }

    public void summingValues() {
        Integer[] arrayNumberNew =  listArrayNumbers.get(numberIndex);

        for (Integer integer : arrayNumberNew) {
            sumValues.getAndAdd(integer);
        }
    }

    @Override
    public Integer call()  {
        summingValues();

        return  sumValues.intValue();
    }


}
