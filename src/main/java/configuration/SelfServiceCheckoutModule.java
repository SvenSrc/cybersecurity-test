package configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import services.payment.IPaymentService;
import services.payment.PaymentService;
import services.scan.IScanService;
import services.scan.ScanService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public class SelfServiceCheckoutModule extends AbstractModule {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final long shutdownWaitTime;

    protected void configure() {
        // eventBus
        bind(AsyncEventBus.class).toInstance(new AsyncEventBus(executorService));

        // repository

        // microservices
        bind(IScanService.class).to(ScanService.class);
        bind(IPaymentService.class).to(PaymentService.class);

    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService() {
        return executorService;
    }

    public synchronized void shutdown() {
        log.info("shutting down AsyncEventBus.");
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(shutdownWaitTime, TimeUnit.SECONDS)) {
                log.warn("ExecutorService did not terminate in time | forcing shutdown.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.error("shutdown interrupted | forcing shutdown.");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        log.info("shutdown complete.");
    }
}
