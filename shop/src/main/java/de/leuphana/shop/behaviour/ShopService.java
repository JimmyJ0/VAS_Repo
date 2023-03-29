package de.leuphana.shop.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.CartItem;
import de.leuphana.shop.structure.sales.Customer;
import de.leuphana.shop.structure.sales.Order;
import de.leuphana.shop.structure.sales.OrderPosition;

@Service
public class ShopService {

	// TODO: Anhand der Response Entity Exception Handling machen.
	private static final Logger LOG = LoggerFactory.getLogger(ShopService.class);

	private HashMap<String, Article> catalog = new HashMap<>();
	private HashMap<Integer, Customer> customers = new HashMap<>();

	public void receiveInfo(String message) {
		LOG.info(message);
		System.out.println(message);
	}

	public HashMap<String, Article> getCatalog() {
		return this.catalog;
	}
	public HashMap<Integer, Customer> getCustomer() {
		return this.customers;
	}
	
	public void catalogingArticles(List<Article> articleList) {
		catalog.clear();
		for(Article article: articleList) {
			String catalogId = article instanceof Book ? "BK"+String.valueOf(((Book)article).getArticleId()): "CD"+String.valueOf(((CD)article).getArticleId());
			catalog.put(catalogId, article);
		}
		catalog.values().forEach(x -> System.out.println(x.getName()));
	}
	
	public void insertCustomers(List<Customer> customerList) {
		customers.clear();
		LOG.info("\n\n" + customerList + "\n\n");
		
		for(Customer customer : customerList) {
			customer.setCart(new Cart());
			customers.put(customer.getCustomerId(), customer);
		}
		
//		customerList.forEach(customer -> customers.put(customer.getCustomerId(), customer));
	}
	
	// CHECK!
	public void addArticleToCart(Integer customerId, String articleId) {
		Article foundArticle = catalog.get(articleId);
		Cart cart = customers.get(customerId).getCart();
		cart.addCartItem(articleId, foundArticle);
	}

	public void removeArticleFromCart(Integer customerId, String articleId) {
		Cart cart = customers.get(customerId).getCart();
		cart.deleteCartItem(articleId);
		
	}

	public void changeArticleQuantity(Integer customerId, String articleId) {
		Cart cart = customers.get(customerId).getCart();
		cart.decrementArticleQuantity(articleId);
		
	}

	public Order checkOutCart(Integer customerId) {
		Customer customer = customers.get(customerId);
		Cart cart = customer.getCart();
		Order order = new Order();
		
		List<OrderPosition> orderPositions = new ArrayList<OrderPosition>();
		int orderPos = 1;
		for(CartItem cartItem: cart.getCartItems()) {
			System.out.println("ID:" + orderPos + " " + cartItem.getQuantity() + " " + cartItem.getPrice());
			OrderPosition orderPosition = new OrderPosition();
			orderPosition.setArticleId(cartItem.getArticleId());
			orderPosition.setPrice(cartItem.getPrice());
			orderPosition.setArticleQuantity(cartItem.getQuantity());
			orderPosition.setPositionId(orderPos);
			
			orderPositions.add(orderPosition);
			orderPos++;
			
		}
		order.setCustomerId(customerId);
		order.setOrderPositions(orderPositions);
		return order;
	}

}
