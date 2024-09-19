import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;


public class Pizza implements Iterable<Ingredient> {
    public final static double PRIX_BASE = 5;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }
    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (this.ingredients.contains(ingredient)){
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
            }
            this.ingredients.add(ingredient);
        }

    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {

            throw new IllegalArgumentException("L'ingrédient est déjà dans la pizza");
        }

        return ingredients.add(ingredient);
    }

    public boolean supprimer(Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            throw new IllegalArgumentException("L'ingrédient n'est pas dans la pizza");
        }
        return ingredients.remove(ingredient);
    }

    public double calculerPrix() {
        double prix = PRIX_BASE;
        for (Ingredient ingredient : ingredients) {
            prix += ingredient.getPrix();
        }
        return prix;
    }


    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza that = (Pizza) o;
        return Objects.equals(titre, that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
