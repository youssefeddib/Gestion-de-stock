package net.javaguids.Produitgesion.model;

public class Produit {
    
    private int id;
    private String nom;
    private String description;
    private int quantity;
    private int prix;
    private String categorie;

    // ✅ Constructor بدون معاملات (مهم إذا كنت تستخدم Java Beans)
    public Produit() {}

    // ✅ Constructor كامل
    public Produit(int id, String nom, String description, int quantity, int prix, String categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantity = quantity;
        this.prix = prix;
        this.categorie = categorie;
    }

    // ✅ Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
