package modules.scale;

import item.Item;
import lombok.extern.slf4j.Slf4j;
import modules.checkout.SelfServiceCheckout;
import modules.enums.Status;

@Slf4j
public class Scale implements IScale {
    private final SelfServiceCheckout selfServiceCheckout;

    private Status status;
    private double currentWeightOnScale;

    public Scale(SelfServiceCheckout selfServiceCheckout) {
        log.info("Scale has been initialized: {}", this);
        activate();
        calibrate();

        this.selfServiceCheckout = selfServiceCheckout;
    }

    @Override
    public boolean weighing(Item item) {
        if (item == null) {
            log.info("Cannot weigh Item that doesn't exist");
            return false;
        } else {
            log.info("Weighing: {}", item);
            currentWeightOnScale = item.getWeight();

            Item containsInShoppingList = selfServiceCheckout.getCustomerShoppingList()
                    .stream()
                    .filter(i -> i.equals(item))
                    .findFirst()
                    .orElse(null);

            if (item.getWeight() == containsInShoppingList.getWeight()) {
                selfServiceCheckout.getDisplay().showMessage("Correct Item");
                calibrate();
                return true;
            } else {
                selfServiceCheckout.getDisplay().showMessage("Item hasn't been placed, manipulated or it is the wrong item");
                calibrate();
                return false;
            }
        }

    }

    @Override
    public void calibrate() {
        this.currentWeightOnScale = 0;
        log.info("Scale has been calibrated. Current weight: {}", this.currentWeightOnScale);
    }

    @Override
    public void activate() {
        this.status = Status.Active;
        log.info("Scale Status is set to: {}", this.status);
    }

    @Override
    public void deactivate() {
        this.status = Status.Inactive;
        log.info("Scale Status is set to: {}", this.status);

    }
}
