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
        try{

            numberOfPlayers = new Random().nextInt(0, 50);
            cards = new ArrayList<>();

            BufferedWriter writer;

            // Create a fle with random positive cards inside
            correctPackFile = "correctPack.txt";
            File correctFile = new File(correctPackFile);
            try{
                correctFile.createNewFile();
            } catch (IOException e){
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
        } catch (Exception e){
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
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    public void getCards() {
        correctPack.importPack();
        for (int i = 0 ; i < cards.size(); i++) {
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
        StringBuilder fileContents = new StringBuilder((int)file.length());

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String data = scanner.nextLine();
                expectedCards.add(new Card(Integer.parseInt(data)));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0; i < actualCards.size(); i++) {
            try {
                assertEquals(actualCards.get(i).getCardValue(), expectedCards.get(i).getCardValue());
            } catch (Exception e){
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
        // check we

        assertTrue(correctPack.verified());


        Pack negativePack;
        Pack wrongLinePack;

        File negativeFile = new File("negativeFile.txt");
        File wrongLineFile = new File("wrongLineFile.txt");
        try {
            negativeFile.createNewFile();
            wrongLineFile.createNewFile();
        } catch (IOException e) {
            throw new IOException(e);
        }

        BufferedWriter negWriter = new BufferedWriter( new FileWriter(negativeFile.getName(), true));
        BufferedWriter wrongWriter = new BufferedWriter( new FileWriter(wrongLineFile.getName(), true));

        negWriter.write("-1");
        for (int i = 0; i < (numberOfPlayers * 8) - 1; i++){
            negWriter.write(new Random().nextInt(0,50));
            negWriter.newLine();
        }
        wrongWriter.write("5");

        negWriter.close();
        wrongWriter.close();

        negativePack = new Pack(negativeFile.getName(), numberOfPlayers);
        wrongLinePack = new Pack(wrongLineFile.getName(), numberOfPlayers);

        assertFalse(negativePack.verified());
        assertFalse(wrongLinePack.verified());
    }
}