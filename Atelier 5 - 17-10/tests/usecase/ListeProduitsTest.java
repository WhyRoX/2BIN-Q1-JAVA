package usecase;

import domaine.Prix;
import domaine.Produit;
import exceptions.PrixNonDisponibleException;
import exceptions.ProduitNonPresentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ListeProduitsTest {



    private ListeProduits listeProduits;
    private Produit produit1;
    private Produit produit2;

    @BeforeEach
    void setUp() {
        listeProduits = new ListeProduits();
        produit1 = new Produit("Produit1", "Marque1", "Rayon1");
        produit2 = new Produit("Produit2", "Marque2", "Rayon2");
        listeProduits.ajouterProduit(produit1);
    }

    @Test
    void contient() {
        assertTrue(listeProduits.contient(produit1));
        assertFalse(listeProduits.contient(produit2));
    }

    @Test
    void ajouterProduit() {
        assertTrue(listeProduits.ajouterProduit(produit2));
        assertTrue(listeProduits.contient(produit2));
    }

    void supprimerProduit() {
        assertTrue(listeProduits.supprimerProduit(produit1));
        assertFalse(listeProduits.contient(produit1));

    }

    @Test
    void trouverProduit() {
        assertEquals(produit1, listeProduits.trouverProduit("Produit1", "Marque1", "Rayon1"));
        assertNull(listeProduits.trouverProduit("Produit2", "Marque2", "Rayon2"));
    }

    @Test
    void testproduits() {
        Iterator<Produit> iterator = listeProduits.produits();
        assertTrue(iterator.hasNext());
        assertEquals(produit1, iterator.next());
    }

    @Test
   void ajouterPrix() {
        LocalDate date = LocalDate.now();
        Prix prix = new Prix();
        assertThrows(ProduitNonPresentException.class, () -> listeProduits.ajouterPrix(produit2, date, prix));
        assertDoesNotThrow(() -> listeProduits.ajouterPrix(produit1, date, prix));
    }

    @Test
    void trouverPrix() {
        LocalDate date = LocalDate.now();
        Prix prix = new Prix();
        listeProduits.ajouterPrix(produit1, date, prix);
        assertEquals(prix, listeProduits.trouverPrix(produit1, date));
        //assertThrows(PrixNonDisponibleException.class, () -> listeProduits.trouverPrix(produit1, date.plusDays(1)));
    }
}