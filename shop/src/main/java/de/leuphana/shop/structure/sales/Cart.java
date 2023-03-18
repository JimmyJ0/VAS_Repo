package de.leuphana.shop.structure.sales;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer, CartItem> cartItems;
	private int numberOfArticles;

	public Cart() {
		cartItems = new HashMap<Integer, CartItem>();
		numberOfArticles = 0;
	}

//	public void addCartItem(Article article) {
////		String id = article.getArticleId();
//		CartItem cartItem;
//		if (cartItems.containsKey(id)) {
//			cartItem = cartItems.get(id);
//			cartItem.incrementQuantity();
//		} else {
//			cartItem = new CartItem();
//			cartItems.put(id, cartItem);
//		}
//		numberOfArticles++;
//	}

	public void deleteCartItem(int articleId) {
		for (CartItem cartItem : cartItems.values()) {
			if (cartItem.getArticleId() == (articleId)) {
				cartItems.remove(cartItem.getCartItemId());
				break;
			}
		}
	}

	public void decrementArticleQuantity(Integer articleId) {
		if (cartItems.containsKey(articleId)) {
			CartItem cartItem = (CartItem) cartItems.get(articleId);
			cartItem.decrementQuantity();

			if (cartItem.getQuantity() <= 0)
				cartItems.remove(articleId);

			numberOfArticles--;
		}
	}

	public Collection<CartItem> getCartItems() {
		return cartItems.values();
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;

		for (CartItem cartItem : getCartItems()) {
			totalPrice += cartItem.getQuantity() * cartItem.getPrice();
		}

		return totalPrice;
	}

}
