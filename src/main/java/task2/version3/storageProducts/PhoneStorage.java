package task2.version3.storageProducts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import task2.products.Phone;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@ToString
@Getter
@Setter
public class PhoneStorage {

    private final Phone phone;
    private AtomicInteger amount = new AtomicInteger();


    public PhoneStorage(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneStorage that = (PhoneStorage) o;
        return Objects.equals(amount, that.amount) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, amount);
    }
}
