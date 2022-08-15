package task2.version2;

import org.apache.log4j.Logger;
import task2.version2.storageProducts.PhoneStorage;
import task2.version2.storageProducts.StorageQueue;

import java.util.Random;


public class Producer implements Runnable {

    private final Logger logger = Logger.getLogger("Producer");
    private final PhoneStorage[] phoneStorage;
    private final StorageQueue<PhoneStorage> storageQueue;


    public Producer(final PhoneStorage[] storage, final StorageQueue<PhoneStorage> storageQueue) {
        this.phoneStorage = storage;
        this.storageQueue = storageQueue;
    }


    public void produceElement() throws InterruptedException {

        PhoneStorage phoneRandom = phoneStorage[new Random().nextInt(9)];

        for (PhoneStorage phoneLook : phoneStorage) {
            if (phoneLook.equals(phoneRandom)) {
                storageQueue.put(phoneLook);
                break;
            }
        }

        logger.info("Producing --" + phoneRandom.getPhone().getNameCellphone() + " amount:" + phoneRandom.getAmount());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                produceElement();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
