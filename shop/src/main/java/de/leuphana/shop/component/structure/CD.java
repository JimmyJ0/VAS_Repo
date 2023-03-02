package de.leuphana.shop.component.structure;

public class CD extends Article {

	private String artist;
	
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

}
