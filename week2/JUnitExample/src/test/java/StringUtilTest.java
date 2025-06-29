import org.example.StringUtil;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



public class StringUtilTest {

    private StringUtil stringUtil;

    @Before
    public void setUp() {
        stringUtil = new StringUtil();
    }

    @After
    public void tearDown() {
        stringUtil = null;
    }

    @Test
    public void testReverse() {
        String result = stringUtil.reverse("hello");
        assertEquals("olleh", result);
    }

    @Test
    public void testReverseEmptyString() {
        String result = stringUtil.reverse("");
        assertEquals("", result);
    }

    @Test
    public void testReverseNull() {
        String result = stringUtil.reverse(null);
        assertNull(result);
    }

    @Test
    public void testIsPalindromeTrue() {
        boolean result = stringUtil.isPalindrome("madam");
        assertTrue(result);
    }

    @Test
    public void testIsPalindromeFalse() {
        boolean result = stringUtil.isPalindrome("hello");
        assertFalse(result);
    }
}
