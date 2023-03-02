package de.leuphana.article.component.behaviour;

import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public interface IArticleService {
		
	CD saveCD(CD cd);
	
	Book saveBook(Book book);

}
