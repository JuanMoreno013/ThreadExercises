package task2.products;

import lombok.*;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Phone {
    private final String nameCellphone;
    private final String model;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return nameCellphone.equals(phone.nameCellphone) && model.equals(phone.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCellphone, model);
    }
}
