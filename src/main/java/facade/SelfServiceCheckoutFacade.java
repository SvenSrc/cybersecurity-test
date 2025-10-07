package facade;

import com.google.common.eventbus.AsyncEventBus;
import event.payment.FinishPaymentEvent;
import item.Item;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import services.payment.IPaymentService;
import services.payment.method.enums.PaymentMethods;
import services.scan.IScanService;

@Slf4j
public class SelfServiceCheckoutFacade {
    private final AsyncEventBus eventBus;
    private final IScanService scanService;

    @Inject
    public SelfServiceCheckoutFacade(AsyncEventBus eventBus,
                                     IScanService scanService,
                                     IPaymentService paymentService
    ) {
        this.eventBus = eventBus;
        this.scanService = scanService;

        log.info("EventBus (hashcode) : {}", eventBus.hashCode());
        eventBus.register(this);
    }

    // Is used by Barcode Scanner
    public void scanned(Item item, int amount) {

        log.info("SelfServiceChechkoutFacade scan()");
        scanService.scanned(item, amount);

    }

    // Customer uses it
    public void finishPayment(PaymentMethods method) {
        log.info("SelfServiceChechkoutFacade finishPayment()");

        eventBus.post(new FinishPaymentEvent(method));


    }
}
