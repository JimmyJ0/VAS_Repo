package de.leuphana.article.component.behaviour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;
import de.leuphana.article.configuration.ArticleRepository;

@Service
public class ArticleService implements IArticleService {

	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	@Override
	public CD saveCD(CD cd) {
		long lastCdId = articleRepository.countCds() + 1 ;
		cd.setCdId("CD" + String.valueOf(lastCdId));
		return articleRepository.save(cd);
	}

	@Override
	public Book saveBook(Book book) {
		
		if(articleRepository.getBookById(book.getBookId()) != null) {
			return articleRepository.save(book);
		}
		else {
			long lastBookId = articleRepository.countBooks() + 1 ;
			book.setBookId("BK" + String.valueOf(lastBookId));
			return articleRepository.save(book);
		}

		
	}

	@Override
	public List<Article> getArticles() {
		List<Article> allArticles = new ArrayList<>();
		try {
			List<Book> books = articleRepository.getAllBooks(Book.class);
			System.out.println("ARTICLE SERVICE GET LISTE: " + books.size());
			List<CD> cds = articleRepository.getAllCds(CD.class);
			
			
			allArticles.addAll(books);
			allArticles.addAll(cds);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR");
		}
		
		return allArticles;
	}

	public Article getArticleById(String id) {
		Article article = null;

		try {
			if(id.startsWith("BK")) {
				article = articleRepository.getBookById(id);
			}
			else if(id.startsWith("CD")) {
				article = articleRepository.getCdById(id);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return article;
		
	}

	public boolean deleteArticleById(String id) {
		boolean deleted = false;
		try {
			if(id.startsWith("BK")) {
				deleted = articleRepository.deleteBookById(id);
			}
			else if(id.startsWith("CD")) {
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}


}
