import com.sun.source.tree.AssertTree;
import org.junit.Assert;

import java.io.File;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CardDeckTest {
    private CardDeck deck;

    @Before
    public void setUp() throws Exception {
        try {
            // makes a new deck
            this.deck = new CardDeck(1);
            // gives the deck card values
            this.deck.getCards().add(new Card(2));
            this.deck.getCards().add(new Card(3));
            this.deck.getCards().add(new Card(5));
            this.deck.getCards().add(new Card(7));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            // removes the deck
            this.deck = null;
            assertNull(this.deck);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test
    public void getDeckSize() {
        assertEquals(this.deck.getDeckSize(), 4);
    }

    @Test
    public void getDeckSizeFalse() {
        assertNotEquals(this.deck.getDeckSize() + 1, 4);
    }

    @Test
    public void putBottom() {
        this.deck.putBottom(new Card(100));

        // checks the last card is the expected value
        assertEquals(this.deck.getCards().get(this.deck.getDeckSize() - 1).getCardValue(), 100);

        // checks if the deck has been updated by looking at the deck size
        assertEquals(this.deck.getDeckSize(), 5);
    }

    @Test
    public void getTopCard() {
        // check top card is correct value
        assertEquals(this.deck.getTopCard().getCardValue(), 2);
        assertEquals(this.deck.getDeckSize(), 3);
    }

    @Test
    public void getTopCardMultiple(){
        // checks cards that have been added are in correct order

        ArrayList<Card> expectedCardValues = new ArrayList<>();
        expectedCardValues.add(new Card(2));
        expectedCardValues.add(new Card(3));
        expectedCardValues.add(new Card(5));
        expectedCardValues.add(new Card(7));
        for (int i = 0; i < this.deck.getDeckSize(); i++) {
            assertEquals(this.deck.getCards().get(i).getCardValue(), expectedCardValues.get(i).getCardValue());
        }
    }

    @Test
    public void getDeckNum() {
        assertEquals(this.deck.getDeckNum(), 1);
    }

    @Test
    public void getDeckNumFalse() {
        assertNotEquals(this.deck.getDeckNum() + 1, 1);
    }

    @Test
    public void makeFile(){
        this.deck.makeFile();
        File f = new File("deck1_output.txt");
        // check if exists
        assertTrue(f.exists());
        f.delete();

    }
    @Test
    public void writeContentsToFile() {
        this.deck.writeContentsToFile();
        String writtenContents = "";
        String expectedWrittenContents = "Deck1 contains 2 3 5 7";

        File file = new File("deck1_output.txt");
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()); // gets what is written in file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        writtenContents = fileContents.toString();
        assertEquals(writtenContents, expectedWrittenContents); // checks contents of file is same as expected output
    }
}