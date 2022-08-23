

import org.junit.Test;
import static org.junit.Assert.*;

public class TestP{
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByN = new OffByN(3);

    @Test
    public void testIsPalindrome(){
        assertFalse("should be false",palindrome.isPalindrome("cae", offByN));
        assertFalse("should be false",palindrome.isPalindrome("bacc",offByN));
        assertTrue("should be true",palindrome.isPalindrome("DorG",offByN));
        assertTrue("should be true",palindrome.isPalindrome("DfuariA",offByN));
    }
}
