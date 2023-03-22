package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Timed(value = "my.order")
@RequiredArgsConstructor
public class OrderServiceV4 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
        log.info("주문하기");
        stock.decrementAndGet();
        sleep(500);
    }

    @Override
    public void cancel() {
        log.info("취소하기");
        stock.incrementAndGet();
        sleep(200);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}