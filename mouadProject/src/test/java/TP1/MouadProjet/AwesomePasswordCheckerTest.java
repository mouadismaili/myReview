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
        File file = new File("src/test/resources/cluster_centers_HAC_aff.csv");

        checker = AwesomePasswordChecker.getInstance(file);
    }
    /**
     * Vérifie que la méthode {@code maskAff} génère correctement un masque pour un mot de passe.
     */
    @Test
    public void testMaskAff() {
        String password = "123hmmMAROC@";
        int[] mask = checker.maskAff(password);

        // Masque attendu : lettres majuscules, minuscules, chiffres et caractères spéciaux
        int[] expectedMask = new int[28];
        expectedMask[0] = 5; 
        expectedMask[1] = 5; 
        expectedMask[2] = 5; 
        expectedMask[3] = 2; 
        expectedMask[4] = 2; 
        expectedMask[5] = 2; 
        expectedMask[6] = 4; 
        expectedMask[7] = 3; 
        expectedMask[8] = 3;
        expectedMask[9] = 3;
        expectedMask[10] = 4;
        expectedMask[11] = 6;

        assertArrayEquals(expectedMask, mask, "Le masque généré pour le mot de passe n'est pas correct.");
    }


    /**
     * Vérifie que la méthode {@code computeMd5} calcule correctement le hachage MD5.
     */
    @Test
    public void testComputeMd5() {
        String input = "mouadSAma@";
        String md5Hash = AwesomePasswordChecker.ComputeMD5(input);

        // MD5 de "testPassword" attendu
        String expectedMd5 = "2512849b0592ee0147e2057f34b4badc"; // Valeur correcte pour "testPassword"

        assertEquals(expectedMd5, md5Hash, "Le hachage MD5 n'est pas correct.");
    }
    /**
     * Teste la méthode `euclideanDistance()` pour vérifier le calcul de la distance euclidienne entre deux vecteurs.
     * Ce test vérifie que la distance entre deux vecteurs est correcte et égale à la racine carrée de la somme des carrés des différences.
     */
    @Test
    void testEuclideanDistance() {
        AwesomePasswordChecker checker = new AwesomePasswordChecker();
        int[] a = {2, 3, 4};
        double[] b = {1.0, 2.0, 3.0};
        double distance = checker.euclideanDistance(a, b);
        
        // Vérifie que la distance entre les vecteurs est correcte
        assertEquals(Math.sqrt(15.0), distance, 1e-6, "La distance doit être correcte entre les vecteurs");
    }

   /**
 * Teste la méthode ComputeMD5 pour différents cas de chaînes de caractères.
 */
@Test
public void testComputeMD5() {
    // Cas de test avec une chaîne vide
    assertEquals("d41d8cd98f00b204e9800998ecf8427e", AwesomePasswordChecker.ComputeMD5(""));

    // Cas de test avec une chaîne de caractères
    assertEquals("5d41402abc4b2a76b9719d911017c592", AwesomePasswordChecker.ComputeMD5("hello"));

    // Cas de test avec une chaîne de caractères plus longue
    assertEquals("9e107d9d372bb6826bd81d3542a419d6", AwesomePasswordChecker.ComputeMD5("The quick brown fox jumps over the lazy dog"));

    // Cas de test avec une chaîne de caractères contenant des caractères spéciaux
    assertEquals("7723e4154231f6d9f5c8854cd5cc5032", AwesomePasswordChecker.ComputeMD5("MD5#Test@2024"));
}
}

