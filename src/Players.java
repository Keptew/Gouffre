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
        for(int i = 0;i<playerList.size();i++){
            if (playerList.get(i) == player){
                IndReturn = i;
            }
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
    public boolean isJoueursGuerre(){
        boolean isGuerre = false;
        for (Player p : this.playerList){
            if (p.isGuerre()) {
                isGuerre = true;
                break;
            }
        }
        return isGuerre;
    }

    public void finGuerre(){
        System.out.println("Fin de la guerre !");
        for (Player p : this.playerList){
            if(p.isGuerre()){
                p.setGuerre(false);
                p.setPosition(45);
            }
        }
    }
    public void boireGuerre(){
        for (Player p : this.playerList){
            if (p.isGuerre()){
                p.ajoutGorgee(1);
            }
        }
    }

    public boolean ak47(Player victime){
        boolean isShot = false;
        HashMap<Player, Integer> resultatTir = new HashMap<>();
        for (Player j : this.playerList){
            if (j != victime){
                resultatTir.put(j, j.choixDe());
            }
        }
        Integer resultatVictime = victime.lancerDe();
        for (Map.Entry m : resultatTir.entrySet()){
            if (m.getValue() == resultatVictime){
                Player temp = (Player) m.getKey();
                temp.mortJoueur();
                isShot = true;
            }
        }
        return isShot;
    }

    public Player magnum357(){
        Player pReturn = null;
        boolean isDead = false;
        for (int i = 0;!isDead && i < this.playerList.size(); i++ ){
            Player j = this.playerList.get(i);
            int result = j.lancerDe();
            if (result == 1){
                isDead = true;
                pReturn = j;
            }
        }
        return pReturn;
    }

    public Player joueurPlusBasScore(List<Player> playerList1) {
        Player pReturn = null;
        HashMap<Player, Integer> resultats = new HashMap<>();
        for (Player p : playerList1){
            resultats.put(p, p.lancerDe());
        }
        boolean isDoublons = false;
        int lowerValue = 7;
        for (Player p : resultats.keySet()){
            int resultat = resultats.get(p);
            if (resultat < lowerValue){
                lowerValue = resultat;
                pReturn = p;
                isDoublons = false;
            } else if (resultat == lowerValue) {
                isDoublons = true;
            }
        }
        if (isDoublons){
            HashMap<Player, Integer> doublons = getDoublons(resultats, lowerValue);
            List<Player> listDoublons = new ArrayList<>(doublons.keySet());
            joueurPlusBasScore(listDoublons);
        }else {
            System.out.println("C'est le joueur "+pReturn.getName()+" qui a perdu !");
        }
        return pReturn;
    }
    public HashMap<Player, Integer> getDoublons(HashMap<Player, Integer> liste, int lowerValue){
        HashMap<Player, Integer> doublons = new HashMap<>();
        for (Player p : liste.keySet()){
            int resultat = liste.get(p);
            if (resultat == lowerValue){
                doublons.put(p, resultat);
            }
        }
        return doublons;
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
