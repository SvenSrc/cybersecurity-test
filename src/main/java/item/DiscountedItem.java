package item;

import lombok.Getter;

@Getter
public class DiscountedItem extends Item {
    private final double originalPrice;
    private final double discountPercentage;

    public DiscountedItem(String name, double weight, double originalPrice, double discountPercentage) {
        super(name, weight);
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPrice() {
        return originalPrice * (1 - discountPercentage / 100);
    }

    @Override
    public double accept(IArticleType articleType) {
        return articleType.calculate(this);
    }
}
