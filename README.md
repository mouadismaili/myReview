# AwesomePasswordChecker

**AwesomePasswordChecker** est une classe Java qui fournit des fonctionnalités pour l'analyse et la manipulation de mots de passe. Elle combine des techniques de classification basées sur des centres de clusters, des transformations en masques numériques et une implémentation personnalisée de l'algorithme de hachage MD5.

## Fonctionnalités

- **Chargement des Clusters de Référence :**  
  Les centres des clusters sont chargés à partir d'un fichier CSV pour analyser et comparer les mots de passe.

- **Transformation en Masque :**  
  Les mots de passe sont convertis en vecteurs numériques (`maskArray`) selon des règles basées sur leurs caractères.

- **Calcul de Distance :**  
  Compare un mot de passe à des centres de clusters en utilisant une distance euclidienne modifiée.

- **Implémentation MD5 Personnalisée :**  
  Génération d'un hachage MD5 pour un texte donné sans utiliser de bibliothèques externes.

## Prérequis

- **Java 8 ou supérieur**
- **Fichier cluster_centers_HAC_aff.csv :**  
  Ce fichier doit contenir les centres des clusters, avec chaque ligne représentant un centre sous forme de valeurs séparées par des points-virgules (`;`).

## Installation

1. Clonez le dépôt ou copiez les fichiers source dans votre projet :

   ```bash
   git clone https://gitlab.isima.fr/moismailim/codereview.git
   ```
   

2. Ajoutez le fichier `cluster_centers_HAC_aff.csv` dans le dossier resources de votre projet.

Compilez le projet avec votre IDE préféré ou en ligne de commande :
    ```bash
    javac TP1/MouadProjet/AwesomePasswordChecker.java
    ```
##Utilisation
- ***Chargement de l'Instance***

Vous pouvez initialiser une instance de `AwesomePasswordChecker` de deux manières :
1. Avec un Fichier Personnalisé

    ```java
    File file = new File("path/to/your/cluster_centers_HAC_aff.csv");
    AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance(file);
    ```

2. Avec le Fichier de Ressources par Défaut

    ```java
    AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();
    ```

- ***Calcul de la Distance***

1. Calculez la distance minimale entre un mot de passe et les centres de clusters :

    ```java
    String password = "examplePassword123!";
    double distance = checker.getDIstance(password);
    System.out.println("Distance : " + distance);
    ```

- ***Génération d'un Masque***

1. Transformez un mot de passe en un tableau numérique basé sur ses caractères :

    ```java
    int[] mask = checker.maskAff("examplePassword123!");
    System.out.println(Arrays.toString(mask));
    ```

- ***Hachage MD5***

1. Générez un hachage MD5 pour un texte donné :

    ```java
    String hash = AwesomePasswordChecker.ComputeMD5("examplePassword123!");
    System.out.println("MD5 Hash : " + hash);
    ```

-***Règles de Masque (maskAff)***

# Règles de Mapping des Caractères

Les caractères du mot de passe sont mappés selon les règles suivantes :

| **Caractère**                                      | **Valeur** |
|----------------------------------------------------|------------|
| Lettres spécifiques (e, s, a, i, t, n, r, u, o, l) (minuscule) | 1          |
| Lettres spécifiques (majuscule)                   | 3          |
| Caractères spéciaux (>, <, -, ?, ., /, !, %, @, &) | 6          |
| Lettres minuscules (autres)                       | 2          |
| Lettres majuscules (autres)                       | 4          |
| Chiffres (0-9)                                    | 5          |
| Autres caractères                                 | 7          |

---

## Structure du Code

### Fichiers Importants

- **`AwesomePasswordChecker.java`**  
  Contient l'intégralité de la logique pour :
  - Le chargement des clusters
  - La transformation en masque
  - Le calcul de distance
  - Le hachage MD5

- **`cluster_centers_HAC_aff.csv`**  
  Fichier CSV contenant les centres des clusters utilisés pour la comparaison.

### Principales Méthodes

- **`getInstance(File file)`**  
  Retourne une instance unique de `AwesomePasswordChecker` en chargeant un fichier CSV donné.

- **`maskAff(String password)`**  
  Transforme un mot de passe en un masque numérique basé sur ses caractères.

- **`getDIstance(String password)`**  
  Calcule la distance minimale entre le masque d'un mot de passe et les centres de clusters.

- **`ComputeMD5(String input)`**  
  Implémente l'algorithme MD5 pour hacher une chaîne d'entrée.

---

## Limitations

### Sécurité de MD5
- **MD5 n'est pas sécurisé** pour des usages modernes nécessitant une forte résistance aux attaques.  
  Il est recommandé d'utiliser des alternatives comme **SHA-256** pour des systèmes critiques.

### Performance
- Le calcul de distance peut devenir **lent si le nombre de clusters est très élevé**.

### Taille du Masque
- La taille fixe de **28** pour le masque peut ne pas convenir à tous les cas.

---

## Contribuer

1. Forkez le projet.  
2. Créez une branche pour votre fonctionnalité :
   ```bash
   git checkout -b ma-nouvelle-fonctionnalite

