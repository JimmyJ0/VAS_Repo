package de.leuphana.article.component.behaviour;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.article.connector.ArticleSpringDataConnectorRequester;

@SpringBootTest
class ArticleServiceTest {
	
	@Autowired
	ArticleSpringDataConnectorRequester restConnector;
	
	@Autowired
	private ArticleService articleService;
	
	
	@Test
	void canArticleDeleted() {
		System.out.println();
		String id = "BK1";
		articleService.deleteArticleById(id);
		System.out.println("AKSJDKJASDKJASDKJ");
		System.out.println("ASDASD");
	}

}
