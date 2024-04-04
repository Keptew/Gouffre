import java.util.*;

public class Players {
    private final List<Player> playerList;
    public Players(){
        this.playerList = new ArrayList<>();
    }
    public boolean ajPlayer(Player j){
        boolean isAj = false;
        if(j != null){
            this.playerList.add(j);
            isAj = true;
        }
        return isAj;
    }
    public boolean rmPlayer(Player j){
        boolean isRm = false;
        if(this.playerList.contains(j)){
            this.playerList.remove(j);
            isRm = true;
        }
        return isRm;
    }
    public Player getPlayerFromName(String nameR){
        Player p = null;
        for (Player temp : this.playerList){
            if(Objects.equals(temp.getName(), nameR)){
                p = temp;
            }
        }
        return p;
    }
    public Player getPlayerFromInt(int index){
        return this.playerList.get(index);
    }
    public List<Player> getPlayerList(){return this.playerList;}
    public int getPlayerIndex(Player player){
        int IndReturn = 0;
        int i = 0;
        for(Player p : playerList){
            if(player == p){
                IndReturn = i;
            }
            i++;
        }
        return IndReturn;
    }
    public int getSize(){
        return this.playerList.size();
    }
    public void gorgeeGenerale(int nbrGorgee){
        for (Player j : this.playerList){
            j.ajoutGorgee(nbrGorgee);
        }
    }
    public void gorgeeGeneraleSauf(int nbrGorgee, Player player){
        for (Player j : this.playerList){
            if (j != player){
                j.ajoutGorgee(nbrGorgee);
            }
        }
    }
    public void case43(Player player){
        for (Player j : this.playerList){
            if (j != player){
                j.ajoutGorgee(j.lancerDe());
            }
        }
    }
    public void case46(){
        for (Player j : this.playerList){
            int resultat = j.lancerDe();
            if (resultat != 2 && resultat != 4 && resultat != 6){
                j.ajoutGorgee(2);
            }
        }
    }
    public Player vieChat(List<Player> playerList1) {
        Player pReturn = null;
        int nbrJoueurs = playerList1.size();
        Map<Player, Integer> resultatsDe = new HashMap<>();
        for(Player p : playerList1){
            resultatsDe.put(p, p.lancerDe());
        }
        int minValue = resultatsDe.get(getPlayerFromInt(0));
        List<Player> listDoublons = new ArrayList<>();
        boolean isDoublons = false;
        for (int resultat : resultatsDe.values()){
            if (resultat < minValue){
                minValue = resultat;
                listDoublons.clear();
                isDoublons = false;
            }else if(resultat == minValue){
                isDoublons = true;
                listDoublons = getPlayerFromResultatDe(resultatsDe, resultat);
            }
        }
        if (isDoublons){
            vieChat(listDoublons);
        }else {
            pReturn = getPlayerFromResultatDe(resultatsDe, minValue).getFirst();
        }
        System.out.println(STR."C'est le joueur \{Objects.requireNonNull(pReturn).getName()} qui a perdu !");
        return pReturn;
    }
    public static List<Player> getPlayerFromResultatDe(Map<Player, Integer> map, int targetValue) {
        List<Player> joueurs = new ArrayList<>();
        for (Map.Entry<Player, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(targetValue)) {
                joueurs.add(entry.getKey());
            }
        }

