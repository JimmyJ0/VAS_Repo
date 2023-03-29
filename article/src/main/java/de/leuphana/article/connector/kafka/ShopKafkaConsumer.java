package de.leuphana.article.connector.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.behaviour.ArticleService;
import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@Service
public class ShopKafkaConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(ShopKafkaConsumer.class);
	@Autowired
	private Tracer tracer;

	private ArticleService articleService;

	@Autowired
	public ShopKafkaConsumer(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}

	@KafkaListener(topics = { "book_topic", "cd_topic" }, groupId = "shop_group")
	public void receiveArticle(Article article) {
		Span span = tracer.spanBuilder("receiveArticle-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			if (article instanceof Book) {
				articleService.saveBook((Book) article);
			} else if (article instanceof CD) {
				articleService.saveCD((CD) article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			span.end();
		}
	}

	@KafkaListener(topics = "article_topic", groupId = "group_id")
	public void deleteArticle(Map<String, String> articleData) {
		Span span = tracer.spanBuilder("deleteArticle-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			String articleType = articleData.get("articleType");
			String catalogId = articleData.get("id");
			Long id = Long.parseLong(catalogId.replaceAll("[^0-9]", ""));
			articleService.deleteArticle(articleType, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			span.end();
		}
	}
}
