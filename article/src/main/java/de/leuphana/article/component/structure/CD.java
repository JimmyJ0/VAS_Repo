package de.leuphana.article.component.structure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cds")
public class CD extends Article {
    
    @Column(name="cd_id")
    private String cdId;
    private String artist;
    
    public CD() {
    	super();
    }
    
	public String getCdId() {
		return cdId;
	}

	public void setCdId(String id) {
		this.cdId = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
        
}
