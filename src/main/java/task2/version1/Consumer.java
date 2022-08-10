package task2.version1;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import task2.version1.storageProducts.PhoneStorage;
import task2.version1.storageProducts.StorageQueue;


public class Consumer implements Runnable {

    final Logger logger = Logger.getLogger(this.getClass());

    private final StorageQueue storageQueue;

    public Consumer(final StorageQueue storageQueue) {
        this.storageQueue = storageQueue;
    }


    public void decrease() throws InterruptedException {

        PhoneStorage phoneStorage = storageQueue.poll();
        storageQueue.decreaseMapValue(phoneStorage);

        synchronized (phoneStorage) {
            while (storageQueue.isEmpty()) {
                phoneStorage.wait();
            }
        }

        synchronized (phoneStorage) {
            phoneStorage.setAmount(phoneStorage.getAmount() - 1);
            logger.info("Consuming " + phoneStorage.getPhone().getPhone() + " " + phoneStorage.getAmount());
//            System.out.println("---Consuming--- " + phoneStorage.getPhone().getPhone() + ", amount:  " + phoneStorage.getAmount());
            phoneStorage.notify();
        }

    }


    @Override
    public void run() {
        BasicConfigurator.configure();
//        while (true) {
        synchronized (this) {
            try {
                decrease();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
//        }
    }

}
