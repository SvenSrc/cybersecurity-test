package item;

import lombok.Getter;

@Getter
public class AgeRestrictedItem extends Item {
    private final double price;
    private final int age;

    public AgeRestrictedItem(String name, double weight, double price, int age) {
        super(name, weight);
        this.price = price;
        this.age = age;
    }

    @Override
    public double accept(IArticleType articleType) {
        return articleType.calculate(this);
    }
}
