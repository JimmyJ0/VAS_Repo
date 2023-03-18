package de.leuphana.article.connector;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.leuphana.article.component.behaviour.ArticleService;
import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

@RestController
@RequestMapping("/shop/article")
public class ArticleSpringDataConnectorRequester {
	
	org.slf4j.Logger log = LoggerFactory.getLogger(ArticleSpringDataConnectorRequester.class);

	private ArticleService articleService;

	@Autowired
	public ArticleSpringDataConnectorRequester(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}

	// 2.
	// Erhält HTML mit als JSON verpacktem Artikel vom ArticleRestConnector
	// Ruft anschließend den Service auf und speichert den Artikel in Datenbank
	
	@PostMapping("/cd")
	public ResponseEntity<CD> saveArticle(@RequestBody CD cd) throws JsonProcessingException {
	    if (cd instanceof CD) {
	       articleService.saveCD(cd);
	    } else  {
	    	//TODO: Exception Handling
	    }
		return null;
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> saveArticle(@RequestBody Book book) throws JsonProcessingException {
	    if (book instanceof Book) {
	       articleService.saveBook(book);

	    } else  {
	    	//TODO: Exception Handling
	    }
		return null;
	}
	
	@GetMapping("/getAllArticles")
	public ResponseEntity<List<Article>> getAllArticles() {
		System.out.println("ARTIKEL MS EMPFÄNGT GET ANFRAGE");
	    List<Article> articles = articleService.getArticles();
	    ResponseEntity<List<Article>> re = new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
	    // Zugriff auf die Datenbank mit Hilfe von JPA Repository
	    return re; // Rückgabe der Liste mit HTTP-Statuscode 200 OK
	}
	
	@GetMapping("/getArticleById/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id) {
	    Article article = articleService.getArticleById(id);
	    if (article == null) {
	        return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	
	@GetMapping("/deleteArticleById/{articleid}")
	public ResponseEntity<String> deleteArticleById(@PathVariable String articleid) {
	    boolean successfullDeleted = articleService.deleteArticleById(articleid);
	    if (successfullDeleted) {
	        return new ResponseEntity<String>("Article deleted",HttpStatus.OK);
	    }
	    return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
    	log.info("PING PING PING");
    	System.out.println("PING");
    	System.out.println("PING");
        return new ResponseEntity<String>("ARTICLE SERVICE PINGED",HttpStatus.OK);

    }
	
}
