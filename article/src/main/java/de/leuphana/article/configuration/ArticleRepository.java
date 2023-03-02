package de.leuphana.article.configuration;


import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.article.component.structure.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	

}
