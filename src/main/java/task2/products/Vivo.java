package task2.products;

import lombok.Getter;

@Getter
public class Vivo {
    private final String nameCellphone;
    private final String model;

    public Vivo(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}
