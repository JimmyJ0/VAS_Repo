package order.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.leuphana.order.component.behaviour.OrderService;
import de.leuphana.order.component.structure.Order;
import de.leuphana.order.configuration.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setOrderId(1L);
        order.setCustomerId(100L);
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals(order.getOrderId(), createdOrder.getOrderId());
        assertEquals(order.getCustomerId(), createdOrder.getCustomerId());
    }

    @Test
    public void testGetOrder() throws Exception {
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));

        Order fetchedOrder = orderService.getOrder(1L);

        assertNotNull(fetchedOrder);
        assertEquals(order.getOrderId(), fetchedOrder.getOrderId());
        assertEquals(order.getCustomerId(), fetchedOrder.getCustomerId());
    }

    @Test
    public void testGetOrderNotFound() {
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> orderService.getOrder(1L));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

}
