package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.SelfServiceCheckoutModule;
import customer.Customer;
import facade.SelfServiceCheckoutFacade;
import item.Item;
import modules.checkout.SelfServiceCheckout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.payment.method.enums.PaymentMethods;

import java.util.Map;
import java.util.Random;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) throws InterruptedException {
        SelfServiceCheckoutModule module = new SelfServiceCheckoutModule(3);
        Injector injector = Guice.createInjector(module);
        SelfServiceCheckoutFacade facade = injector.getInstance(SelfServiceCheckoutFacade.class);

        SelfServiceCheckout selfServiceCheckout = new SelfServiceCheckout(facade);
        Customer customer = new Customer(selfServiceCheckout);

        Random random = new Random();

        // Activate
        customer.touchScreen();

        // Iterate through Customers Shopping List
        // Thread.sleep is needed to prevent collisions and to assure a correct sequence
        for (Map.Entry<String, Integer> barcode : customer.getBarcodeList().entrySet()) {
            Thread.sleep(2);
            Item item = customer.scanItem(barcode.getKey(), barcode.getValue());
            Thread.sleep(2);
            customer.weighItem(item);
        }

        // Finish through Customer
        PaymentMethods[] method = PaymentMethods.values();
        customer.payment(method[random.nextInt(method.length)]);

    }
}
