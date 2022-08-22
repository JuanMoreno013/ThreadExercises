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
        newPhoneStorage.getAmount().getAndIncrement();

        keyForMap = () -> newPhoneStorage;
        productsMap.replace(keyForMap.get().getPhone(), newPhoneStorage.getAmount());

    }

    public T remove() {

        Supplier<PhoneStorage> secondKey = () -> keyForMap.get();

        PhoneStorage phoneRemoveStorage = secondKey.get();
        if (phoneRemoveStorage != null) {
            phoneRemoveStorage.getAmount().getAndDecrement();

            productsMap.replace(phoneRemoveStorage.getPhone(), phoneRemoveStorage.getAmount());
            return (T) phoneRemoveStorage;
        }

        return null;
    }

}

