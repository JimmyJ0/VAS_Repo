<<<<<<< HEAD
package de.leuphana.order.component.structure;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_position")
public class OrderPosition {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long positionId;

@Column(name = "articleId", nullable = false)
private Long articleId;

@Column(name = "articleQuantity", nullable = false)
private int articleQuantity;

@Column(name = "price", nullable = false)
private double price;

@ManyToOne
private Order order;

public Long getPositionId() {
	return positionId;
}

public void setPositionId(Long positionId) {
	this.positionId = positionId;
}

public Long getArticleId() {
	return articleId;
}

public void setArticleId(Long articleId) {
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

=======
package de.leuphana.order.component.structure;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_position")
public class OrderPosition {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long positionId;

@Column(name = "articleId", nullable = false)
private Long articleId;

@Column(name = "articleQuantity", nullable = false)
private int articleQuantity;

@Column(name = "price", nullable = false)
private double price;

@ManyToOne
private Order order;

public Long getPositionId() {
	return positionId;
}

public void setPositionId(Long positionId) {
	this.positionId = positionId;
}

public Long getArticleId() {
	return articleId;
}

public void setArticleId(Long articleId) {
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

>>>>>>> refs/remotes/origin/development_dan
}