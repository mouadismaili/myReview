package TP1.MouadProjet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenue dans AwesomePasswordChecker!");

        try {
            // Charger automatiquement le fichier de ressources
            InputStream clusterFileStream = AwesomePasswordChecker.class.getClassLoader()
                .getResourceAsStream("cluster_centers_HAC_aff.csv");

            if (clusterFileStream == null) {
                System.err.println("Erreur : Le fichier cluster_centers_HAC_aff.csv est introuvable dans les ressources.");
                return;
            }

            // Charger les centres de clusters
            AwesomePasswordChecker checker = new AwesomePasswordChecker(clusterFileStream);

            System.out.println("Fichier chargé avec succès!");

            // Saisie d'un mot de passe à analyser
            System.out.println("Veuillez entrer un mot de passe à analyser :");
            String password = scanner.nextLine();

            // Calculer la distance minimale au cluster
            double distance = checker.getDIstance(password);
            System.out.println("Distance minimale au cluster : " + distance);

            // Calculer et afficher le hash MD5 du mot de passe
            String md5Hash = AwesomePasswordChecker.ComputeMD5(password);
            System.out.println("Hash MD5 du mot de passe : " + md5Hash);

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de clusters : " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
