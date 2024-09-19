import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande> {

    private static int numerSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesDeCommande;

    public Commande(Client client) {
        if (client.getCommandeEnCours() != null) {
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        }
        this.numero = numerSuivant++;
        this.client = client;
        this.date = LocalDateTime.now();
        this.lignesDeCommande = new ArrayList<>();
        client.enregistrer(this);
    }

    public int getNumero() {
        return numero;
    }
    public Client getClient() {
        return client;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public boolean ajouter(Pizza pizza, int quantite) {
        if (client.getCommandeEnCours() != this) {
            return false;
        }
        for (LigneDeCommande ligneDeCommande : lignesDeCommande) {
            if (ligneDeCommande.getPizza().equals(pizza)) {
                ligneDeCommande.setQuantite(ligneDeCommande.getQuantite() + quantite);
                return true;
            }
        }
        return lignesDeCommande.add(new LigneDeCommande(pizza, quantite));
    }
    public boolean ajouter(Pizza pizza) {
        return ajouter(pizza, 1);
    }

    public double calculerMontantTotal() {
        double montantTotal = 0;
        for (LigneDeCommande ligneDeCommande : lignesDeCommande) {
            montantTotal += ligneDeCommande.calculerPrixTotal();
        }
        return montantTotal;
    }

    public String detailler() {
        StringBuilder sb = new StringBuilder();
        for (LigneDeCommande ligneDeCommande : lignesDeCommande) {
            sb.append(ligneDeCommande.getPizza().getTitre())
                    .append(" x ")
                    .append(ligneDeCommande.getQuantite())
                    .append(" = ")
                    .append(ligneDeCommande.calculerPrixTotal())
                    .append("\n");
        }
        return sb.toString();
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }



    @Override
    public Iterator<LigneDeCommande> iterator() {
        return lignesDeCommande.iterator();
    }


    //Les méthodes ajouter échouent si la commande n’est pas la commande en cours du client.
    //Sinon, s’il existe déjà une ligne de commande pour cette pizza, elle modifie cette ligne pour
    //lui ajouter la quantité voulue. Sinon elle crée une nouvelle ligne de commande pour la pizza
    //et l’ajoute.
    //• Pour la méthode ajouter ne recevant pas de quantité en paramètre, il faut ajouter une seule
    //pizza.
    //• La méthode caculerMontantTotal calcule le montant total de la commande et le renvoie.
    //• La méthode détailler renvoie, sous forme de chaîne de caractères, toutes les lignes de la
    //commande (une ligne de commande par ligne)
}
