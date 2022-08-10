package task2.version1;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import task2.version1.storageProducts.PhoneStorage;
import task2.version1.storageProducts.Storage;
import task2.version1.storageProducts.StorageQueue;

import java.util.Random;


public class Producer implements Runnable {

    final Logger logger = Logger.getLogger("Producer");

    private final StorageQueue storageQueue;

    final int queueLimit = 5;

    final PhoneStorage phoneStorage;


    public Producer(final StorageQueue storageQueue, Storage storage) {
        this.storageQueue = storageQueue;
        this.phoneStorage = storage.getStorages()[new Random().nextInt(9)];
    }


    public void increase() throws InterruptedException {

        synchronized (phoneStorage) {

            while (storageQueue.size() == queueLimit) {
//                logger.info("No items to handle");
                phoneStorage.wait();
            }
        }

        synchronized (phoneStorage) {

            phoneStorage.setAmount(phoneStorage.getAmount() + 1);
            storageQueue.add(phoneStorage);

            logger.info("Producing --" + phoneStorage.getPhone().getPhone() + " amount:" + phoneStorage.getAmount());
//            System.out.println("Producing --" + phoneStorage.getPhone().getPhone() + " amount:" + phoneStorage.getAmount());
            phoneStorage.notify();

            Thread.sleep(1000);
        }

    }

    @Override
    public void run() {
        BasicConfigurator.configure();
//        while (true) {
        synchronized (this) {
            try {
                increase();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
//        }
    }

}
