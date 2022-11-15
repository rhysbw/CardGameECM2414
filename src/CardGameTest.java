import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CardGameTest {

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
    public void main() {
    }

    @Test
    public void checkIfPlayerHasWon() {
    }
}