import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author sayanray
 * @version Oct 5, 2018
 */
public class SparseMatrixTest extends TestCase
{


    public void setUp() {

        }


    public void test1()
    {
        SparseMatrix spm = new SparseMatrix();
        spm.addMovie("KNPH");
        assertEquals(spm.getRowCounter(), 1);
        spm.addMovie("abcd");
        SparseMatrixNode a = spm.searchMovie("KNPH");
        assertEquals(a.getNext().getData().getMovie(), "abcd");

    }

    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

}
