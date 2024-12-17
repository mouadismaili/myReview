package TP1.MouadProjet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe {@link AwesomePasswordChecker}.
 */
public class AwesomePasswordCheckerTest {

    private AwesomePasswordChecker checker;

    @BeforeEach
    public void setUp() throws IOException {
        // Charge le fichier CSV de test depuis les ressources
        File file = new File(getClass().getClassLoader().getResource("cluster_centers_HAC_aff_test.csv").getFile());
        checker = AwesomePasswordChecker.getInstance(file);
    }

    /**
     * Vérifie que les centres des clusters sont correctement chargés depuis le fichier CSV.
     */
    @Test
public void testClusterCentersLoaded() throws IOException {
    // Récupère les centres des clusters via le getter
    List<double[]> clusters = checker.getClusterCenters();

    // Vérifie que les centres des clusters ne sont pas nuls ou vides
    assertNotNull(clusters, "La liste des centres des clusters ne doit pas être nulle.");
    assertFalse(clusters.isEmpty(), "Les centres des clusters ne doivent pas être vides.");

    // Vérifie le nombre de centres (10 lignes dans le fichier CSV)
    assertEquals(10, clusters.size(), "Le fichier CSV doit contenir 10 centres de clusters.");

    // Vérifie quelques valeurs spécifiques pour s'assurer du bon chargement
    double[] firstCluster = clusters.get(0);
    assertEquals(1.6607142857142858, firstCluster[0], 1e-6, "Première valeur du premier cluster incorrecte.");
    assertEquals(1.375, firstCluster[1], 1e-6, "Deuxième valeur du premier cluster incorrecte.");

    double[] lastCluster = clusters.get(9); // Dernier centre
    assertEquals(3.8076923076923075, lastCluster[0], 1e-6, "Première valeur du dernier cluster incorrecte.");
    assertEquals(3.269230769230769, lastCluster[1], 1e-6, "Deuxième valeur du dernier cluster incorrecte.");
}

    /**
     * Vérifie que la méthode {@code maskAff} génère correctement un masque pour un mot de passe.
     */
    @Test
    public void testMaskAff() {
        String password = "Pass123!";
        int[] mask = checker.maskAff(password);

        // Masque attendu : lettres majuscules, minuscules, chiffres et caractères spéciaux
        int[] expectedMask = new int[28];
        expectedMask[0] = 3; // 'P' -> Majuscule
        expectedMask[1] = 2; // 'a' -> Minuscule
        expectedMask[2] = 2; // 's' -> Minuscule
        expectedMask[3] = 2; // 's' -> Minuscule
        expectedMask[4] = 5; // '1' -> Chiffre
        expectedMask[5] = 5; // '2' -> Chiffre
        expectedMask[6] = 5; // '3' -> Chiffre
        expectedMask[7] = 6; // '!' -> Caractère spécial

        assertArrayEquals(expectedMask, mask, "Le masque généré pour le mot de passe n'est pas correct.");
    }

    /**
     * Vérifie que la méthode {@code getDistance} calcule correctement la distance minimale.
     */
    @Test
    public void testGetDistance() {
        String password = "Pass123!";
        double distance = checker.getDIstance(password);

        assertTrue(distance >= 0, "La distance minimale doit être positive ou nulle.");
        System.out.println("Distance minimale calculée : " + distance);
    }

    /**
     * Vérifie que la méthode {@code computeMd5} calcule correctement le hachage MD5.
     */
    @Test
    public void testComputeMd5() {
        String input = "testPassword";
        String md5Hash = AwesomePasswordChecker.ComputeMD5(input);

        // MD5 de "testPassword" attendu
        String expectedMd5 = "e16b2ab8d12314bf4efbd6203906ea6c"; // Valeur correcte pour "testPassword"

        assertEquals(expectedMd5, md5Hash, "Le hachage MD5 n'est pas correct.");
    }
}
