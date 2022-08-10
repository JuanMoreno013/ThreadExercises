package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Xiaomi {
    private final String nameCellphone;
    private final String model;

    public Xiaomi(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}
