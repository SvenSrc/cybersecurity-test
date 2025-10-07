package modules.checkout;

import database.Database;
import facade.SelfServiceCheckoutFacade;
import item.Item;
import item.ItemCalculator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import modules.display.Display;
import modules.enums.Status;
import modules.scale.Scale;
import modules.scanner.BarcodeScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class SelfServiceCheckout implements ISelfServiceCheckout {
    private BarcodeScanner barcodeScanner;
    private Scale scale;
    private Display display;
    private Database databaseReader;
    private ItemCalculator itemCalculator;
    private SelfServiceCheckoutFacade selfServiceCheckoutFacade;

    private Status status;
    private Map<String, Item> database;
    private List<Item> customerShoppingList;
    private double totalSum;

    public SelfServiceCheckout(SelfServiceCheckoutFacade facade) {
        log.info("Self-Service Checkout has been initialized: {}", this);
        idle();

        this.databaseReader = new Database();
        initializeDatabase();
        initializeCustomerShoppingList();
        totalSum = 0;

        this.selfServiceCheckoutFacade = facade;
        this.scale = new Scale(this);
        this.barcodeScanner = new BarcodeScanner(this);
        this.display = new Display(this);
        this.itemCalculator = new ItemCalculator();
    }

    @Override
    public void initializeDatabase() {
        this.database = databaseReader.readData();
        log.info("Database has been read: {}", this.database.size());
    }

    @Override
    public void initializeCustomerShoppingList() {
        this.customerShoppingList = new ArrayList<>();
        log.info("Customer Shopping List has been created: {}", this.customerShoppingList);
    }

    @Override
    public void addToShoppingList(Item item) {
        customerShoppingList.add(item);
        log.info("Item has been added to the Customer Shopping list: {}", item);
    }


    @Override
    public void activate() {
        status = Status.Active;
        log.info("Self-Service Checkout Status is set to: {}", this.status);
    }

    @Override
    public void deactivate() {
        status = Status.Inactive;
        log.info("Self-Service Checkout Status is set to: {}", this.status);
    }

    @Override
    public void idle() {
        status = Status.Waiting;
        log.info("Self-Service Checkout Status is set to: {}", this.status);

    }
}
