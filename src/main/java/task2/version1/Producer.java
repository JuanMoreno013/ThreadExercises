package task2.version1;

import org.apache.log4j.Logger;
import task2.version1.storageProducts.PhoneStorage;
import task2.version1.storageProducts.StorageQueue;


import java.util.Random;


public class Producer implements Runnable {

    private final Logger logger = Logger.getLogger("Producer");
    private final PhoneStorage[] phoneStorage;
    private final StorageQueue storageQueue;


    public Producer(final PhoneStorage[] storage, StorageQueue storageQueue) {
        this.phoneStorage = storage;
        this.storageQueue = storageQueue;
    }

    private void increaseAmount(PhoneStorage phoneLook) {
        phoneLook.setAmount(phoneLook.getAmount() + 1);
    }

    public void increase() throws InterruptedException {

        PhoneStorage phoneRandom = phoneStorage[new Random().nextInt(9)];

        for (PhoneStorage phoneLook : phoneStorage) {
            if (phoneLook.equals(phoneRandom)) {
                increaseAmount(phoneLook);
                storageQueue.add(phoneLook);
                break;
            }
        }

        logger.info("Producing --" + phoneRandom.getPhone().getNameCellphone() + " amount:" + phoneRandom.getAmount());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                try {
                    increase();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
