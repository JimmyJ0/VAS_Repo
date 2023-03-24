package de.leuphana.article.component.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cds")

public class CD extends Article<String>{
    
    private String artist;
    
    public CD() {
    	super();
    }
    
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
        
}
