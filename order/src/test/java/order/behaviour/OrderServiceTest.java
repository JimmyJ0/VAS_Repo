package order.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.leuphana.order.component.behaviour.OrderService;
import de.leuphana.order.component.structure.Order;
import de.leuphana.order.component.structure.OrderPosition;
import de.leuphana.order.configuration.OrderPositionRepository;
import de.leuphana.order.configuration.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private OrderPositionRepository orderPositionRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setOrderId(1L);
        order.setCustomerId(100);

//        ArrayList<OrderPosition> orderPositions = new ArrayList<>();
//        orderPositions.add(new OrderPosition());
//        OrderPosition pos1 = new OrderPosition();
//        pos1.setArticleId("BK1");
//        pos1.setArticleQuantity(1);
//        pos1.setPrice(3.30);
//        pos1.setOrder(order);
//        
//        OrderPosition pos2 = new OrderPosition();
//        pos2.setArticleId("CD1");
//        pos2.setArticleQuantity(1);
//        pos2.setPrice(7.30);
//        pos2.setOrder(order);
//        
//        ArrayList<OrderPosition> orderPositions = new ArrayList<>();
//        orderPositions.add(pos1);
//        orderPositions.add(pos2);
        
//        order.setOrderPositions(orderPositions);
        
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
