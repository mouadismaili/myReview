package TP1.MouadProjet;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class AwesomePasswordCheckerTest {

    private static AwesomePasswordChecker checker;

    

    @Test
    void testMaskAff() {
        String password = "password123!";
        int[] expectedMask = new int[] {
                2, 2, 2, 2, 5, 5, 5, 5, 7, 5, 2, 2, 3, 5, 5, 7, 2, 2, 5, 7, 2, 5, 5, 5, 5, 5, 5, 7
        };

        int[] maskArray = checker.maskAff(password);
        assertArrayEquals(expectedMask, maskArray, "Les masques ne correspondent pas.");
    }

    @Test
    void testGetDIstance() {
        String password = "password123!";
        double distance = checker.getDIstance(password);
        assertTrue(distance >= 0, "La distance ne peut pas être négative.");
    }

    @Test
    void testComputeMD5() {
        String input = "password123!";
        String expectedMD5 = "ef92b778bafe771e89245b0c946c1e8c"; 
        String md5Hash = AwesomePasswordChecker.ComputeMD5(input);
        assertEquals(expectedMD5, md5Hash, "Les hachages MD5 ne correspondent pas.");
    }

    @Test
    void testSingletonInstance() throws IOException {
        File file = new File("src/test/resource/cluster_centers_HAC_aff.csv");
        AwesomePasswordChecker firstInstance = AwesomePasswordChecker.getInstance(file);
        AwesomePasswordChecker secondInstance = AwesomePasswordChecker.getInstance(file);

        assertSame(firstInstance, secondInstance, "Les instances doivent être identiques.");
    }
}
