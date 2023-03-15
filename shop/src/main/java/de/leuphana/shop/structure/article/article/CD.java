package de.leuphana.shop.component.structure.article;

public class CD extends Article {

	private Long id;

	private String artist;
	private String cdId;
	
	public CD() {
		super();
	}

	public CD(String artist) {
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCdId() {
		return cdId;
	}

	public void setCdId(String cdId) {
		this.cdId = cdId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
