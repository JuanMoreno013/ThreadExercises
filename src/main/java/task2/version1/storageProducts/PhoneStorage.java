package task2.version1.storageProducts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class PhoneStorage {
    @Getter
    private final Phone phone;
    @Setter
    @Getter
    private volatile int amount = 0;

}
