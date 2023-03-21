package de.leuphana.shop.behaviour;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.connector.CustomerRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.sales.Customer;

@Service
public class ShopService implements IShopService {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerRestConnectorRequester.class);

	// TODO: Anhand der Response Entity Exception Handling machen.
	private ArticleRestConnectorRequester articleRestConnector;
	private CustomerRestConnectorRequester customerRestConnectorRequester;

	@Autowired
	public void setArticleRestConnector(ArticleRestConnectorRequester articleRestConnector) {
		this.articleRestConnector = articleRestConnector;
	}
	@Autowired
	public void setCustomerRestConnector(CustomerRestConnectorRequester customerRestConnectorRequester) {
		this.customerRestConnectorRequester = customerRestConnectorRequester;
	}

	@Override
	public boolean saveArticleInDB(Article article) {
		return articleRestConnector.saveArticle(article);
	}

	@Override
	public List<Article> getArticles() {
		List<Article> articles = articleRestConnector.getArticles();
		if (articles != null)
			return articles;
		return null;
	}

	@Override
	public Article getArticleById(String id) {
		Article foundArticle = articleRestConnector.getArticleById(id);
		if (foundArticle != null)
			return foundArticle;
		return null;
	}

	// Creepy, but working
	@Override
	public boolean updateArticle(Article article, String id) {
		Article oldArticle = getArticleById(id);
		if (oldArticle != null) {
			oldArticle.setName(article.getName() == null ? oldArticle.getName() : article.getName());
			oldArticle.setManufactor(
					article.getManufactor() == null ? oldArticle.getManufactor() : article.getManufactor());
			oldArticle.setPrice(article.getPrice() == 0.0 ? oldArticle.getPrice() : article.getPrice());
			if (article instanceof Book) {
				((Book) oldArticle).setAuthor(((Book) article).getAuthor() == null ? ((Book) oldArticle).getAuthor()
						: ((Book) article).getAuthor());
				((Book) oldArticle).setBookCategory(
						((Book) article).getBookCategory() == null ? ((Book) oldArticle).getBookCategory()
								: ((Book) article).getBookCategory());
			} else if (article instanceof CD) {
				((CD) oldArticle).setArtist(((CD) article).getArtist() == null ? ((CD) oldArticle).getArtist()
						: ((CD) article).getArtist());
			}
		}
		if (saveArticleInDB(oldArticle))
			return true;
		return false;
	}

	@Override
	public boolean deleteArticleById(String articleid) {
		boolean success = articleRestConnector.deleteArticleById(articleid);
		if (success)
			return true;
		return false;
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		try {
			LOGGER.info("Creating customer: {}", customer);
			Customer createdCustomer = customerRestConnectorRequester.createCustomer(customer);
			LOGGER.info("Customer created successfully: {}", createdCustomer);
			return createdCustomer;
		} catch (Exception e) {
			LOGGER.error("Error creating customer: {}", customer, e);
			throw e;
		}
	}
	@Override
	public List<Customer> getAllCustomers() {
		try {
			LOGGER.info("Getting all customers");
			List<Customer> customers = customerRestConnectorRequester.getAllCustomers();
			LOGGER.info("Retrieved {} customers", customers.size());
			return customers;
		}catch (Exception e) {
			LOGGER.error("Error retrieving customer: {}", e);
			throw e;
		} 
	}
	@Override
	public Customer getCustomerById(Integer customerId) throws Exception {
		try {
			LOGGER.info("Getting customer with id {}", customerId);
			Customer getCustomer = customerRestConnectorRequester.getCustomerById(customerId);
			LOGGER.info("Retrieved customer with id {}", customerId);
			return getCustomer;

		}catch(Exception e) {
			LOGGER.error("Error retrieving customer with id {}", customerId, e);
			throw e;
		} 

	}

	@Override
	public Customer updateCustomerById(Integer customerId, Customer customer) throws Exception{
		try {
			LOGGER.info("Updating customer with id {}", customerId);
			Customer updatedCustomer = customerRestConnectorRequester.updateCustomerById(customerId, customer);
			LOGGER.info("Updated customer with id {}", customerId);
			return updatedCustomer;
		}catch(Exception e) {
			LOGGER.error("Error updating customer with id {}", customerId, e);
			throw e;
		} 

	}

	@Override
	public void deleteCustomerById(Integer customerId) throws Exception{
		try {
			LOGGER.info("Deleting customer with id {}", customerId);
			customerRestConnectorRequester.deleteCustomerById(customerId);
			LOGGER.info("Customer with id {}", customerId, "deleted successfully");

		}catch(Exception e) {
			LOGGER.error("Error deleting customer with id {}", customerId, e);
			throw e;
		} 
	}


}
