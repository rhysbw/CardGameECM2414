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


    public static boolean checkIfPlayerHasWon(ArrayList<Player> players, ArrayList<CardDeck> decks) {
        // Check if any players have won - player.checkWin() -> bool
        // If a player has won
        // player.notified (Player id) -> of the player that's won

        // return true
        // else
        // return false
        boolean hasPlayerWon = false;
        int playerThatWon = 0;
        for (Player p : players) {
            if (p.checkWin()) {
                hasPlayerWon = true;
                playerThatWon = p.getPlayerNum();
            }
        }
        // If a player has won notify all players of the winning player
        if (hasPlayerWon) {
            for (Player p : players) {
                p.notified(playerThatWon);
                System.out.println("Player "+ p.getPlayerNum() + " Has: ");
                for (Card c: p.getHand()){
                    System.out.println(c.getCardValue());
                }
            }
            for (CardDeck d : decks) {
                d.writeContentsToFile();
            }
            return true;
        }
        return false;
    }


}
