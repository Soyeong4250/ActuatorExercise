package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    void order();
    void cancel();
    AtomicInteger getStock();  // AtomicInteger : 멀티쓰레드 환경에서 안전하게 값을 증가/감소시킬 수 있는 객체
}
