package junit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.SelfServiceCheckoutModule;
import customer.Customer;
import facade.SelfServiceCheckoutFacade;
import item.DiscountedItem;
import item.Item;
import item.NormalItem;
import lombok.extern.slf4j.Slf4j;
import modules.checkout.SelfServiceCheckout;
import modules.enums.Status;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class SelfServiceCheckoutTest {

    private SelfServiceCheckoutFacade facade;
    private SelfServiceCheckout selfServiceCheckout;
    private Customer customer;


    @BeforeEach
    void setup() {
        SelfServiceCheckoutModule module = new SelfServiceCheckoutModule(3);
        Injector injector = Guice.createInjector(module);
        facade = injector.getInstance(SelfServiceCheckoutFacade.class);

        selfServiceCheckout = new SelfServiceCheckout(facade);
        customer = new Customer(selfServiceCheckout);

    }

    @Test
    @Order(1)
    public void activateByTouch() {
        customer.touchScreen();
        Assertions.assertEquals(Status.Active, selfServiceCheckout.getStatus());
    }

    @Test
    @Order(2)
    public void correctArticleIsScannedAndInserted() {
        Item expectedItem = new NormalItem("Chocolate", 1, 0.99);
        customer.scanItem("12345", 1);

        Assertions.assertEquals(expectedItem, selfServiceCheckout.getCustomerShoppingList().getFirst());
    }

    @Test
    @Order(3)
    public void correctAmountIsInserted() {
        Item expectedItem = new NormalItem("Phone", 0.5, 109);
        customer.scanItem("23456", 6);

        // Check the size of the list
        Assertions.assertEquals(6, selfServiceCheckout.getCustomerShoppingList().size());

        // Check if it is the expected Item
        for (Item item : selfServiceCheckout.getCustomerShoppingList()) {
            Assertions.assertEquals(expectedItem, item);
        }
    }

    @Test
    @Order(4)
    public void returnCorrectWeight() {
        Item scannedItem = customer.scanItem("34567", 1);

        // Should be 10, since 34567 has a weight of 10
        Assertions.assertTrue(selfServiceCheckout.getScale().weighing(scannedItem));
    }

    @Test
    @Order(5)
    public void correctDiscountCalculation() {
        DiscountedItem expectedItem = new DiscountedItem("Laptop", 3, 250, 25);

        Item scannedItem = customer.scanItem("56789", 1);
        double discountedPrice = selfServiceCheckout.getItemCalculator().calculate((DiscountedItem) scannedItem);

        Assertions.assertNotEquals(discountedPrice, expectedItem.getOriginalPrice());

    }
}
