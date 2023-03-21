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


	public boolean deleteArticleById(String articleid) {
		boolean success = articleRestConnector.deleteArticleById(articleid);
		if (success)
			return true;
		return false;
	}


	public Customer createCustomer(Customer customer) {
		return customerRestConnectorRequester.createCustomer(customer);

	}

	public List<Customer> getAllCustomers() {
		return customerRestConnectorRequester.getAllCustomers();

	}

	public Customer getCustomerById(Integer customerId){
		return customerRestConnectorRequester.getCustomerById(customerId);

	}


	public Customer updateCustomerById(Integer customerId, Customer customer){
		return customerRestConnectorRequester.updateCustomerById(customerId, customer);

	}


	public void deleteCustomerById(Integer customerId){
		customerRestConnectorRequester.deleteCustomerById(customerId);

	}

}
