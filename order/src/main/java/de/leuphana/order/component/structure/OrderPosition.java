package de.leuphana.order.component.structure;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import de.leuphana.order.configuration.OrderPositionKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "positionId")
@Entity
@Table(name = "order_position")
public class OrderPosition {

    @EmbeddedId
    private OrderPositionKey id;

    @Column(name = "positionId", nullable = false, insertable = false, updatable = false)
    private Long positionId;

    @Column(name = "articleId", nullable = false)
    private String articleId;

    @Column(name = "articleQuantity", nullable = false)
    private int articleQuantity;

    @Column(name = "price", nullable = false)
    private double price;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name="orderId")
    private Order order;

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}