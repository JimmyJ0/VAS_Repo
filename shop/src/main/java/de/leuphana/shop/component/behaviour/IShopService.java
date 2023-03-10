package de.leuphana.shop.component.behaviour;

import java.util.List;

import de.leuphana.shop.component.structure.article.Article;

public interface IShopService {
	
	public boolean insertArticle(Article article);
	
	public List<Article> getArticles();
	

}
