package task2.version3;

import org.apache.log4j.Logger;
import task2.version3.storageProducts.PhoneStorage;
import task2.version3.storageProducts.MapImplementation;


public class Consumer implements Runnable {

    private final Logger logger = Logger.getLogger("Consumer");

    private final MapImplementation<PhoneStorage> mapImplementation;

    public Consumer(final MapImplementation<PhoneStorage> mapImplementation) {
        this.mapImplementation = mapImplementation;
    }

    public void consumeElement() {

        PhoneStorage phoneToConsume = mapImplementation.remove();
        logger.info("-- Consuming --" + phoneToConsume.getPhone().getNameCellphone() + " amount:" + phoneToConsume.getAmount());

    }


    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                consumeElement();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
