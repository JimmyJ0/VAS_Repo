package de.leuphana.shop.structure.sales;

public class CartItem {
	private int cartItemId;
	private String articleId;
	private int quantity;
	private Double price;

	public CartItem() {
		quantity = 1;
	}

	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartId) {
		this.cartItemId = cartId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}
	
	public void decrementQuantity() {
		quantity--;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
