import java.util.List;
import java.util.Scanner;

public class InterfaceJeu {
    private String sensPlateau;

    public InterfaceJeu() {
        this.sensPlateau = "horaire";
    }
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
                        Parcours(choixJoueurs(),0);
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
            System.out.println(StringTemplate.STR."Choisissez un nom pour le joueur \{i}");
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
    public void Parcours(Players joueurs, int playerStart) {
        Scanner scanner = new Scanner(System.in);
        if(sensPlateau.equals("horaire")) {
            for (int i = playerStart; i < joueurs.getSize(); i++) {
                Player joueur = joueurs.getPlayerFromInt(i);
                int nbrGorgee = joueur.getNbrGorgee();
                int multiplicateur = joueur.getMultiplicateur();
                int nbrSecs = joueur.getNbrSec();
                int nbrJoker = joueur.getNbrJoker();
                System.out.println(StringTemplate.STR."Au tour de \{joueur.getName()} !");
                System.out.println(StringTemplate.STR."Tu es actuellement sur la case \{joueur.getPosition()}");
                System.out.println("Voici tes statistiques pour le moment : ");
                System.out.println(StringTemplate.STR."Nombre de gorgée : \{nbrGorgee}");
                System.out.println(StringTemplate.STR."Nombre de secs : \{nbrSecs}");
                System.out.println(StringTemplate.STR."Multiplicateur : \{multiplicateur}");
                System.out.println(StringTemplate.STR."Nombre de joker : \{nbrJoker}");
                if (joueur.getNbrTour() < 1) {
                    Jouer(joueur, joueurs);
                } else {
                    System.out.println("Vous passez un tour !");
                    System.out.println("\n");
                    joueur.setNbrTour(-1);
                    break;
                }
            }
            do {
                for (int i = 0; i < joueurs.getSize(); i++) {
                    Player joueur = joueurs.getPlayerFromInt(i);
                    int nbrGorgee = joueur.getNbrGorgee();
                    int multiplicateur = joueur.getMultiplicateur();
                    int nbrSecs = joueur.getNbrSec();
                    int nbrJoker = joueur.getNbrJoker();
                    System.out.println(StringTemplate.STR."Au tour de \{joueur.getName()} !");
                    System.out.println(StringTemplate.STR."Tu es actuellement sur la case \{joueur.getPosition()}");
                    System.out.println("Voici tes statistiques pour le moment : ");
                    System.out.println(StringTemplate.STR."Nombre de gorgée : \{nbrGorgee}");
                    System.out.println(StringTemplate.STR."Nombre de secs : \{nbrSecs}");
                    System.out.println(StringTemplate.STR."Multiplicateur : \{multiplicateur}");
                    System.out.println(StringTemplate.STR."Nombre de joker : \{nbrJoker}");
                    if (joueur.getNbrTour() < 1) {
                        Jouer(joueur, joueurs);
                        if (joueurs.checkWin()){
                            System.out.println(StringTemplate.STR."Féliciations à \{joueurs.winner().getName()} pour la victoire !");
                            System.out.println("Appuyez sur entrée pour retourner au menu");
                            scanner.nextLine();
                            MenuJeu();
                        }
                    } else {
                        System.out.println("Vous passez un tour !");
                        System.out.println("\n");
                        joueur.setNbrTour(-1);
                        break;
                    }
                }
            } while (!joueurs.checkWin());
        }
        else if (sensPlateau.equals("antihoraire")){
            for (int i = playerStart; i >= 0; i--){
                Player joueur = joueurs.getPlayerFromInt(i);
                int nbrGorgee = joueur.getNbrGorgee();
                int multiplicateur = joueur.getMultiplicateur();
                int nbrSecs = joueur.getNbrSec();
                int nbrJoker = joueur.getNbrJoker();
                System.out.println(StringTemplate.STR."Au tour de \{joueur.getName()} !");
                System.out.println(StringTemplate.STR."Tu es actuellement sur la case \{joueur.getPosition()}");
                System.out.println("Voici tes statistiques pour le moment : ");
                System.out.println(StringTemplate.STR."Nombre de gorgée : \{nbrGorgee}");
                System.out.println(StringTemplate.STR."Nombre de secs : \{nbrSecs}");
                System.out.println(StringTemplate.STR."Multiplicateur : \{multiplicateur}");
                System.out.println(StringTemplate.STR."Nombre de joker : \{nbrJoker}");
                if (joueur.getNbrTour() < 1) {
                    Jouer(joueur, joueurs);
                    if (joueurs.checkWin()){
                        System.out.println(StringTemplate.STR."Féliciations à \{joueurs.winner().getName()} pour la victoire !");
                        System.out.println("Appuyez sur entrée pour retourner au menu");
                        scanner.nextLine();
                        MenuJeu();
                    }
                } else {
                    System.out.println("Vous passez un tour !");
                    System.out.println("\n");
                    joueur.setNbrTour(-1);
                    break;
                }
            }
            do {
                for (int i = joueurs.getSize()-1; i >= 0; i--){
                    Player joueur = joueurs.getPlayerFromInt(i);
                    int nbrGorgee = joueur.getNbrGorgee();
                    int multiplicateur = joueur.getMultiplicateur();
                    int nbrSecs = joueur.getNbrSec();
                    int nbrJoker = joueur.getNbrJoker();
                    System.out.println(StringTemplate.STR."Au tour de \{joueur.getName()} !");
                    System.out.println(StringTemplate.STR."Tu es actuellement sur la case \{joueur.getPosition()}");
                    System.out.println("Voici tes statistiques pour le moment : ");
                    System.out.println(StringTemplate.STR."Nombre de gorgée : \{nbrGorgee}");
                    System.out.println(StringTemplate.STR."Nombre de secs : \{nbrSecs}");
                    System.out.println(StringTemplate.STR."Multiplicateur : \{multiplicateur}");
                    System.out.println(StringTemplate.STR."Nombre de joker : \{nbrJoker}");
                    if (joueur.isGuerre()){
                        System.out.println("Vous passez votre tour !");
                        System.out.println("\n");
                    }else if (joueur.getNbrTour() < 1) {
                        Jouer(joueur, joueurs);
                        if (joueurs.checkWin()) {
                            System.out.println(StringTemplate.STR."Féliciations à \{joueurs.winner().getName()} pour la victoire !");
                            System.out.println("Appuyez sur entrée pour retourner au menu");
                            scanner.nextLine();
                            MenuJeu();
                            }
                        } else {
                        System.out.println("Vous passez un tour !");
                        System.out.println("\n");
                        joueur.setNbrTour(-1);
                        break;
                    }
                }
            }while (!joueurs.checkWin());
        }
    }

