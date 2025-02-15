package net.javaguids.Produitgesion.model;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private int quantity;
    private double prix;
    private String categorie;

   
    public Produit() {}

    
    public Produit(int id, String nom, String description, int quantity, double prix, String categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantity = quantity;
        this.prix = prix;
        this.categorie = categorie;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom='" + nom + '\'' + ", description='" + description + '\'' +
               ", quantity=" + quantity + ", prix=" + prix + ", categorie='" + categorie + '\'' + '}';
    }
}
