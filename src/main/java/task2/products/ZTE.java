package task2.products;

import lombok.Getter;

@Getter
public class ZTE {


    private final String nameCellphone;
    private final String model;

    public ZTE(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}