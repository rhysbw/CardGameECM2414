import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CardGame {


    CardGame() {
    }

    public static void main(String[] args) {


        ArrayList<Player> players = new ArrayList<>();
        ArrayList<CardDeck> decks = new ArrayList<>();

        int numberOfPlayers = 0;
        String filename;
        Pack cardPack;
        boolean playerInput; // used to see if the playersInput is valid
        do {

            // ask for number of players
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter number of players: ");
            try {
                numberOfPlayers = sc.nextInt(); // gets the integer that the user has inputted
                playerInput = true;
            } catch (InputMismatchException e) { // checks input is a int
                System.out.println("Invalid player input");
                playerInput = false;
            }

            // Ask for filename of pack
            System.out.println("Enter the path or filename");
            filename = sc.next();

            cardPack = new Pack(filename, numberOfPlayers); // creates a new instance of packClass
        } while (!cardPack.verified() || !playerInput); // keeps asking until the packFile is verified and number of players is valid
        cardPack.importPack(); // imports the pack file to the pack class

        // makes the correct numbers of players and decks for the numbers of players
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player(i));
            decks.add(new CardDeck(i));
        }

        // Starting Player
        for (Player p : players) {
            p.start();
        }



        // deals the cards out
        deal(cardPack, players, decks, numberOfPlayers);

        // until a player has won, make players take a turn
        while (!checkIfPlayerHasWon(players, decks)) {
            for (Player p : players) {
                p.takeTurn(decks);
            }
        }
    }

    public static void deal(Pack cardPack, ArrayList<Player> players, ArrayList<CardDeck> decks, int numberOfPlayers) {
        // deals to the players
        for (int i = 0; i < cardPack.getCards().size() / 2; i++) {
            // this uses the first half of the deck
            players.get(i % numberOfPlayers).addCardToHand(cardPack.getCards().get(i));
        }

        // deal to decks
        for (int i = cardPack.getCards().size() / 2; i < cardPack.getCards().size(); i++) {
            // this only uses the second half of the deck, and gives the correct card to the correct deck
            decks.get(i % numberOfPlayers).putBottom(cardPack.getCards().get(i));
        }
    }

    // checks if any player has a winning hand
    public static boolean checkIfPlayerHasWon(ArrayList<Player> players, ArrayList<CardDeck> decks) {
        boolean hasPlayerWon = false;
        int playerThatWon = 0; // player number of the winning player
        for (Player p : players) { // looks if any player has won
            if (p.checkWin()) {
                hasPlayerWon = true;
                playerThatWon = p.getPlayerNum(); // sets playerThatWon to the player number of the winning player
            }
        }
        // If a player has won notify all players of the winning player
        if (hasPlayerWon) {
            for (Player p : players) { // for all the players
                p.notified(playerThatWon); // notify player that a player has won
            }
            for (CardDeck d : decks) { // for all the decks
                d.writeContentsToFile(); // write to file
            }
            return true;
        }
        return false;
    }


}


