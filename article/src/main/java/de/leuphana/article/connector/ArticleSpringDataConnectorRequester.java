package de.leuphana.article.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.article.component.behaviour.ArticleService;
import de.leuphana.article.component.structure.Article;

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
	
	@PostMapping()
	public ResponseEntity<Article> saveArticle(@RequestBody Article article) {
		System.out.println("... Erhalte Artikel und ...");

		// Gibt den hinzugefügten Artikel und den HttpStatus.Created zurück
		return new ResponseEntity<Article>(articleService.saveArticle(article), HttpStatus.CREATED);
	}
}
