package de.leuphana.article.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.article.connector.rest.ArticleSpringDataConnectorRequester;

@SpringBootTest
class ArticleServiceTest {
	
	@Autowired
	ArticleSpringDataConnectorRequester restConnector;
	
	@Autowired
	private ArticleService articleService;
	
	
//	@Test
//	void canArticleDeleted() {
//		String id = "BK1";
//		assertTrue(articleService.deleteArticleById(id));
//
//	}

}
