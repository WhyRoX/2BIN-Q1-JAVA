package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1, 20.0);
        prixAucune.definirPrix(10, 10.0);

        prixPub = new Prix(TypePromo.PUB, 0.1);
        prixPub.definirPrix(3, 15.0);

        prixSolde = new Prix(TypePromo.SOLDE, 0.2);

    }

    @Test
    @DisplayName("Test de la classe Prix")
    void Prix(){
        assertThrows(IllegalArgumentException.class, () -> new Prix(null, 0.1));
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB, -0.8));
    }

    @Test
    @DisplayName("Test pour les types de promo")
    void getTypePromo() {
        assertNull(new Prix().getTypePromo());
        assertEquals(new Prix(TypePromo.PUB, 0.9898).getTypePromo(), TypePromo.PUB);
    }

    @Test
    @DisplayName("Test pour les valeur de promo")
    void getValeurPromo() {
        assertEquals(prixAucune.getValeurPromo(), 0);
        assertEquals(prixPub.getValeurPromo(), 0.1);

    }

    @Test
    @DisplayName("Test pour les définitions de prix")
    void definirPrix() {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(0, 1));
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(4, 0));
        prixAucune.definirPrix(10, 6.0);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @DisplayName("Test pour les get de prix")
    @ParameterizedTest
    @ValueSource(ints = {10, 15 , 20, 25})
    void getPrix(int qte) {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.getPrix(0));
        assertEquals(20, prixAucune.getPrix(1));
        assertEquals(20, prixAucune.getPrix(5));
        assertEquals(20, prixAucune.getPrix(9));

        assertEquals(10, prixAucune.getPrix(qte));

        assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }
}