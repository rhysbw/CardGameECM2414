import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    private Card card;

    @Before
    public void setUp() throws Exception {
        // makes a new card of value 3
        try {
            this.card = new Card(3);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            this.card = null;
            assertNull(this.card);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test
    public void getCardValue() {
        assertEquals(this.card.getCardValue(), 3);
    }

    @Test
    public void getCardValueFalse(){assertNotEquals(this.card.getCardValue(), 4);}
}