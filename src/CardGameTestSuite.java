import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Test suite that runs all the tests for the program

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                PlayerTest.class, CardTest.class, CardDeckTest.class, PackTest.class, CardGameTest.class
        })
public class CardGameTestSuite {
}
