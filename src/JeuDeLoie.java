import java.util.Scanner;
import java.util.Random;

public class JeuDeLoie {
    public static boolean jokerCheck(){
        boolean isJoker = false;
        int isAnswer = 0;
        while(isAnswer == 0) {
            Scanner s = new Scanner(System.in);
            System.out.println("Veux tu utiliser ton joker pour cette case ?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            switch (s.nextInt()) {
                case 1:
                    isJoker = true;
                    isAnswer = 1;
                    break;
                case 2:
                    isAnswer = 1;
                    break;
                default:break;
            }
        }
        return isJoker;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        int positionJoueur = 0;
        int gorgeeJoueur = 0;
        int secJoueur = 0;
        int jokerJoueur = 0;
        int multiplicateurJoueur = 1;
        int nbrTour = 0;

        System.out.println("Bienvenue dans le Gouffre !");

        while (positionJoueur < 70) {
            if (nbrTour>=1){
                nbrTour--;
                break;
            }
            System.out.println("Vous êtes sur la case " + positionJoueur);
            System.out.println("Nombre de gorgées : "+gorgeeJoueur);
            System.out.println("Nombre de secs : "+secJoueur);
            System.out.println("Nombre de joker : "+jokerJoueur);
            System.out.println("Multiplicateur : "+multiplicateurJoueur);
            System.out.print("Appuyez sur Entrée pour lancer le dé...");
            scanner.nextLine();

            int de = random.nextInt(6) + 1;
            System.out.println("Vous avez obtenu un " + de);

            positionJoueur += de;
            switch (positionJoueur){
                case 1: System.out.println("1 pour tous, Tous pour 1 : Tout le monde trinque et bois une gorgée.");
                        if (!jokerCheck()){gorgeeJoueur++;}else {jokerJoueur--;}break;
                case 2: System.out.println("2 d'Tension : T'as cru que c'est en faisant des 2 que t'allais gagner... Aller bois 2 gorgées.");
                    if (!jokerCheck()){gorgeeJoueur+=2;}else {jokerJoueur--;}break;
                case 3: System.out.println("Cheval de 3 : Un poison s'est dissimulé dans ton brevage... Rajoute de la potion dans ton verre.");
                        if(jokerCheck()){jokerJoueur--;}break;
                case 4: System.out.println("Pète à 4 feuilles : Tu gagne un joker qui annulera le gage de ton choix à utiliser avant la fin de la partie.");
                        jokerJoueur++;
                case 5: System.out.println("Unis comme les 5 doigts de la main : L'union fait la force. Tout le monde boit une gorgée");
                        if(!jokerCheck()){gorgeeJoueur++;}else {jokerJoueur--;}break;
                case 6: System.out.println("6 pieds sous terre : Extermine ton adversaire. Fais boire 6 gorgées à l'adversaire de ton choix ");
                        break;
                case 7: System.out.println("Bottes de 7 lieues : Tu viens de voler les chaussures de Flash Gordon. Avance 1 case devant le joueur de tête. Si tu es déjà premier, rejoue.");
                        break;
                case 8: System.out.println("Grand 8 : Bois 2 gorgées et fais 8 tours sur toi-même.");
                        if(!jokerCheck()){gorgeeJoueur+=2;}else {jokerJoueur--;}break;
                case 9: System.out.println("Les 9 vies d'un chat : Chaque joueur lance le dé. Le plus petit score se fait écraser et retourne sur la case départ. Si tu avais un joker, dit lui adieu");
                        if (random.nextInt(6)+1 < 3){positionJoueur=0;jokerJoueur=0;break;}
                case 10: System.out.println("Une de perdue, 10 de retrouvées. T'as misé sur le mauvais cheval. Bois 2 gorgées et rejoue");
                         gorgeeJoueur++;positionJoueur += random.nextInt(6)+1;break;
                case 11: System.out.println("11 septembre : T'as l'bonjour de Ben Laden. Passe 2 tours.");
                         nbrTour++;break;
            }
            System.out.println();
        }

        scanner.close();
    }
}
