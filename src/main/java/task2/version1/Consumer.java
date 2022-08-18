package task2.version1;

import org.apache.log4j.Logger;
import task2.version1.storageProducts.PhoneStorage;
import task2.version1.storageProducts.StorageQueue;


public class Consumer implements Runnable {

    final Logger logger = Logger.getLogger("Consumer");

    private final StorageQueue storageQueue;

    public Consumer(final StorageQueue storageQueue) {
        this.storageQueue = storageQueue;
    }

    public void decrease() {

        PhoneStorage phoneInsideQueue = storageQueue.poll();

        phoneInsideQueue.setAmount(phoneInsideQueue.getAmount() - 1);
        logger.info("-- Consuming --" + phoneInsideQueue.getPhone().getNameCellphone() + " amount:" + phoneInsideQueue.getAmount());
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                try {
                    decrease();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
