import org.example.Address;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressTest {

    @Test
    public void testIsPostalCodeValid_ValidCodes() {
        assertTrue(Address.isPostalCodeValid("A1B2C3"));
        assertTrue(Address.isPostalCodeValid("A1B 2C3"));
        assertTrue(Address.isPostalCodeValid("a1b2c3"));
        assertTrue(Address.isPostalCodeValid("a1b 2c3"));
    }

    @Test
    public void testIsPostalCodeValid_InvalidCodes() {
        assertFalse(Address.isPostalCodeValid(""));
        assertFalse(Address.isPostalCodeValid(null));
        assertFalse(Address.isPostalCodeValid("123456"));
        assertFalse(Address.isPostalCodeValid("ABC123"));
        assertFalse(Address.isPostalCodeValid("AB C123"));
        assertFalse(Address.isPostalCodeValid("A1B2C"));
    }
}