package de.leuphana.shop.services;

import de.leuphana.shop.structure.sales.Order;

public interface CustomerServices {
	
	public void addArticleToCart(Integer customerId, String articleId);
	
	public void removeArticleFromCart(Integer customerId, String articleId);
	
	public void changeArticleQuantity(Integer customerId, String articleId);
	
	public Order checkOutCart(Integer customerId);
	
	
}
