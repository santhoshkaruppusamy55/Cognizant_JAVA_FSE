import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {

        assertEquals("Sum should be 5", 5, 2 + 3);

        assertTrue("5 is greater than 3", 5 > 3);
        assertFalse("5 is not less than 3", 5 < 3);

        String str = null;
        assertNull("str should be null", str);

        String name = "NK";
        assertNotNull("name should not be null", name);

        String lang = "Java";
        String anotherLang = lang;
        assertSame("Both references should point to the same object", lang, anotherLang);

        String first = new String("test");
        String second = new String("test");
        assertNotSame("These are different objects in memory", first, second);

        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals("Arrays should be equal", expectedArray, actualArray);
    }
}
