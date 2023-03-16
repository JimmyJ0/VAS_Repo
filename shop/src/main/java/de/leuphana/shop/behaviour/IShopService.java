package de.leuphana.shop.behaviour;


import java.util.List;

import de.leuphana.shop.structure.article.Article;


public interface IShopService {
	
	public boolean saveArticleInDB(Article article);
	
	public List<Article> getArticles();
	
	public Article getArticleById(String id);
	
	public boolean updateArticle(Article article, String id);
	
	public boolean deleteArticleById(String id);
	

}
