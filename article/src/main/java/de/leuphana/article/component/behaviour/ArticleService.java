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
import de.leuphana.article.connector.kafka.ArticleKafkaConsumer;

@Service
public class ArticleService implements IArticleService {

	private ArticleRepository articleRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ArticleKafkaConsumer.class);

	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public boolean saveCD(CD cd) {
		// CD already exists in DB: Update
//		if (articleRepository.getCdById(cd.getCdId()) != null) {
//			CD updatedCd = articleRepository.save(cd);
//			if (updatedCd != null)
//				return true;
//		}
		// CD doesn't exist in DB: Create
		
			long lastCdId = articleRepository.countCds() + 1;
//			cd.setCdId("CD" + String.valueOf(lastCdId));
			CD newCd = articleRepository.save(cd);
			if (newCd != null)
				return true;
		

		return false;
	}

	@Override
	public boolean saveBook(Book book) {
		// Book already exists in DB: Update
//		if (articleRepository.getBookById(book.getBookId()) != null) {
//			Book updatedBook = articleRepository.save(book);
//			if (updatedBook != null)
//				return true;
//		}
		// Book doesn't exist in DB: Create
	
			long lastBookId = articleRepository.countBooks() + 1;
//			book.setBookId("BK" + String.valueOf(lastBookId));
			Book newBook = articleRepository.save(book);
			if (newBook != null)
				return true;
		
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

	public Article getArticleById(String articleType, String articleId) {
		Article article = null;
		
		switch(articleType) {
		case "book": article = articleRepository.getBookById(Long.valueOf(articleId));break;
		case "cd": article = articleRepository.getCdById(Long.valueOf(articleId));break;
		}
		
		
		
//		if (id.startsWith("BK")) {
//			article = articleRepository.getBookById(id);
//		} else if (id.startsWith("CD")) {
//			article = articleRepository.getCdById(id);
//		}
		return article;

	}

	public boolean deleteArticle(Article article) {
		if(article instanceof Book) articleRepository.deleteBookById(article.getId());
		
		return true;
		
//		LOG.info("DELETE ARTICLE BY ID: " + id);
//		if (id.startsWith("BK")) {
//			// Workaround because of Spring-Bug. Can't delete by String ID, just by Int
//			int bookId = Integer.valueOf(id.substring(2, id.length()));
//			articleRepository.deleteBookById(bookId);
//			return true;
//		} else if (id.startsWith("CD")) {
//			int cdId = Integer.valueOf(id.substring(2, id.length()));
//			articleRepository.deleteCdById(cdId);
//			return true;
//		}
//		return true;
	}

}
