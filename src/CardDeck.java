import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CardDeck{

    private ArrayList<Card> cards;
    private final int deckNum;

    CardDeck(int deckNum) {
        this.deckNum = deckNum;
        this.cards = new ArrayList<>();
    }

    protected int getDeckSize() {
        return cards.size();
    }

    public void putBottom(Card card) {
        cards.add(card);
    }

    // returns the first card in the arraylist
    public Card getTopCard() {
        Card elem = cards.get(0);
        cards.remove(elem);
        return elem;
    }

    // adds an item to the arraylist
    private Card getBottomCard() {
        Card elem = cards.get(cards.size() - 1);
        cards.remove(elem);
        return elem;
    }

    public int getDeckNum() {
        return deckNum;
    }

    // makes a file to write the final deck output too
    private void makeFile() {
        try {
            File deckFile = new File("deck" + this.deckNum + "_output.txt");
            try {
                // tries to delete file if already exits
                deckFile.delete();
            } catch (Exception ignored) {} // if cant delete there is no issue as file does not exist

            if (deckFile.createNewFile()) {
                System.out.println("File created: " + deckFile.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred in making a deck file");
            e.printStackTrace();
        }
    }

    // writes the contents of the deck to file
    public void writeContentsToFile() {
        String deckContents = "";
        makeFile();
        try {
            BufferedWriter deckWriter = new BufferedWriter(new FileWriter("deck" + this.deckNum + "_output.txt", true));
            for (Card c : cards) {
                deckContents = deckContents + " " + c.getCardValue();
            }
            deckWriter.write("Deck" + this.deckNum + " contains" + deckContents);
            deckWriter.newLine();
            deckWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in writing to a deck file");
            e.printStackTrace();

        }
    }

    public ArrayList<Card> getCards(){
        return cards;
    }
}
