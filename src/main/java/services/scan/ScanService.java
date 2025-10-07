package services.scan;

import com.google.common.eventbus.AsyncEventBus;
import event.scan.ScanningEvent;
import item.Item;
import item.ItemCalculator;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScanService implements IScanService {
    private final AsyncEventBus eventBus;
    private final ItemCalculator itemCalculator;

    @Inject
    public ScanService(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
        this.itemCalculator = new ItemCalculator();

        log.info("eventBus (hashCode): {}", eventBus.hashCode());
        eventBus.register(this);
    }

    public void scanned(Item item, int amount) {
        double cost = item.accept(itemCalculator);
        eventBus.post(new ScanningEvent(item.getName(), cost, amount));
    }
}
