package de.leuphana.shop.structure.article;

public class CD extends Article {

	private String artist;
//	private String cdId;
	private Long id;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



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

//	public String getCdId() {
//		return cdId;
//	}
//	public void setCdId(String id) {
//		cdId = id;
//		
//	}

}