        return joueurs;
    }
    public List<Player> getPlayerWithJokerSauf(Player player){
        List<Player> playerWithJoker = new ArrayList<>();
        for (Player p : playerList){
            if (p != player && p.getNbrJoker() > 0){
                playerWithJoker.add(p);
            }
        }
        return playerWithJoker;
    }
    public boolean isJokerSauf(Player player){
        boolean isJoker = false;
        for (Player p : playerList){
            if (p != player){
                if (p.getNbrJoker() != 0){
                    isJoker = true;
                }
            }
        }
        return isJoker;
    }
    public Player choixPerdant(){
        Player pReturn = null;
        int isAnswer = 0;
        while(isAnswer == 0) {
            Scanner s = new Scanner(System.in);
            System.out.println("Quel Joueur a perdu ?");
            int i = 1;
            for (Player p : this.playerList){
                System.out.println(STR."\{i} - \{p.getName()}");
                i++;
            }
            int choix = s.nextInt()-1;
            if(choix<this.playerList.size()){
                System.out.println(STR."Tu as choisi \{this.playerList.get(choix).getName()}");
                isAnswer++;
                pReturn = this.playerList.get(choix);
            }
        }
        return pReturn;
    }
    public Player choixGagnant(){
        Player pReturn = null;
        int isAnswer = 0;
        while(isAnswer == 0) {
            Scanner s = new Scanner(System.in);
            System.out.println("Quel Joueur a gagné(gros) ?");
            int i = 1;
            for (Player p : this.playerList){
                System.out.println(STR."\{i} - \{p.getName()}");
                i++;
            }
            int choix = s.nextInt()-1;
            if(choix<this.playerList.size()){
                System.out.println(STR."Tu as choisi \{this.playerList.get(choix).getName()}");
                isAnswer++;
                pReturn = this.playerList.get(choix);
            }
        }
        return pReturn;
    }
    public Player choixJoueur(){
        Player pReturn = null;
        int isAnswer = 0;
        while(isAnswer == 0) {
            Scanner s = new Scanner(System.in);
            System.out.println("Quel Joueur choisis-tu ?");
            int i = 1;
            for (Player p : this.playerList){
                System.out.println(STR."\{i} - \{p.getName()}");
                i++;
            }
            int choix = s.nextInt()-1;
            if(choix<this.playerList.size()){
                System.out.println(STR."Tu as choisi \{this.playerList.get(choix).getName()}");
                isAnswer++;
                pReturn = this.playerList.get(choix);
            }
        }
        return pReturn;
    }
    public Player choixSpecificPlayer(Player player1){
        Player pReturn = null;
        int isAnswer = 0;
        while(isAnswer == 0) {
            Scanner s = new Scanner(System.in);
            System.out.println("Quel Joueur choisi-Tu ?");
            int i = 1;
            for (Player p : this.playerList){
                if(p != player1) {
                    System.out.println(STR."\{i} - \{p.getName()}");
                }
                i++;
            }
            int choix = s.nextInt()-1;
            if(choix<this.playerList.size()){
                System.out.println(STR."Tu as choisi \{this.playerList.get(choix).getName()}");
                isAnswer++;
                pReturn = this.playerList.get(choix);
            }
        }
        return pReturn;
    }
    public String listeJoueurs(){
        StringBuilder stringReturn;
        stringReturn = new StringBuilder("Voici la liste des joueurs : ");
        for (Player p : playerList){
            stringReturn.append("[").append(p.getName()).append("(x").append(p.getMultiplicateur()).append(")").append("]");
        }
        return stringReturn.toString();
    }
    public Player joueurEnTete(){
        Player pReturn = this.playerList.getFirst();
        for (Player p : this.playerList){
            if(p.getPosition() > pReturn.getPosition()){
                pReturn = p;
            }
        }
        return pReturn;
    }
    public Player joueurDevantPlusProche(Player player){
        Player playerPlusProche = player;
        int diffMin = playerList.size();
        for (Player p : this.playerList){
            if(p.getPosition() > player.getPosition()){
                int diff = p.getPosition() - player.getPosition();
                if(diff < diffMin){
                    diffMin = diff;
                    playerPlusProche = p;
                }
            }
        }
        return playerPlusProche;
    }
    public boolean checkWin(){
        boolean isWin = false;
        for (Player p : this.playerList){
            if (p.getPosition() == 70) {
                isWin = true;
                break;
            }
        }
        return isWin;
    }
    public Player winner(){
        Player pWinner = null;
        for (Player p : this.playerList){
            if(p.getPosition() == 70){
                pWinner = p;
            }
        }
        return pWinner;
    }

}
