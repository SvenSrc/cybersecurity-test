package item;

public class ItemCalculator implements IArticleType {

    @Override
    public double calculate(NormalItem item) {
        return item.getPrice();
    }

    @Override
    public double calculate(WeightedItem item) {
        return item.getTotalPrice();
    }

    @Override
    public double calculate(DiscountedItem item) {
        return item.getDiscountPrice();
    }

    @Override
    public double calculate(AgeRestrictedItem item) {
        return item.getAge();
    }
}
