package de.leuphana.shop.structure.billing;

public class InvoicePosition {
	private Integer positionId;
	private String articleId;
	private Double articlePrice;
	private int articleQuantity;
	private Double totalPrice;

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Double getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(Double articlePrice) {
		this.articlePrice = articlePrice;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

	public Double getTotalPrice() {
		return articleQuantity * articlePrice;
	}

}
