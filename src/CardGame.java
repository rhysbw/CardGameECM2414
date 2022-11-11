import java.util.Arrays;

public class CardGame {
    CardGame() {
    }
    public static void main(String[] args){
        // TEST CODE TO CHECK PACK IMPORTING WORKS
        Pack testPack = new Pack("testPack.txt", 1);
        for (Card c : testPack.getCards()){
            System.out.println(c.getCardValue());
        }
    }

    // checks if any player has a winning hand
    public static boolean checkIfPlayerHasWon(ArrayList<Player> players, ArrayList<CardDeck> decks) {
        boolean hasPlayerWon = false;
        int playerThatWon = 0;
        for (Player p : players) { // looks if any player has won
            if (p.checkWin()) {
                hasPlayerWon = true;
                playerThatWon = p.getPlayerNum();
            }
        }
        // If a player has won notify all players of the winning player
        if (hasPlayerWon) {
            for (Player p : players) {
                p.notified(playerThatWon); // notify player that a player has won
            }
            for (CardDeck d : decks) { // write contents of all decks used
                d.writeContentsToFile();
            }
            return true;
        }
        return false;
    }


}
