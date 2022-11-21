import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PackTest {

    private Pack correctPack;
    private String correctPackFile;
    private int numberOfPlayers;
    private ArrayList<Card> cards;

    @Before
    public void setUp() throws Exception {
        try {

            numberOfPlayers = new Random().nextInt(0, 50);
            cards = new ArrayList<>();

            BufferedWriter writer;

            // Create a fle with random positive cards inside
            correctPackFile = "correctPack.txt";
            File correctFile = new File(correctPackFile);
            try {
                correctFile.createNewFile();
            } catch (IOException e) {
                throw new IOException(e);
            }

            for (int i = 0; i < numberOfPlayers * 8; i++){
                cards.add(new Card(new Random().nextInt(0,30)));
            }

            writer = new BufferedWriter(new FileWriter(this.correctPackFile, true));
            for (Card c : cards){
                writer.write(c.getCardValue() + "");
                writer.newLine();
            }
            writer.close();
            this.correctPack = new Pack(this.correctPackFile, numberOfPlayers);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        try {

            File f = new File(correctPackFile);
            f.delete();

            correctPack = null;
            correctPackFile = null;
            assertNull(correctPackFile);
            assertNull(correctPack);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test
    public void getCards() {
        correctPack.importPack();
        // checks that the getCard function returns the cards in the correct order, which is tested by checking that they match the cards in the arraylist
        for (int i = 0; i < cards.size(); i++) {
            assertEquals(this.correctPack.getCards().get(i).getCardValue(), cards.get(i).getCardValue());
        }
    }

    @Test
    public void importPack() {
        this.correctPack.importPack();
        ArrayList<Card> actualCards;
        actualCards = this.correctPack.getCards();

        ArrayList<Card> expectedCards = new ArrayList<>();
        File file = new File(this.correctPackFile);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                expectedCards.add(new Card(Integer.parseInt(data)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < actualCards.size(); i++) {
            try {
                assertEquals(actualCards.get(i).getCardValue(), expectedCards.get(i).getCardValue()); // compares that the card values are the same in both
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void getNumberOfPlayers() {
        assertEquals(correctPack.getNumberOfPlayers(), numberOfPlayers);
    }

    @Test
    public void getFilename() {
        assertEquals(correctPack.getFilename(), "correctPack.txt");
    }

    @Test
    public void verified() throws IOException {
        // check that a valid pack file is verified as true
        assertTrue(correctPack.verified());
    }

    @Test
    public void negativeVerified() throws IOException {
        Pack negativePack;
        File negativeFile = new File("negativeFile.txt");
        try {
            negativeFile.createNewFile();
        } catch (IOException e) {
            throw new IOException(e);
        }
        BufferedWriter negWriter = new BufferedWriter(new FileWriter(negativeFile.getName(), true));
        negWriter.write("-1"); // writes a non-positive integer to the file
        for (int i = 0; i < (numberOfPlayers * 8) - 1; i++) {
            negWriter.write(new Random().nextInt(0, 50));
            negWriter.newLine();
        }
        negWriter.close();
        negativePack = new Pack(negativeFile.getName(), numberOfPlayers);
        assertFalse(negativePack.verified()); // checks that the pack file is seen as invalid

    }

    @Test
    public void wrongNumberVerified() throws IOException {
        Pack wrongLinePack;

        File wrongLineFile = new File("wrongLineFile.txt");
        try {
            wrongLineFile.createNewFile();
        } catch (IOException e) {
            throw new IOException(e);
        }
        // makes a packfile of the incorrect length
        BufferedWriter wrongWriter = new BufferedWriter(new FileWriter(wrongLineFile.getName(), true));
        wrongWriter.write("5");
        wrongWriter.close();
        wrongLinePack = new Pack(wrongLineFile.getName(), numberOfPlayers);
        assertFalse(wrongLinePack.verified()); // checks that the pack file is seen as invalid


    }


}