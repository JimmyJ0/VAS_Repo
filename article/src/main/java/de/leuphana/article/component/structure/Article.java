package de.leuphana.article.component.structure;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Article {
    
    @Id
    @Column(name="id", length=10)
    protected String id;
    
    @Column(name="manufactor_name")
    protected String manufactor;
    
    @Column(name="article_name")
    protected String name;
    
    @Column(name="article_price")
    protected String price;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getManufactor() {
        return manufactor;
    }
    
    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
}