    public void Jouer(Player joueur, Players joueurs) {
        Scanner scanner = new Scanner(System.in);
        int de = joueur.lancerDe();
        if (joueurs.isJoueursGuerre()){
            if (de == 6){
                joueurs.finGuerre();
            }else {
                joueurs.boireGuerre();
            }
        }
        int newPosition = joueur.getPosition() + de;
        joueur.setPosition(newPosition);
        System.out.println(StringTemplate.STR."Tu es désormais sur la case \{joueur.getPosition()}");
        if (joueur.getNbrJoker() == 0){
            effetCase(joueur, joueurs, newPosition);
        }else {
            boolean jokerCheck = joueur.jokerCheck();
            if (jokerCheck){
                System.out.println("Vous avez utilisé votre joker ! ");
            }else {
                effetCase(joueur, joueurs, newPosition);
            }
        }

    }


    public void effetCase(Player joueur, Players joueurs, int newPosition){
        Scanner scanner = new Scanner(System.in);
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
                joueurs.choixSpecificPlayer(joueur).ajoutGorgee(6);
                scanner.nextLine();break;
            case 7:
                System.out.println("Bottes de 7 lieues : Tu viens de voler les chaussures de Flash Gordon. Avance 1 case devant le joueur de tête. Si tu es déjà premier, rejoue.");
                if (joueurs.joueurEnTete() != joueur) {
                    joueur.setPosition(joueurs.joueurEnTete().getPosition() + 1);
                    effetCase(joueur, joueurs, joueur.getPosition());
                } else {Jouer(joueur, joueurs);}
                scanner.nextLine();break;
            case 8:
                System.out.println("Grand 8 : Bois 2 gorgées et fais 8 tours sur toi-même.");
                joueur.ajoutGorgee(2);scanner.nextLine();break;
            case 9:
                System.out.println("Les 9 vies d'un chat : Chaque joueur lance le dé. Le plus petit score se fait écraser et retourne sur la case départ. Si tu avais un joker, dit lui adieu");
                joueurs.joueurPlusBasScore(joueurs.getPlayerList()).mortJoueur();scanner.nextLine();break;

            case 10:
                System.out.println("Une de perdue, 10 de retrouvées. T'as misé sur le mauvais cheval. Bois 2 gorgées et rejoue");
                joueur.ajoutGorgee(2);Jouer(joueur, joueurs);scanner.nextLine();break;
            case 11:
                System.out.println("11 septembre : T'as l'bonjour de Ben Laden. Passe 2 tours.");
                joueur.setNbrTour(2);scanner.nextLine();break;
            case 12:
                System.out.println("Les 12 coups de minuit : L'heure du crime... Lance le dé et fais boire le nombre de gorgées qu'il indiquera au joueur de ton choix");
                int nbrGorgee = joueur.lancerDe();
                joueurs.choixSpecificPlayer(joueur).ajoutGorgee(nbrGorgee);
                scanner.nextLine();break;
            case 13:
                System.out.println("Vendredi 13 : Tous tes malheurs sont doublés jusqu'à la fin de la partie, sauf si tu meurs avant");
                joueur.setMultiplicateur(joueur.getMultiplicateur()*2);break;
            case 14:
                System.out.println("Chercher midi à 14 heures : Une brèche spatio-temporelle vient de s'ouvrir sous vos pieds. L'ordre du lancé du dé s'inverse.");
                this.sensPlateau = "antihoraire";
                Parcours(joueurs, joueurs.getPlayerIndex(joueur));break;
            case 15:
                System.out.println("SAMU 15 : C'est parti pour un tour d'ambulance. Lance le dé et recule du nombre de cases qu'il t'indiquera");
                int nbrRecul = joueur.lancerDe();
                joueur.setPosition(joueur.getPosition()-nbrRecul);
                effetCase(joueur, joueurs, joueur.getPosition());break;
            case 16:
                System.out.println("Alors comme ça tu vis dans l'16ème : Ici c'est l'Gouffre... Bois 2 gorgées.");
                joueur.ajoutGorgee(2);break;
            case 17:
                System.out.println("17 G.A.V : Ton voisin a appelé les keufs. Passe un tour, et si tu as un joker tu le perds à la fouille");
                joueur.setNbrTour(1);
                joueur.setNbrJoker(0);break;
            case 18:
                System.out.println("Interdit aux moins de 18 : Le dernier à mettre son majeur en l'air bois 2 gorgées.");
                joueurs.choixPerdant().ajoutGorgee(2);break;
            case 19:
                System.out.println("En verlan ça fait 9-1 : Représente juste pour nous-même. Tout l'monde boit 1 gorgée sauf toi.");
                joueurs.gorgeeGeneraleSauf(1, joueur);break;
            case 20:
                System.out.println("Qui connaît Mr Tilitre ??? Vincent Tilitre : Finis ton verre.");
                joueur.ajoutSec(1);break;
            case 21:
                System.out.println("Zezette : Lance le dé, si tu fais un 2 ou un 1, tu distribue 4 gorgées, sinon tu les bois");
                int resultat = joueur.lancerDe();
                if (resultat == 2 || resultat == 1){
                    joueurs.choixSpecificPlayer(joueur).ajoutGorgee(4);
                }else {
                    joueur.ajoutGorgee(4);
                }
                break;
            case 22:
                System.out.println("22 V'là les flics : Course poursuite !!! Tous les joueurs lancent le dé, le plus petit score se fait péter et finit en G.A.V case 17.");
                Player perdant = joueurs.joueurPlusBasScore(joueurs.getPlayerList());
                perdant.setPosition(17);
                effetCase(perdant, joueurs, 17);break;
            case 23:
                System.out.println("Jamais 2 sans 3: Bois 2 gorgées et offre la 3ème au joueur de ton choix");
                joueur.ajoutGorgee(2);
                joueurs.choixSpecificPlayer(joueur).ajoutGorgee(1);break;
            case 24:
                System.out.println("24 Heures du Mans : Tes adversaires sont au stand, double-les. Avance une case devant le joueur qui te devances. Si tu es en tête, rejoue.");
                Player joueurDevant = joueurs.joueurDevantPlusProche(joueur);
                if (joueurDevant == joueur){
                    Jouer(joueur, joueurs);
                }else {
                    joueur.setPosition(joueurDevant.getPosition()+1);
                    effetCase(joueur, joueurs, joueur.getPosition());
                }
                break;
            case 25:
                System.out.println("25 Décembre : C'est Noël, mais dans le Gouffre rares sont les cadeaux... Lance le dé, si tu fais un 2 ou un 5 tu gagnes un joker.");
                int result = joueur.lancerDe();
                if(result == 2 || result == 5){
                    joueur.ajoutJoker(1);
                }
                break;
            case 26:
                System.out.println("26 lettres de l'alphabet : Choisis une lettre, le 1er à trouver le nom d'un rappeur commençant par celle-ci fait boire 2 gorgées au joueur de son choix");
                joueurs.choixSpecificPlayer(joueurs.choixGagnant()).ajoutGorgee(2);break;
            case 27:
                System.out.println("Club des 27: La mort t'as fauché en pleine glore, retournes à la case départ... Si tu avais un joker il passe à la trappe");
                joueur.mortJoueur();break;
            case 28:
                System.out.println("28 jours plus tard : La mutation est en cours... Bois 2 gorgées.");
                joueur.ajoutGorgee(2);break;
            case 29:
                System.out.println("29 Février : Tu fêtes ton anniversaire tous les 4 ans. Passe 3 tours.");
                joueur.setNbrTour(3);break;
            case 30:
                System.out.println("30 Millions d'enemis : Tous les joueurs lancent le dé sauf toi, celui ou ceux qui feront un 3 iront sur la case 13 et en paierons les conséquences...");
                for (int i = 0; i < joueurs.getSize(); i++){
                    Player temp = joueurs.getPlayerFromInt(i);
                    if (temp != joueur){
                        if (temp.lancerDe() == 3){
                            temp.setPosition(13);
                            effetCase(temp, joueurs, 13);
                        }
                    }
                }
                break;
            case 31:
                System.out.println("Tu t'es mis sur ton 31 : Tout l'monde boit 1 gorgée sauf toi.");
                joueurs.gorgeeGeneraleSauf(1, joueur);break;
            case 32:
                System.out.println("Souris ! Montre-nous tes 32 dents : Changement de corps. Change de pion avec le joueur de ton choix. Tu hérites de sa case, de ses jokers comme de ses poisses, et vice-versa");
                int tempPos = joueur.getPosition();
                int tempMulti = joueur.getMultiplicateur();
                int tempJoker = joueur.getNbrJoker();
                Player tempPlay = joueurs.choixSpecificPlayer(joueur);
                joueur.setPosition(tempPlay.getPosition());
                joueur.setNbrJoker(tempPlay.getNbrJoker());
                joueur.setMultiplicateur(tempPlay.getMultiplicateur());
                tempPlay.setPosition(tempPos);
                tempPlay.setPosition(tempMulti);
                tempPlay.setNbrJoker(tempJoker);break;
            case 33:
                System.out.println("33 Tours : Tu tournes en rond comme un vinyle. Bois 1 gorgée.");
                joueur.ajoutGorgee(1);break;
            case 34:
                System.out.println("3/4 Cuir : Fusillade !!! Choisis un bouclier humain. Le joueur de ton choix se fait descendre et retournes sur la case départ. PS : Fouilles-le, voir si il a pas un joker");
                Player temp = joueurs.choixSpecificPlayer(joueur);
                if (temp.getNbrJoker() > 0){
                    joueur.ajoutJoker(temp.getNbrJoker());
                }
                temp.mortJoueur();break;
            case 35:
                System.out.println("Les 35 heures c'est fini : Il est l'heure de faire des heures sup... Lance le dé et bois le nombre de gorgées qu'il indiquera.");
                joueur.ajoutGorgee(joueur.lancerDe());break;
            case 36:
                System.out.println("36ème chambre de Shaolin : ODB revient des morts te donner son joker qu'il n'a pas eu le temps d'utiliser... Tu gagnes un joker à utiliser avant la fin de la partie.");
                joueur.ajoutJoker(1);break;
            case 37:
                System.out.println("37,5° : La température monte... Bois 1 bouchon.");break;
            case 38:
                System.out.println("Amandine du 38 : Chante quelque phases du son de ton choix.");break;
            case 39:
                System.out.println("Début de la guerre 39-45 : Attends qu'un joueur fasse un 6 pour que la guerre se finisse et que tu ailles sur la case 45 sinon bois une gorgée à chaque défaite, c'est-à-dire à chaque fois que quelqu'un lance le dé.");
                joueur.setGuerre(true);break;
            case 40:
                System.out.println("Ali baba et les 40 voleurs : Si un des joueurs a un joker tu peux le lui voler... Sinon tant pis pour toi, bois 2 gorgéess.");
                if (joueurs.isJokerSauf(joueur)){
                    int isAnswer = 0;
                    while(isAnswer == 0) {
                        System.out.println("Quel Joueur choisi-Tu ?");
                        List<Player> playerWithJoker = joueurs.getPlayerWithJokerSauf(joueur);
                        for (int i = 0; i < playerWithJoker.size();i++){
                            Player pTemp = playerWithJoker.get(i);
                            int ind = i+1;
                            System.out.println(StringTemplate.STR."\{ind} - \{pTemp.getName()}");
                        }
                        int choix = scanner.nextInt()-1;
                        if (choix<playerWithJoker.size() || choix>0){
                            Player joueurVole = playerWithJoker.get(choix);
                            System.out.println(StringTemplate.STR."Tu as choisi \{joueurVole.getName()}");
                            int nbrVol = joueurVole.getNbrJoker();
                            joueurVole.setNbrJoker(0);
                            joueur.ajoutJoker(nbrVol);
                            isAnswer++;
                        }
                    }
                }else {
                    joueur.ajoutGorgee(2);
                }
                break;
            case 41:
                System.out.println("4-1 Catin : On t'as pété les reins, tu boites... Le SAMU est venu te chercher, retournes sur la case 15.");
                joueur.setPosition(15);
                effetCase(joueur, joueurs, 15);break;
            case 42:
                System.out.println("Marathon 42km : La course est loin d'être finie, prends des forces on t'offre à boire. Bois 2 gorgées dans le verre de l'adversaire de ton choix.");
                joueur.ajoutGorgee(2);break;
            case 43:
                System.out.println("Goerges W.Bush 43ème président des Etats-Unis : Bouton rouge ! Arme de destruction massive ! Tous tes adversaires lancent le dé et boivent le nombre de gorgées qu'il indiquera.");
                joueurs.case43(joueur);break;
            case 44:
                System.out.println("4x4 : Va sur la case du joueur de ton choix.");
                joueur.setPosition(joueurs.choixSpecificPlayer(joueur).getPosition());break;
            case 45:
                System.out.println("Fin de la guerre 39-45 : Armistice : Tout l'monde trinque et boit 1 gorgée.");
                joueurs.gorgeeGenerale(1);
            case 46:
                System.out.println("En 2-4-6 : Chaque joueur lance le dé, celui qui ne fais pas un 2, 4 ou 6 boit 2 gorgées");
                joueurs.case46();break;
            case 47:
                System.out.println("AK-47 : Tire dans l'tas. Chacun de tes adversaires choisit un chiffre de 1 à 6(ils peuvent choisir le même). Lance le dé, si le chiffre d'un des joueurs sort, tu l'éxecute et il devra retourner à la case départ. Sinon va en G.A.V case 48");
                if (!joueurs.ak47(joueur)){
                    joueur.setPosition(48);
                    effetCase(joueur, joueurs, 48);
                }
                break;
            case 48:
                System.out.println("48 heures de G.A.V : La garde à vue s'éternise... Passe 2 tours. Si tu as un joker tu le perds à la fouille.");
                joueur.setNbrTour(2);
                joueur.setNbrJoker(0);break;
            case 49:
                System.out.println("Marche Arrière : Tout l'monde recule d'1 case et subit le gage de la case sur laquelle il atterit.");
                for (int i = 0; i < joueurs.getSize(); i++){
                    Player victime = joueurs.getPlayerFromInt(i);
                    victime.setPosition(victime.getPosition()-1);
                    effetCase(victime, joueurs, victime.getPosition());
                }
            case 50:
                System.out.println("Top 50 : Fredonne un air connu, le premier à trouver le titre ou l'interprète fais boire 3 gorgées à l'adversaire de son choix.");
                joueurs.choixSpecificPlayer(joueurs.choixGagnant()).ajoutGorgee(3);break;
            case 51:
                System.out.println("Zone 51 : Tu as été enlevé par les extra-terrestres. Passe 1 tour.");
                joueur.setNbrTour(1);break;
            case 52:
                System.out.println("Coup d'poker 52 cartes : Tapis. Choisis le joueur de ton choix pour une partie de pierre-feuille-ciseaux. Le perdant devra finir son verre");
                joueurs.choixPerdant().ajoutSec(1);break;
            case 53:
                System.out.println("Cocinelle n°53 : Panne sèche, va chercher d'l'essence. Lance le dé et recule du nombre de case qu'il indiquera.");
                int recul = joueur.lancerDe();
                joueur.setPosition(joueur.getPosition()-recul);
                effetCase(joueur, joueurs, joueur.getPosition());break;
            case 54:
                System.out.println("5 4 3 2 1 Feuuu(j) : Tout le monde boit une gorgée. Le dernier à la boire en reboit 2 autres.");
                joueurs.gorgeeGenerale(1);
                joueurs.choixPerdant().ajoutGorgee(1);break;
            case 55:
                System.out.println("Reçu 5/5 : Distribue 5 gorgées à l'adversaire de ton choix.");
                joueurs.choixSpecificPlayer(joueur).ajoutGorgee(5);break;
            case 56:
                System.out.println("56 kbits/s : Putain tu rames mec, ici on boit à haut débit(e). Boit 3 gorgées");
                joueur.ajoutGorgee(3);break;
            case 57:
                System.out.println("3-5-7 Magnum : Roulette Russe... Tous les joueurs lancent le dé, le 1er à faire 1 s'en prend une en pleine tête, et retournes à la case départ");
                joueurs.magnum357().mortJoueur();break;
            case 58:
                System.out.println("58 Minutes pour vivre : Tu es invincible comme John Mac Clane. Tu gagnes un joker");
                joueur.ajoutJoker(1);break;
            case 59:
                System.out.println("59 Bienvenue chez les ch'tis : Bois 1 gorgée à la bouteille");
                joueur.ajoutGorgee(1);break;
            case 60:
                System.out.println("60 secondes chrono : Minute de silence. Tu n'as plus l'droit à la parole durant 1 minute, sinon finis ton verre cul sec");
                break;
            case 61:
                System.out.println("6/1 6/1 : Jeu set et match : Victoire écrasante. Fais boire 2 gorgées au joueur de ton choix");
                joueurs.choixSpecificPlayer(joueur).ajoutGorgee(2);break;
            case 62:
                System.out.println("6-2-PUTE.");break;
            case 63:
                System.out.println("28 Août 1963 : I Have a Dream... Chez nous les rêves se finissent toujours en cauchemar... Avance de 6 cases.");
                joueur.setPosition(69);
                effetCase(joueur, joueurs, 69);break;
            case 64:
                System.out.println("64 cases en moins : Échec et mat. Le Gouffre t'as vaincu. Avance de 5 cases.");
                joueur.setPosition(69);
                effetCase(joueur, joueurs, 69);break;
            case 65:
                System.out.println("65 ans : L'âge de la retraite : Dans l'Gouffre on ne l'atteint pas... Avance de 4 cases.");
            case 66:
                System.out.println("Route 66 : Elle te guide vers ta décadence. Avance de 3 cases.");
            case 67:
                System.out.println("6+7 = 13 : La poisse t'as rattrapé. Retourne sur la case 13.");
                joueur.setPosition(13);
                effetCase(joueur, joueurs, 13);break;
            case 68:
                System.out.println("Mai 68 : Toute révolution est inutile. Tu ne peux pas changer ta destinée. Avance d'1 case.");
            case 69:
                System.out.println("LE GOUFFRE TE LA FAIT TOUJOURS À L'ENVERS");
                System.out.println("ON NE PEUT PAS SORTIR DU GOUFFRE, FAIS-TOI UNE RAISON...");
                System.out.println("FINIS TON VERRE ET SAVOURE LA DÉFAITE");
                System.out.println("AHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHHAAHAH");
                System.out.println("(Les plus téméraires peuvent repartir pour un tour)");
                joueur.mortJoueur();break;
        }
    }
}
