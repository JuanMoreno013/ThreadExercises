package task2.version3;

import org.apache.log4j.Logger;
import task2.version3.storageProducts.PhoneStorage;
import task2.version3.storageProducts.StorageQueue;


public class Consumer implements Runnable {

    private final Logger logger = Logger.getLogger("Consumer");

    private final StorageQueue<PhoneStorage> storageQueue;

    public Consumer(final StorageQueue<PhoneStorage> storageQueue) {
        this.storageQueue = storageQueue;
    }

    public void consumeElement() {

        try {
            PhoneStorage phoneInsideQueue = storageQueue.take();

            logger.info("-- Consuming --" + phoneInsideQueue.getPhone().getNameCellphone() + " amount:" + phoneInsideQueue.getAmount());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                try {
                    consumeElement();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
