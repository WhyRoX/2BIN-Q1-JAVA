import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;


public class PizzaComposable extends Pizza {
    private Client createur;
    private LocalDateTime date;

    public PizzaComposable(Client createur) {
        super("Pizza composable du client " + createur.getNumero(), "Pizza de " + createur.getPrenom() + " " + createur.getNom(), new ArrayList<>());
        this.createur = createur;
        this.date = LocalDateTime.now();
    }

    public Client getCreateur() {
        return this.createur;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return super.toString() + "\nPizza créée le " + formater.format(date);
    }
}

//3.7 La sous-classe PizzaComposable
//À sa création, une pizza composable ne reçoit que le client qui la crée. Son titre doit être initialisé à la
//valeur « Pizza composable du client XX» (où XX doit être remplacé par le numéro du client), sa
//description à la valeur « Pizza de » suivi du prénom et du nom du client), sa date à la date courante et
//ses ingrédients à une liste vide.
//5
//Pour obtenir la date courante, il existe, dans la classe LocalDateTime, une méthode statique
//now() qui la renvoie.
//Question 13 : en java, comme invoque-t-on une méthode statique ?
