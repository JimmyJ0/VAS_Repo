package de.leuphana.order.configuration;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class OrderPositionKey implements Serializable {
	
	@Column(name = "positionId")
    private Long positionId;

    @Column(name = "orderId")
    private Long orderId;

    // Getters, setters, hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPositionKey)) return false;
        OrderPositionKey that = (OrderPositionKey) o;
        return positionId.equals(that.positionId) && orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, orderId);
    }
}
