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
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@RestController
@RequestMapping("/shop/article")
public class ArticleSpringDataConnectorRequester {

	org.slf4j.Logger log = LoggerFactory.getLogger(ArticleSpringDataConnectorRequester.class);

	@Autowired
	private Tracer tracer;
	private ArticleService articleService;

	@Autowired
	public ArticleSpringDataConnectorRequester(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}

	@GetMapping("/getAllArticles")
	public ResponseEntity<List<Article>> getAllArticles() {
		Span span = tracer.spanBuilder("getArticles-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			List<Article> articles = articleService.getArticles();
			if (articles.size() > -1)
				return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			span.end();
		}
		return new ResponseEntity<List<Article>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getArticleById/{articleType}/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id, @PathVariable String articleType) {
		Span span = tracer.spanBuilder("getArticleById-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			Long numId = Long.parseLong(id.replaceAll("[^0-9]", ""));
			Article article = articleService.getArticleById(articleType, numId);
			if (article != null) {
				return new ResponseEntity<Article>(article, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			span.end();
		}
		return new ResponseEntity<Article>(HttpStatus.BAD_REQUEST);
	}

}
