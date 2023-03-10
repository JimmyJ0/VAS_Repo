package de.leuphana.shop.component.behaviour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.leuphana.shop.component.structure.article.Article;
import de.leuphana.shop.connector.ArticleRestConnector;

public class ShopService implements IShopService{
	
	
	private ArticleRestConnector articleRestConnector;
	
	@Autowired
	public ShopService(ArticleRestConnector articleRestConnector) {
		this.articleRestConnector = articleRestConnector;
	}

	@Override
	public boolean insertArticle(Article article) {
		try {
			articleRestConnector.saveArticle(article);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true;
		
		
	}

	@Override
	public List<Article> getArticles() {
		return articleRestConnector.getArticles().getBody();
	}

}
