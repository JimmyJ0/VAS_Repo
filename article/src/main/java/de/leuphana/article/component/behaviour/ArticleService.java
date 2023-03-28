package de.leuphana.article.component.behaviour;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;
import de.leuphana.article.configuration.ArticleRepository;
import de.leuphana.article.connector.kafka.ShopKafkaConsumer;
import de.leuphana.article.connector.kafka.ArticleKafkaController;

@Service
public class ArticleService implements IArticleService {

	private ArticleRepository articleRepository;
	private ArticleKafkaController articleKafkaController;

	private static final Logger LOG = LoggerFactory.getLogger(ShopKafkaConsumer.class);

	@Autowired
	public ArticleService(ArticleRepository articleRepository,ArticleKafkaController articleKafkaController) {
		this.articleRepository = articleRepository;
		this.articleKafkaController = articleKafkaController;
	}
	
	public void messageShop() {
		articleKafkaController.sendMessage("The article database has been updated");
	}
	

	@Override
	public boolean saveCD(CD cd) {
		CD newCd = articleRepository.save(cd);
		if (newCd != null) {
			messageShop();
			return true;
		}
		return false;
	}

	@Override
	public boolean saveBook(Book book) {
		Book newBook = articleRepository.save(book);
		if (newBook != null) {
			messageShop();
			return true;
		}
		return false;
	}

	@Override
	public List<Article> getArticles() {
		List<Article> allArticles = new ArrayList<>();
		List<Book> books = articleRepository.getAllBooks(Book.class);
		List<CD> cds = articleRepository.getAllCds(CD.class);

		allArticles.addAll(books);
		allArticles.addAll(cds);
		if (allArticles.size() > -1) {
			return allArticles;
		}
		return null;
	}

	@Override
	public Article getArticleById(String articleType, Long articleId) {
		Article article = null;

		switch (articleType) {
		case "book":
			article = articleRepository.getBookById(Long.valueOf(articleId));
			break;
		case "cd":
			article = articleRepository.getCdById(Long.valueOf(articleId));
			break;
		}
		return article;

	}

	@Override
	public boolean deleteArticle(String articleType, Long id) {
		if (articleType.equals("book")) {
			articleRepository.deleteBookById(id);
			messageShop();
			return true;
		}
		if (articleType.equals("cd")) {
			articleRepository.deleteCdById(id);
			messageShop();
			return true;
		}
		return false;

	}


}
