package task2.products;

import lombok.Getter;

@Getter
public class Huawei {
    private final String nameCellphone;
    private final String model;

    public Huawei(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}
