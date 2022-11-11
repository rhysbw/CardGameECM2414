import java.util.ArrayList;

public class CardDeck{

    private ArrayList<Card> cards;
    private final int deckNum;
    private final int limit = 4;

    CardDeck(int deckNum) {
        this.deckNum = deckNum;
        this.cards = new ArrayList<>();
    }

    private Card getTopCard() {
        return cards.get(0);
    }

    private Card getBottomCard() {
        return cards.get(cards.size() - 1);
    }

    public int getDeckNum() {
        return deckNum;
    }
}
