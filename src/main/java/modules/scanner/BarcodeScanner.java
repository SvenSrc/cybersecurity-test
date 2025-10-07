package modules.scanner;

import item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import modules.checkout.SelfServiceCheckout;
import modules.enums.Status;

@Setter
@Getter
@Slf4j
public class BarcodeScanner implements IBarcodeScanner {
    private SelfServiceCheckout selfServiceCheckout;
    private Status status;

    public BarcodeScanner(SelfServiceCheckout selfServiceCheckout) {
        log.info("Barcode Scanner has been initialized: {}", this);
        this.selfServiceCheckout = selfServiceCheckout;
        activate();
    }

    @Override
    public Item scanning(String barcode, int amount) {
        if (selfServiceCheckout.getStatus() != Status.Active) {
            selfServiceCheckout.activate();
        }

        log.info("Scanning: {}, {} times", barcode, amount);
        Item item = selfServiceCheckout.getDatabase().get(barcode);

        if (item != null) {
            selfServiceCheckout.getDisplay().showMessage("Scanned Item: " + item);

            for (int i = 0; i < amount; i++) {
                selfServiceCheckout.addToShoppingList(item);
            }
            selfServiceCheckout.getSelfServiceCheckoutFacade().scanned(item, amount);

            selfServiceCheckout.getDisplay().showMessage("Please put the Item on the Scale");
            return item;
        } else {
            selfServiceCheckout.getDisplay().showMessage("Item not found for barcode: " + barcode);
            return null;
        }
    }

    @Override
    public void activate() {
        this.status = Status.Active;
        log.info("Barcode Scanner Status is set to: {}", this.status);
    }

    @Override
    public void deactivate() {
        this.status = Status.Inactive;
        log.info("Barcode Scanner Status is set to: {}", this.status);

    }
}
