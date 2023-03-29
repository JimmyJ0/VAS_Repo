package de.leuphana.order.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.leuphana.order.component.structure.OrderPosition;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {


}
