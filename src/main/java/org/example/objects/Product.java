package org.example.objects;

import java.util.List;

public class Product {
    private String naam;

    private List<Product> ingredienten;

    public Product(String naam) {
        this.naam = naam;
    }

    public Product(String naam, List<Product> ingredienten) {
        this.naam = naam;
        this.ingredienten = ingredienten;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Product> getIngredienten() {
        return ingredienten;
    }

    public void setIngredienten(List<Product> ingredienten) {
        this.ingredienten = ingredienten;
    }

}
