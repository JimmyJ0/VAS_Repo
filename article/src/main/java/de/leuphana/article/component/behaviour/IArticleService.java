package de.leuphana.article.component.behaviour;

import java.util.List;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public interface IArticleService {
		
	boolean saveCD(CD cd);
	
	boolean saveBook(Book book);
	
	List<Article> getArticles();

}
