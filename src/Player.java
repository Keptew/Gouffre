import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private int position;
    private int nbrTour;
    private int nbrGorgee;
    private int nbrSec;
    private int nbrJoker;
    private int multiplicateur;
    public Player(){
        position = 0;
        nbrTour = 0;
        nbrGorgee = 0;
        nbrSec = 0;
        nbrJoker = 0;
        multiplicateur = 1;
    }
    public Player(String newName, int newMultiplicateur){
        this.name = newName;
        this.multiplicateur = newMultiplicateur;
    }
    public String getName() {
        return name;
    }
    public void setName(String nomJoueur) {
        this.name = nomJoueur;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getNbrTour() {
        return nbrTour;
    }
    public void setNbrTour(int nbrTour) {
        this.nbrTour = nbrTour;
    }
    public int getNbrGorgee() {
        return nbrGorgee;
    }
    public void setNbrGorgee(int nbrGorgee) {
        this.nbrGorgee = nbrGorgee;
    }
    public void ajoutGorgee(int nbrAdd){
        this.nbrGorgee += (nbrAdd*this.multiplicateur);
    }
    public int getNbrSec() {
        return nbrSec;
    }
    public void setNbrSec(int nbrSec) {
        this.nbrSec = nbrSec;
    }
    public void ajoutSec(int nbrAdd){
        this.nbrSec += (nbrAdd*this.multiplicateur);
    }
    public int getNbrJoker() {
        return nbrJoker;
    }
    public void setNbrJoker(int nbrJoker) {
        this.nbrJoker = nbrJoker;
    }
    public void ajoutJoker(int nbrAdd){
        this.nbrJoker += nbrAdd;
    }
    public int getMultiplicateur() {
        return multiplicateur;
    }
    public void setMultiplicateur(int multiplicateur) {
        this.multiplicateur = multiplicateur;
    }
    public boolean jokerCheck(){
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
                    this.nbrJoker--;
                    break;
                case 2:
                    isAnswer = 1;
                    break;
                default:break;
            }
        }
        return isJoker;
    }
    public void mortJoueur(){
        this.position = 0;
        this.nbrJoker = 0;
    }
    public int lancerDe(){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Appuyez sur Entrée pour lancer le dé...");
        scanner.nextLine();
        int de = random.nextInt(6) + 1;
        System.out.println("Vous avez obtenu un " + de);
        scanner.nextLine();
        return de;
    }
    @Override
    public String toString() {
        return name+" {" +
                ", position=" + position +
                ", nbrTour=" + nbrTour +
                ", nbrGorgee=" + nbrGorgee +
                ", nbrSec=" + nbrSec +
                ", nbrJoker=" + nbrJoker +
                ", multiplicateur=" + multiplicateur +
                '}';
    }
}
