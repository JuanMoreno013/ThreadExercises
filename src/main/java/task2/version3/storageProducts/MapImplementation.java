package task2.version3.storageProducts;

import lombok.Getter;
import lombok.ToString;
import task2.products.Phone;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Getter
@ToString
public class MapImplementation<T> {
    private final Map<Phone, AtomicInteger> productsMap;
    private Supplier<PhoneStorage> keyForMap;

    public MapImplementation(Map<Phone, AtomicInteger> productsMap) {
        this.productsMap = productsMap;
    }

    public void put(T phoneStorage) {

        PhoneStorage newPhoneStorage = (PhoneStorage) phoneStorage;
        keyForMap = () -> newPhoneStorage;

        productsMap.computeIfPresent(newPhoneStorage.getPhone(), (phoneKey, count) -> {
            count = newPhoneStorage.getAmount();
            count.getAndIncrement();

            return count;
        });

    }

    public T remove() {

        Supplier<PhoneStorage> secondKey = () -> getKeyForMap().get();
        PhoneStorage phoneRemoveStorage = secondKey.get();

        productsMap.computeIfPresent(phoneRemoveStorage.getPhone(), (phoneKey, count) -> {
            if (count.get() != 0) {
                count = phoneRemoveStorage.getAmount();
                count.getAndDecrement();
            }
            return count;
        });

        return (T) phoneRemoveStorage;
    }

}

