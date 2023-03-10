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
		System.out.println("... lege Artikel in Datenbank ab.");
		return articleRepository.save(cd);
	}

	@Override
	public Book saveBook(Book book) {
		System.out.println("... lege Artikel in Datenbank ab.");
		return articleRepository.save(book);
		
	}

	@Override
	public List<Article> getArticles() {
		System.out.println("GET ARTICLES");
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


}
