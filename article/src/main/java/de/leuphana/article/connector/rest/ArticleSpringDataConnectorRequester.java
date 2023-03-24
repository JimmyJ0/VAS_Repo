package de.leuphana.article.connector.rest;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.article.component.behaviour.ArticleService;
import de.leuphana.article.component.structure.Article;

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

	@GetMapping("/getAllArticles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> articles = articleService.getArticles();
		if (articles.size() > -1)
			return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
		return new ResponseEntity<List<Article>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getArticleById/{articleType}/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id, @PathVariable String articleType) {
		Article article = articleService.getArticleById(articleType, id);
		if (article != null) {
			return new ResponseEntity<Article>(article, HttpStatus.OK);
		}
		return new ResponseEntity<Article>(HttpStatus.BAD_REQUEST);
	}

}
