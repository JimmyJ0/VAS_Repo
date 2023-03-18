package de.leuphana.shop.behaviour;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@Service
public class ShopService implements IShopService{
	
	//TODO: Anhand der Response Entity Exception Handling machen. 
	
	private ArticleRestConnectorRequester articleRestConnector;
	
    @Autowired
    public void setArticleRestConnector(ArticleRestConnectorRequester articleRestConnector) {
        this.articleRestConnector = articleRestConnector;
    }
	
//	@Autowired
//	public ShopService(ArticleRestConnectorRequester articleRestConnector) {
//		this.articleRestConnector = articleRestConnector;
//	}

	@Override
	public boolean saveArticleInDB(Article article) {
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

	@Override
	public Article getArticleById(String id) {
		Article foundArticle = articleRestConnector.getArticleById(id).getBody();
		
		return foundArticle;
	}

	// Gruselig... Aber funktioniert
	@Override
	public boolean updateArticle(Article article, String id) {
		Article oldArticle = getArticleById(id);
		if(oldArticle != null) {
			oldArticle.setName(article.getName() == null ? oldArticle.getName() : article.getName());
			oldArticle.setManufactor(article.getManufactor() == null ? oldArticle.getManufactor() : article.getManufactor());
			oldArticle.setPrice(article.getPrice() == 0.0 ? oldArticle.getPrice() : article.getPrice());
			if(article instanceof Book) {
				((Book) oldArticle).setAuthor(((Book)article).getAuthor() == null ? ((Book)oldArticle).getAuthor() : ((Book)article).getAuthor());
				((Book) oldArticle).setBookCategory(((Book)article).getBookCategory() == null ? ((Book)oldArticle).getBookCategory() : ((Book)article).getBookCategory());
			}
			else if(article instanceof CD) {
				((CD) oldArticle).setArtist(((CD)article).getArtist() == null ? ((CD)oldArticle).getArtist() : ((CD)article).getArtist());
			}
		}
		saveArticleInDB(oldArticle);
		
		
		return false;
	}

	@Override
	public boolean deleteArticleById(String articleid) {
		String message = articleRestConnector.deleteArticleById(articleid).getBody();
		if(message.equals("Article deleted!")) return true;
		return false;
	}

	public boolean ping() {
		if(articleRestConnector.pinging()) {
			return true;
		}
		return false;
	}
	
	


	
	

}
