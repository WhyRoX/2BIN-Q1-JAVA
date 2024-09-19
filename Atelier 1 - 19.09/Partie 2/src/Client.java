import java.util.Objects;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;

    public Client(String nom, String prenom, String telephone) {
        this.numero = numeroSuivant++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Commande getCommandeEnCours() {
        return this.commandeEnCours;
    }

    public boolean enregistrer(Commande commande) {
        if (this.commandeEnCours != null || !commande.getClient().equals(this)) {
            return false;
        }
        this.commandeEnCours = commande;
        return true;
    }
    public boolean cloturerCommandeEnCours() {
        if (this.commandeEnCours == null) {
            return false;
        }
        this.commandeEnCours = null;
        return true;
    }
    //• La méthode enregistrer échoue s’il y a déjà une commande en cours ou si la commande
    //passée en paramètre n’est pas une commande du client. Sinon, elle enregistre la commande
    //passée en paramètre comme commande en cours.
    //• La méthode cloturerCommandeEnCours échoue s’il n’y a pas de commande en cours.
    //Sinon, elle supprime la commande en cours.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return numero == client.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }

}
