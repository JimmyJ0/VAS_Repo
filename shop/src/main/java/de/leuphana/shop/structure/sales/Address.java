package de.leuphana.shop.structure.sales;

public class Address {
	
	private Integer addressId;
	private int zip;
	private String city;
	private String street;
	
	public Integer getAdressId() {
		return addressId;
	}
	public void setAdressId(Integer adressId) {
		this.addressId = adressId;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

}
