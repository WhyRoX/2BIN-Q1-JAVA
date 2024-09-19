import java.util.Objects;

public class Ingredient {
    private String nom;
    private double prix;


    public Ingredient(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return this.nom;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return this.nom + " (" + this.prix + " €)";
    }
}
//Complétez votre implémentation des classes Ingredient et Client pour faire en sorte qu’un
//ingrédient soit identifié par son nom et un client par son numéro.