import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Thread {
    private final int preferredCard;
    private final int playerNum;
    private ArrayList<Card> hand;

    Player(int playerNum) {
        this.playerNum = playerNum;
        this.preferredCard = playerNum;
        this.hand = new ArrayList<>();
        makeOutputFile();

    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    // this draws the top card from the deck related to the player and writes to the output file
    private void drawCard(CardDeck deck) {
        Card elem = deck.getTopCard();
        this.hand.add(elem);
        writeToOutputFile("Player " + this.playerNum + " draws card " + elem.getCardValue() + " from deck " + deck.getDeckNum());
        writeToOutputFile("Player " + this.playerNum + " current hand:" + getHandAsString());

    }

    private void discardCard(CardDeck deck) {
        int int_random = 0;
        // gets a random card from the hand, that isn't the preferred card value
        for (; ; ) {

            int_random = new Random().nextInt(0, 4);
            if (this.hand.get(int_random).getCardValue() != this.preferredCard) {
                break;
            }
        }
        // puts the card into the bottom of the deck
        deck.putBottom(this.hand.get(int_random));

        writeToOutputFile("Player " + this.playerNum + " discards card " + this.hand.get(int_random).getCardValue()
                + " to deck" + deck.getDeckNum());

        // removes the card from the hand
        this.hand.remove(int_random);

    }

    // this checks if all the cards in the hand are of the same value
    public boolean checkWin() {
        return this.hand.get(0).getCardValue() == this.hand.get(1).getCardValue() &&
                this.hand.get(0).getCardValue() == this.hand.get(2).getCardValue() &&
                this.hand.get(0).getCardValue() == this.hand.get(3).getCardValue();

    }

    // has a turn - picks up and discards cards
    public void takeTurn(ArrayList<CardDeck> decks) {

        // checks which deck a card should be discarded too
        if (playerNum == decks.size()) {
            discardCard(decks.get(0));
        } else {
            discardCard(decks.get(this.playerNum));
        }

        // draws a card from the correct deck
        drawCard(decks.get(this.playerNum - 1));

    }

    // gets the current hand of a player as a string for outputting
    private String getHandAsString() {
        String currentHand = "";
        for (Card c : hand) {
            currentHand = currentHand + " " + c.getCardValue();
        }
        return currentHand;
    }

    // runs when a player has won
    public void notified(int winningPlayerNumber) {

        // if its this player that won, write: to output file
        if (winningPlayerNumber == this.playerNum) {
            writeToOutputFile("Player " + this.playerNum + " wins");
            writeToOutputFile("Player " + this.playerNum + " exits");
            writeToOutputFile("Player " + this.playerNum + " final hand:" + getHandAsString());
            System.out.println("Player " + this.playerNum + " has won"); // prints to terminal window that the winning player has won
        } else { // if a different player has won
            writeToOutputFile("Player " + winningPlayerNumber + " has informed player " + this.playerNum + " that player "
                    + winningPlayerNumber + " has won");
            writeToOutputFile("Player " + this.playerNum + " exits");
            writeToOutputFile("Player " + this.playerNum + " hand:" + getHandAsString());
        }
        // end the thread
        this.interrupt();

    }

    // this makes an output file
    private void makeOutputFile() {
        try {
            File playerFile = new File("player" + this.playerNum + "_output.txt");
            if (playerFile.createNewFile()) {
                System.out.println("File created: " + playerFile.getName());
            } else {
                // if there already is a output file, deletes file to start from fresh
                playerFile.delete();
                makeOutputFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred in making a player file");
            e.printStackTrace();
        }
    }

    // writes to a new line in the file
    private void writeToOutputFile(String output) {
        try {
            BufferedWriter playerWriter = new BufferedWriter(new FileWriter("player" + this.playerNum + "_output.txt", true));
            playerWriter.write(output);
            playerWriter.newLine();
            playerWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in writing to a player file");
            e.printStackTrace();
        }
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
