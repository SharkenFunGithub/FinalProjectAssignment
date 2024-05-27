import org.example.Util;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testToTitleCase() {
        assertEquals("Hello World", Util.toTitleCase("hello world"));
        assertEquals("Programming Is Fun", Util.toTitleCase("programming is fun"));
        assertEquals("I Love Java", Util.toTitleCase("i love java"));
    }
}