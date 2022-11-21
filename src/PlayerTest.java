import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class PlayerTest {

    private ArrayList<CardDeck> decks;
    private Player player;

    private ArrayList<Card> cards;

    @Before
    public void setUp() throws Exception {
        // make 2 decks
        // fill decks with cards
        // make a player
        decks = new ArrayList<>();
        try{
            this.decks.add(new CardDeck(1));
            this.decks.add(new CardDeck(2));

            cards = new ArrayList<>();
            cards.add(new Card(1));
            cards.add(new Card(2));
            cards.add(new Card(3));
            cards.add(new Card(4));
            for (CardDeck d: this.decks){
                for(Card c: cards){
                    d.putBottom(c);
                }
            }
            this.player = new Player(1);
        } catch (Exception e){
            throw new Exception(e);
        }


    }

    @After
    public void tearDown() throws Exception {
        try{
            this.player = null;
            assertNull(this.player);
            this.decks = null;
            assertNull(this.decks);
        }catch (Exception e){
            throw new Exception(e);
        }


    }

    @Test
    public void addCardToHand() {
        this.player.addCardToHand(cards.get(0));
        assertEquals(this.player.getHand().get(0), cards.get(0));
    }


    @Test
    public void checkWin() {
        // gives player winning hand
        this.player.addCardToHand(cards.get(0));
        this.player.addCardToHand(cards.get(0));
        this.player.addCardToHand(cards.get(0));
        this.player.addCardToHand(cards.get(0));

        assertTrue(this.player.checkWin());
    }

    @Test
    public void takeTurn() {
        // gives player a hand
        this.player.addCardToHand(cards.get(0));
        this.player.addCardToHand(cards.get(1));
        this.player.addCardToHand(cards.get(0));
        this.player.addCardToHand(cards.get(2));

        // takes a turn
        this.player.takeTurn(this.decks);

        // check deck 2 has 5 cards
        assertEquals(this.decks.get(1).getCards().size(), 5);

        // check deck 1 has 3 cards
        assertEquals(this.decks.get(0).getCards().size(), 3);

        // check it doesn't discard the preferred card
        assertNotEquals(this.decks.get(1).getCards().get(4).getCardValue(),1);

    }

    @Test
    public void notified() throws IOException {
        // call notified with as this player has won
        this.player.notified(this.player.getPlayerNum());

        // check last line of file reads "Player 1 has won"
        BufferedReader input = new BufferedReader(new FileReader("player1_output.txt"));
        String last = null;
        String line = null;

        while ((line = input.readLine()) != null) {
            last = line;
        }
        assertEquals(last, "Player 1 final hand:");


    }

    @Test
    public void getPreferredCard() {
        assertEquals(this.player.getPreferredCard(), 1);
    }

    @Test
    public void getPlayerNum() {
        assertEquals(this.player.getPlayerNum(), 1);
    }

    @Test
    public void getHand() {
        assertEquals(this.player.getHand(), new ArrayList<Card>());
    }
}