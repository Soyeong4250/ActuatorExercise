package hello.order.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceV1 implements OrderService {

    private final MeterRegistry registry;  // 마이크로미터 기능 제공 핵심 컴포넌트 (카운터, 게이지 등록 역할)
    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
        log.info("주문하기");
        stock.decrementAndGet();

        // Counter 생성
        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry).increment();  // 메서드가 호출될 떄마다 Counter의 값 1 증가
    }

    @Override
    public void cancel() {
        log.info("취소하기");
        stock.incrementAndGet();

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry).increment();
    }

    @Override
    public AtomicInteger getStock() {
        log.info("재고 확인");
        return stock;
    }
}
