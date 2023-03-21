package hello.order.v2;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceV2 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

    @Counted("my.order")
    @Override
    public void order() {
        log.info("주문하기");
        stock.decrementAndGet();

    }

    @Counted("my.order")
    @Override
    public void cancel() {
        log.info("취소하기");
        stock.incrementAndGet();

    }

    @Override
    public AtomicInteger getStock() {
        log.info("재고 확인");
        return stock;
    }
}
