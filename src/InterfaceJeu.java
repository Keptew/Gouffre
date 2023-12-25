import java.util.Scanner;
public class InterfaceJeu {
    public void MenuJeu() {
        Scanner scanner = new Scanner(System.in);
        int opt;
        do {
            System.out.println("Bienvenue dans le Gouffre !");
            System.out.println("1 - Jouer");
            System.out.println("2 - Options");
            System.out.println("3 - Quitter");
            opt = scanner.nextInt();
            scanner.nextLine();
            if (opt != 3) {
                switch (opt) {
                    case 1:
                        Parcours(choixJoueurs());
                    case 2:
                        options();
                    default:
                        System.out.println("Option Invalide, veuillez réessayer :");
                }
            }
        } while (opt != 3);
    }

    public void options() {
        System.out.println("DOUZE");
    }

    public Players choixJoueurs() {
        Players players = new Players();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le nombre de joueurs :");
        int nbrJ = scanner.nextInt();
        for (int i = 1; i < nbrJ + 1; i++) {
            System.out.println("Choisissez un nom pour le joueur " + i);
            scanner.nextLine();
            String newName = scanner.nextLine();
            System.out.println("Choisissez un multiplicateur pour ce joueur");
            int newMultiplicateur = scanner.nextInt();
            Player p = new Player(newName, newMultiplicateur);
            players.ajPlayer(p);
        }
        System.out.println(players.listeJoueurs());
        return players;
    }

    public void Parcours(Players joueurs) {
        Scanner scanner = new Scanner(System.in);
        do {
            for (int i = 0; i < joueurs.getSize(); i++) {
                Player joueur = joueurs.getPlayerFromInt(i);
                int nbrGorgee = joueur.getNbrGorgee();
                int multiplicateur = joueur.getMultiplicateur();
                int nbrSecs = joueur.getNbrSec();
                int nbrJoker = joueur.getNbrJoker();
                System.out.println("Au tour de " + joueur.getName() + " !");
                System.out.println("Tu es actuellement sur la case " + joueur.getPosition());
                System.out.println("Voici tes statistiques pour le moment : ");
                System.out.println("Nombre de gorgée : " + nbrGorgee);
                System.out.println("Nombre de secs : " + nbrSecs);
                System.out.println("Multiplicateur : " + multiplicateur);
                System.out.println("Nombre de joker : " + nbrJoker);
                if (joueur.getNbrTour() < 1) {
                    Jouer(joueur, joueurs);
                } else {
                    System.out.println("Vous passez un tour !");
                    joueur.setNbrTour(-1);
                    break;
                }
            }
        } while (!joueurs.checkWin());
        System.out.println("Féliciations à " + joueurs.winner().getName() + " pour la victoire !");
        System.out.println("Appuyez sur entrée pour retourner au menu");
        scanner.nextLine();
        MenuJeu();
    }

    public void Jouer(Player joueur, Players joueurs) {
        Scanner scanner = new Scanner(System.in);
        int de = joueur.lancerDe();
        int newPosition = joueur.getPosition() + de;
        joueur.setPosition(newPosition);
        System.out.println("Tu es désormais sur la case " + joueur.getPosition());
        scanner.nextLine();
        switch (newPosition) {
            case 1:
                System.out.println("1 pour tous, Tous pour 1 : Tout le monde trinque et bois une gorgée.");
                joueurs.gorgeeGenerale(1);
                scanner.nextLine();break;
            case 2:
                System.out.println("2 d'Tension : T'as cru que c'est en faisant des 2 que t'allais gagner... Aller bois 2 gorgées.");
                joueur.ajoutGorgee(2);
                scanner.nextLine();break;
            case 3:
                System.out.println("Cheval de 3 : Un poison s'est dissimulé dans ton brevage... Rajoute de la potion dans ton verre.");
                scanner.nextLine();break;
            case 4:
                System.out.println("Pète à 4 feuilles : Tu gagne un joker qui annulera le gage de ton choix à utiliser avant la fin de la partie.");
                joueur.ajoutJoker(1);
                scanner.nextLine();break;
            case 5:
                System.out.println("Unis comme les 5 doigts de la main : L'union fait la force. Tout le monde boit une gorgée");
                joueurs.gorgeeGenerale(1);
                scanner.nextLine();break;
            case 6:
                System.out.println("6 pieds sous terre : Extermine ton adversaire. Fais boire 6 gorgées à l'adversaire de ton choix ");
                joueurs.choixPlayer().ajoutGorgee(6);
                scanner.nextLine();break;
            case 7:
                System.out.println("Bottes de 7 lieues : Tu viens de voler les chaussures de Flash Gordon. Avance 1 case devant le joueur de tête. Si tu es déjà premier, rejoue.");
                if (joueurs.joueurEnTete() != joueur) {
                    joueur.setPosition(joueurs.joueurEnTete().getPosition() + 1);
                } else {Jouer(joueur, joueurs);}
                scanner.nextLine();break;
            case 8:
                System.out.println("Grand 8 : Bois 2 gorgées et fais 8 tours sur toi-même.");
                joueur.ajoutGorgee(2);scanner.nextLine();break;
            case 9:
                System.out.println("Les 9 vies d'un chat : Chaque joueur lance le dé. Le plus petit score se fait écraser et retourne sur la case départ. Si tu avais un joker, dit lui adieu");
                joueurs.vieChat().mortJoueur();scanner.nextLine();break;
            case 10:
                System.out.println("Une de perdue, 10 de retrouvées. T'as misé sur le mauvais cheval. Bois 2 gorgées et rejoue");
                joueur.ajoutGorgee(2);Jouer(joueur, joueurs);scanner.nextLine();break;
            case 11:
                System.out.println("11 septembre : T'as l'bonjour de Ben Laden. Passe 2 tours.");
                joueur.setNbrTour(2);scanner.nextLine();break;
            case 12:
                System.out.println("Les 12 coups de minuit : L'heure du crime... Lance le dé et fais boire le nombre de gorgées qu'il indiquera au joueur de ton choix");
                int nbrGorgee = joueur.lancerDe();
                joueurs.choixPlayer().ajoutGorgee(nbrGorgee);
                scanner.nextLine();break;
            case 13:
                System.out.println("Vendredi 13 : Tous tes malheurs sont doublés jusqu'à la fin de la partie, sauf si tu meurs avant");
                joueur.setMultiplicateur(joueur.getMultiplicateur()*2);
        }
    }
}
