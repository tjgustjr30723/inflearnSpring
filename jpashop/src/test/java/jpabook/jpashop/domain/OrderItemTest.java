package jpabook.jpashop.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class OrderItemTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;


    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();
        Item item = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 Order");
        assertEquals(1, getOrder.getOrderItems().size(), "상품 종류 수가 정확해야한다.");
        assertEquals(10000 * 2, getOrder.getTotalPrice(), "주문 가격은 수량  * 가격이다.");
        assertEquals(8, item.getStockQuantity(), "주문 수량만큼 재고가 줄어야한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember();
        Item item = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL이다.");
        assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야한다.");
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        Member member = createMember();
        Item item = createBook("시골 jpa", 10000, 10);

        int orderCount = 11;

        assertThrows(NotEnoughStockException.class,() -> orderService.order(member.getId(), item.getId(), orderCount));
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

}