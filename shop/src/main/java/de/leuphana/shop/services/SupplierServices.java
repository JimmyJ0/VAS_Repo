package de.leuphana.shop.services;


import java.util.Map;

import de.leuphana.shop.structure.article.Article;


public interface SupplierServices {
	
	public boolean saveArticleInDB(Article article);
	
	public Map<String, Article> getArticles();
	
	public Article getArticleByIdFromDB(String articleType, Long id);
	
	public boolean updateArticle(Article article, Long id);
	
	public boolean deleteArticleById(String id);
	

}
