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

    // this draws the top card from the deck related to the player and writes to the output file
    private void drawCard(CardDeck deck) {
        Card elem = deck.getTopCard();
        System.out.println("Player " + this.playerNum + " draws card " + elem.getCardValue() + " from deck " + deck.getDeckNum());
        this.hand.add(elem);
        writeToOutputFile("Player " + this.playerNum + " draws card " + elem.getCardValue() + " from deck " + deck.getDeckNum());
    }

    private void discardCard(CardDeck deck) {
        int int_random = 0;

        for (;;) {

            System.out.println("Preferred Card: " + this.preferredCard);
            int_random = new Random().nextInt(3);
            System.out.println("random card: " + this.hand.get(int_random).getCardValue());
            if ( this.hand.get(int_random).getCardValue() !=this.preferredCard) {
                break;
            }
        }


        deck.putBottom(this.hand.get(int_random));
        writeToOutputFile("Player " + this.playerNum + " discards card " + this.hand.get(int_random).getCardValue() + " to deck" + deck.getDeckNum());
        System.out.println("Player " + this.playerNum + " discards card " + this.hand.get(int_random).getCardValue() + " to deck " + deck.getDeckNum());
        this.hand.remove(int_random);

    }

    private boolean checkWin() {
        return this.hand.get(0).getCardValue() == this.hand.get(1).getCardValue() &&
                this.hand.get(0).getCardValue() == this.hand.get(2).getCardValue() &&
                this.hand.get(0).getCardValue() == this.hand.get(3).getCardValue();

    }

    // has a turn - picks up and discards cards
    public void takeTurn(ArrayList<CardDeck> decks) {
        // discard random card to deck +1 - need to loop back on self
        // pick up new card from deck same num
        // check if winning
        // [deck1, deck2, deck3, deck4]
        System.out.println("Player " + this.playerNum + " is taking a turn");
        if (playerNum == decks.size()) {
            discardCard(decks.get(0));
        } else {
            System.out.println("Take turn else running");
            discardCard(decks.get(this.playerNum));
        }

        // draws a card from the correct deck
        drawCard(decks.get(this.playerNum - 1));
        checkWin();

    }

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
