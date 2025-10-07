package item;

import lombok.Getter;

@Getter
public class NormalItem extends Item {
    private final double price;

    public NormalItem(String name, double weight, double price) {
        super(name, weight);
        this.price = price;
    }

    @Override
    public double accept(IArticleType articleType) {
        return articleType.calculate(this);
    }
}
