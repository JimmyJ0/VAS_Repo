package de.leuphana.order.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.leuphana.order.component.structure.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}