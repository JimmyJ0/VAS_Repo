package de.leuphana.article.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.leuphana.article.component.behaviour.ArticleService;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

@RestController
@RequestMapping("/shop/article")
public class ArticleSpringDataConnectorRequester {

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
}
