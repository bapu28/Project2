import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * This method tests the Parser class.
 *
 * @author sayanray
 * @version Sep 2, 2018
 */
public class ParserTest {

    /**
     * This method tests the Parser class.
     * 
     * @throws FileNotFoundException
     */
    @Test
    public void test() throws FileNotFoundException {
        Parser testParser = new Parser(20, "P2SampleInput.txt");
        assertEquals(3, testParser.getMovieTable().getCounter());
    }
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

}
