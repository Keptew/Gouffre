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
    public int getSize(){
        return this.playerList.size();
    }
    public void gorgeeGenerale(int nbrGorgee){
        for (Player j : this.playerList){
            j.ajoutGorgee(nbrGorgee);
        }
    }
    public Player vieChat(){
        List<Integer> resultatDe = new ArrayList<>(this.playerList.size());
        Players doublonsPotentiels = new Players();
        for (Player p : this.playerList){
            resultatDe.add(p.lancerDe());
        }
        return getPlayer(resultatDe, doublonsPotentiels);
    }
    private Player getPlayer(List<Integer> resultatDe, Players doublonsPotentiels) {
        Player pReturn;
        int IDLowest = 0;
        int lowestValue = resultatDe.getFirst();
        for (int i = 1; i < resultatDe.size(); i++) {
            if (resultatDe.get(i) < lowestValue) {
                lowestValue = resultatDe.get(i);
                IDLowest = i;
            }
        }
        resultatDe.remove(IDLowest);
        doublonsPotentiels.ajPlayer(this.playerList.get(IDLowest));
        int ind = 0;
        for (Integer i : resultatDe){
            if(i == lowestValue){
                doublonsPotentiels.ajPlayer(this.playerList.get(resultatDe.get(ind+1)));
            }
            ind++;
        }
        if(doublonsPotentiels.getSize()>1){
            pReturn = departagerJoueurs(doublonsPotentiels);
        } else {
            pReturn = doublonsPotentiels.getPlayerFromInt(0);
        }
        return pReturn;
    }
    public Player departagerJoueurs(Players joueurs){
        List<Integer> resultatDe = new ArrayList<>(this.playerList.size());
        Players doublonsPotentiels = new Players();
        for (int i = 0; i<joueurs.getSize();i++){
            Player temp = joueurs.getPlayerFromInt(i);
            resultatDe.add(temp.lancerDe());
        }
        return getPlayer(resultatDe, doublonsPotentiels);
    }
    public Player choixPlayer(){
        Player pReturn = null;
        int isAnswer = 0;
        while(isAnswer == 0) {
            Scanner s = new Scanner(System.in);
            System.out.println("Quel Joueur choisi-Tu ?");
            int i = 1;
            for (Player p : this.playerList){
                System.out.println(i+" - "+p.getName());
                i++;
            }
            int choix = s.nextInt()-1;
            if(choix<this.playerList.size()){
                System.out.println("Tu as choisi "+this.playerList.get(choix).getName());
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
