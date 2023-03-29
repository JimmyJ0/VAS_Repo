package de.leuphana.article.component.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cds")
public class CD extends Article{

    private String artist;
    
    public CD() {
    	super();
    }
    
    public Long getArticleId() {
        return articleId;
    }
    
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
        
}
