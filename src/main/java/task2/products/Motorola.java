package task2.products;

import lombok.Getter;

@Getter
public class Motorola {
    private final String nameCellphone;
    private final String model;

    public Motorola(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}
