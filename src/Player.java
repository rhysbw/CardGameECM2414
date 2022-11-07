import java.util.ArrayList;

public class Player extends Thread {
    private final int preferredCard;
    private final int playerNum;
    private ArrayList<Card> hand;

    Player(int playerNum) {
        this.playerNum = playerNum;
        this.preferredCard = playerNum;
        this.hand = new ArrayList<>();
    }

    private void drawCard() {
    }

    private Card discardCard() {
        return null;
    }

    private boolean checkWin() {
        return false;
    }

    private void takeTurn() {
    }

    private void makeOutputFile() {
    }

    private void writeToOutputFile() {
    }

    public int getPreferredCard() {
        return preferredCard;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
