package de.leuphana.article.connector.rest;

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
	public ResponseEntity<String> saveArticle(@RequestBody CD cd) throws JsonProcessingException {
		if (articleService.saveCD(cd))
			return new ResponseEntity<String>("CD saved: " + cd.getName(), HttpStatus.CREATED);
		return new ResponseEntity<String>("Failed to save:" + cd.getName(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/book")
	public ResponseEntity<String> saveArticle(@RequestBody Book book) throws JsonProcessingException {
		if (articleService.saveBook(book))
			return new ResponseEntity<String>("Book saved: " + book.getName(), HttpStatus.CREATED);
		return new ResponseEntity<String>("Failed to save:" + book.getName(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAllArticles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> articles = articleService.getArticles();
		if (articles.size() > -1)
			return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
		return new ResponseEntity<List<Article>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getArticleById/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id) {
		Article article = articleService.getArticleById(id);
		if (article != null) {
			return new ResponseEntity<Article>(article, HttpStatus.OK);
		}
		return new ResponseEntity<Article>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/deleteArticleById/{articleid}")
	public ResponseEntity<String> deleteArticleById(@PathVariable String articleid) {
		boolean successfullDeleted = articleService.deleteArticleById(articleid);
		if (successfullDeleted) {
			return new ResponseEntity<String>("Article deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/ping")
	public ResponseEntity<String> ping() {
		log.info("PING PING PING");
		System.out.println("PING");
		System.out.println("PING");
		return new ResponseEntity<String>("ARTICLE SERVICE PINGED", HttpStatus.OK);

	}

}
