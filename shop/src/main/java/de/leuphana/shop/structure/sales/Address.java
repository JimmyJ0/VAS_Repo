package de.leuphana.shop.structure.sales;

import java.util.Objects;

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
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address other = (Address) obj;
        return Objects.equals(city, other.city) &&
                Objects.equals(street, other.street) &&
                zip == other.zip;
    }

}
