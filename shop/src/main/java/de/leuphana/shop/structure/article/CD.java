package de.leuphana.shop.structure.article;

public class CD extends Article {

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

	public void setCdId(String id) {
		cdId = id;
		
	}

}
