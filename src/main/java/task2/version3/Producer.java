package task2.version3;

import org.apache.log4j.Logger;
import task2.version3.storageProducts.PhoneStorage;
import task2.version3.storageProducts.MapImplementation;

import java.util.Random;


public class Producer implements Runnable {

    private final Logger logger = Logger.getLogger("Producer");
    private final PhoneStorage[] phoneStorage;
    private final MapImplementation<PhoneStorage> mapImplementation;


    public Producer(final PhoneStorage[] storage, final MapImplementation<PhoneStorage> mapImplementation) {
        this.phoneStorage = storage;
        this.mapImplementation = mapImplementation;

    }


    public void produceElement() {

        PhoneStorage phoneRandom = phoneStorage[new Random().nextInt(9)];

        for (PhoneStorage phoneLook : phoneStorage) {
            if (phoneLook.equals(phoneRandom)) {
                mapImplementation.put(phoneLook);

                break;
            }
        }

        logger.info("Producing --" + phoneRandom.getPhone().getNameCellphone() + " amount:" + phoneRandom.getAmount());
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                produceElement();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
