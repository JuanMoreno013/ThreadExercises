package task2.products;

import lombok.Getter;

@Getter
public class Oppo {
    private final String nameCellphone;
    private final String model;

    public Oppo(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}
