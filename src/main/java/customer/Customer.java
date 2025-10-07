package customer;


import item.Item;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import modules.checkout.SelfServiceCheckout;
import services.payment.method.enums.PaymentMethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Slf4j
public class Customer implements ICustomer {
    private final SelfServiceCheckout selfServiceCheckout;
    private final Map<String, Integer> barcodeList;
    private List<PaymentMethods> paymentMethods;

    public Customer(SelfServiceCheckout selfServiceCheckout) {
        log.info("Customer has been introduced: {}", this);
        this.selfServiceCheckout = selfServiceCheckout;
        this.barcodeList = new HashMap<>();
        initializeBarcodeList();
    }

    @Override
    public void touchScreen() {
        log.info("Touching Display");
        selfServiceCheckout.getDisplay().activateCheckout();
    }

    @Override
    public Item scanItem(String barcode, int amount) {
        log.info("Scanning Item");
        return selfServiceCheckout.getBarcodeScanner().scanning(barcode, amount);
    }

    @Override
    public boolean weighItem(Item item) {
        log.info("Weighing Item");
        return selfServiceCheckout.getScale().weighing(item);
    }

    @Override
    public void payment(PaymentMethods method) {
        log.info("Paying");
        selfServiceCheckout.getSelfServiceCheckoutFacade().finishPayment(method);
    }

    @Override
    public void initializeBarcodeList() {
        barcodeList.put("12345", 3);
        barcodeList.put("23456", 1);
        barcodeList.put("1", 1);
        barcodeList.put("34567", 2);
        barcodeList.put("56789", 5);
        barcodeList.put("89123", 3);
    }

    @Override
    public void initializePaymentMethods() {
        paymentMethods.add(PaymentMethods.CARD);
        paymentMethods.add(PaymentMethods.CASH);
        paymentMethods.add(PaymentMethods.MOBILE);

    }
}
