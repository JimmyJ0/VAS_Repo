package de.leuphana.shop.structure.sales;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.leuphana.shop.structure.article.Article;

public class Cart {
	
	private Map<String, CartItem> cartItems;
	private int numberOfArticles;

	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		numberOfArticles = 0;
	}

	public void addCartItem(String articleId, Article article) {
		CartItem cartItem;
		if (cartItems.containsKey(articleId)) {
			cartItem = cartItems.get(articleId);
			cartItem.incrementQuantity();
		} else {
			cartItem = new CartItem();
			cartItem.setPrice(article.getPrice()); 
			cartItem.setArticleId(articleId);
			cartItem.setCartItemId(cartItems.size() + 1);
			cartItems.put(articleId, cartItem);
		}
		numberOfArticles++;
	}

	public void deleteCartItem(String articleId) {
		for (CartItem cartItem : cartItems.values()) {
			if (cartItem.getArticleId().equals(articleId)) {
				cartItems.remove(articleId);
				break;
			}
		}
	}

	public void decrementArticleQuantity(String articleId) {
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
