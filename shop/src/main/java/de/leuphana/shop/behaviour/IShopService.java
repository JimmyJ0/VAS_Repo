package de.leuphana.shop.behaviour;


import java.util.List;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.sales.Customer;


public interface IShopService {
	
	public boolean saveArticleInDB(Article article);
	
	public List<Article> getArticles();
	
	public Article getArticleById(String id);
	
	public boolean updateArticle(Article article, String id);
	
	public boolean deleteArticleById(String id);

	Customer createCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getCustomerById(Integer customerId) throws Exception;

	Customer updateCustomerById(Integer customerId, Customer customer) throws Exception;

	void deleteCustomerById(Integer customerId) throws Exception;
	

}
