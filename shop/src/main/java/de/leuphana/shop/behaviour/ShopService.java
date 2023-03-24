package de.leuphana.shop.behaviour;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.leuphana.shop.connector.kafka.ShopKafkaController;
import de.leuphana.shop.connector.rest.ArticleRestConnectorRequester;
import de.leuphana.shop.services.SupplierServices;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@Service
public class ShopService implements SupplierServices {

	// TODO: Anhand der Response Entity Exception Handling machen.
    private static final Logger LOG = LoggerFactory.getLogger(ShopService.class);

	private ArticleRestConnectorRequester articleRestConnector;
	private ShopKafkaController kafkaController;
	
	private HashMap<String, Article> catalog = new HashMap<>();

	@Autowired
	public void setArticleRestConnector(ArticleRestConnectorRequester articleRestConnector) {
		this.articleRestConnector = articleRestConnector;
	}
	
	@Autowired
	public void setKafkaArticleController(ShopKafkaController kafkaController) {
		this.kafkaController = kafkaController;
	}

	public HashMap<String,Article> getCatalog(){
		return this.catalog;
	}
	
	
	public void receiveInfo(String message) {
		LOG.info(message);
		System.out.println(message);
	}


	@Override
	public boolean saveArticleInDB(Article article) {
		ResponseEntity<String> response = kafkaController.saveArticle(article);
		if(response.getBody().equals("article sent")) {
			return true;
		}
		return false;
	}
	
	@Override
	public Article getArticleByIdFromDB(String articleType, Long id) {
		Article foundArticle = articleRestConnector.getArticleById(articleType, String.valueOf(id));
		if (foundArticle != null)
			return foundArticle;
		return null;
	}

	@Override
	public Map<String, Article> getArticles() {
		for(Article article: articleRestConnector.getArticles()) {
			String catalogId = article instanceof Book ? "BK"+String.valueOf(((Book)article).getId()): "CD"+String.valueOf(((CD)article).getId());
			catalog.put(catalogId, article);
		}
		return catalog;
	}



	// Creepy, but working
	@Override
	public boolean updateArticle(Article article, Long id) {
		Article oldArticle = null;
		switch(article.getArticleType()) {
		case "book": oldArticle = catalog.get("BK"+String.valueOf(id));break;
		case "cd": oldArticle = catalog.get("CD"+String.valueOf(id));break;
			}
		
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
		if (saveArticleInDB(oldArticle)) return true;
		return false;
	}

	@Override
	public boolean deleteArticleById(String articleid) {
		LOG.info("ABOUT TO DELETE");
		Article articleToDelete = catalog.get(articleid);
		if(articleToDelete != null) {
			ResponseEntity<String> response = kafkaController.deleteArticle(articleToDelete);
			if(response.getBody().equals("article deleted"))return true;
		}
		return false;
	}


}
