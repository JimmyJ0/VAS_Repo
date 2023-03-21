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

	public Customer createCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(Integer customerId);

	public Customer updateCustomerById(Integer customerId, Customer customer);

	public void deleteCustomerById(Integer customerId);
	

}
