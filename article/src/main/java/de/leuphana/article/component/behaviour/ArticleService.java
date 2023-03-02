package de.leuphana.article.component.behaviour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
