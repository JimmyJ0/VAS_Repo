package de.leuphana.shop.component.behaviour;

import java.util.List;

import de.leuphana.shop.component.structure.article.Article;

public interface IShopService {
	
	public boolean saveArticle(Article article);
	
	public List<Article> getArticles();
	
	public Article getArticleById(String id);
	
	public boolean updateArticle(Article article, String id);
	
	public boolean deleteArticleById(String id);
	

}
