import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CardGameTest {
    private ArrayList<Player> players;
    private ArrayList<CardDeck> decks;

    @Before
    public void setUp() throws Exception {
        this.players = new ArrayList<>();
        this.decks = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        this.players = null;
        this.decks = null;
        assertNull(this.players);
        assertNull(this.decks);
    }

    @Test
    public void checkIfPlayerHasWon() {
        // make 2 player
        // make 2 decks


        players.add(new Player(1));
        players.add(new Player(2));

        // Adding winning cards to player 1 hand
        players.get(0).addCardToHand(new Card(1));
        players.get(0).addCardToHand(new Card(1));
        players.get(0).addCardToHand(new Card(1));
        players.get(0).addCardToHand(new Card(1));

        // Adding losing cards to player 2
        players.get(1).addCardToHand(new Card(3));
        players.get(1).addCardToHand(new Card(1));
        players.get(1).addCardToHand(new Card(1));
        players.get(1).addCardToHand(new Card(1));

        assertTrue(CardGame.checkIfPlayerHasWon(players, decks));
    }

    @Test
    public void deal() {

         // All expected Values of Decks and Players

        ArrayList<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player(1));
        expectedPlayers.add(new Player(2));
        expectedPlayers.add(new Player(3));

        ArrayList<CardDeck> expectedDecks = new ArrayList<>();
        expectedDecks.add(new CardDeck(1));
        expectedDecks.add(new CardDeck(2));
        expectedDecks.add(new CardDeck(3));

        // Adding expected cards to players
        expectedPlayers.get(0).addCardToHand(new Card(1));
        expectedPlayers.get(0).addCardToHand(new Card(1));
        expectedPlayers.get(0).addCardToHand(new Card(1));
        expectedPlayers.get(0).addCardToHand(new Card(5));

        expectedPlayers.get(1).addCardToHand(new Card(3));
        expectedPlayers.get(1).addCardToHand(new Card(2));
        expectedPlayers.get(1).addCardToHand(new Card(1));
        expectedPlayers.get(1).addCardToHand(new Card(3));

        expectedPlayers.get(2).addCardToHand(new Card(2));
        expectedPlayers.get(2).addCardToHand(new Card(3));
        expectedPlayers.get(2).addCardToHand(new Card(9));
        expectedPlayers.get(2).addCardToHand(new Card(5));

        // Adding the expected cards to the decks
        expectedDecks.get(0).putBottom(new Card(7));
        expectedDecks.get(0).putBottom(new Card(4));
        expectedDecks.get(0).putBottom(new Card(3));
        expectedDecks.get(0).putBottom(new Card(3));

        expectedDecks.get(1).putBottom(new Card(9));
        expectedDecks.get(1).putBottom(new Card(6));
        expectedDecks.get(1).putBottom(new Card(8));
        expectedDecks.get(1).putBottom(new Card(7));

        expectedDecks.get(2).putBottom(new Card(6));
        expectedDecks.get(2).putBottom(new Card(9));
        expectedDecks.get(2).putBottom(new Card(5));
        expectedDecks.get(2).putBottom(new Card(8));

        //making a pack with a pre-determined testPack file with 3 players
        Pack cardPack = new Pack("testPack.txt", 3);
        this.players.add(new Player(1));
        this.players.add(new Player(2));
        this.players.add(new Player(3));

        this.decks.add(new CardDeck(1));
        this.decks.add(new CardDeck(2));
        this.decks.add(new CardDeck(3));

        CardGame.deal(cardPack, players, decks, 3);

        for (int i = 0; i < players.size(); i++){
            for (int j = 0; j < players.get(i).getHand().size(); j++){
                assertEquals(players.get(i).getHand().get(j).getCardValue(),
                            expectedPlayers.get(i).getHand().get(j).getCardValue());
            }
        }
        for (int i = 0; i < decks.size(); i++){
            for (int j = 0; j < decks.get(i).getCards().size(); j++){
                assertEquals(decks.get(i).getCards().get(j).getCardValue(),
                        expectedDecks.get(i).getCards().get(j).getCardValue());
            }
        }

    }
}