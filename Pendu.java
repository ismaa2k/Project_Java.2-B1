import java.util.Scanner;

public class Pendu {
    // Déclare le mot a trouver et les erreur max attribué
    private static final String MOT_A_DECOUVRIR = "PENDU";
    private static final int MAX_ERREURS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] lettresTrouvees = new char[MOT_A_DECOUVRIR.length()];
        int erreurs = 0;

        // Boucle principale du jeu
        while (erreurs < MAX_ERREURS) {
            afficherMotMasque(lettresTrouvees);
            System.out.print("Entrez une lettre : ");
            char lettre = scanner.next().toUpperCase().charAt(0);
            boolean lettreTrouvee = essaiLettre(lettre, MOT_A_DECOUVRIR, lettresTrouvees);

            // Gestion des erreurs
            if (!lettreTrouvee) {
                erreurs++;
                afficherErreurRestante(erreurs);
            }

            // Vérification si le mot a été découvert
            if (motDecouvert(lettresTrouvees)) {
                afficherMessageGagne(MOT_A_DECOUVRIR);
                break;
            }
        }

        // Fin du jeu - message de victoire ou défaite
        if (erreurs == MAX_ERREURS) {
            afficherMessagePerdu(MOT_A_DECOUVRIR);
        }
        scanner.close(); // Fermeture du scanner
    }

    // Affiche le mot masqué
    private static void afficherMotMasque(char[] lettresTrouvees) {
        for (char c : lettresTrouvees) {
            if (c == '\0') {
                System.out.print("_ ");
            } else {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }

    // Vérifie si toutes les lettres du mot ont été découvertes
    private static boolean motDecouvert(char[] lettresTrouvees) {
        for (char c : lettresTrouvees) {
            if (c == '\0') {
                return false;
            }
        }
        return true;
    }

    // Essaie une lettre et met à jour les lettres trouvées
    private static boolean essaiLettre(char lettre, String motADecouvrir, char[] lettresTrouvees) {
        boolean lettreTrouvee = false;
        for (int i = 0; i < motADecouvrir.length(); i++) {
            if (motADecouvrir.charAt(i) == lettre) {
                lettresTrouvees[i] = lettre;
                lettreTrouvee = true;
            }
        }
        return lettreTrouvee;
    }

    // Affiche le message indiquant le nombre d'erreurs restantes
    private static void afficherErreurRestante(int erreurs) {
        System.out.println("Lettre incorrecte. Il vous reste " + (MAX_ERREURS - erreurs) + " essais.");
    }

    // Affiche le message de victoire
    private static void afficherMessageGagne(String mot) {
        System.out.println("Félicitations ! Vous avez deviné le mot : " + mot);
    }

    // Affiche le message de défaite
    private static void afficherMessagePerdu(String mot) {
        System.out.println("Désolé, vous avez épuisé tous vos essais. Le mot était : " + mot);
    }
}