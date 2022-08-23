import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByN = new OffByN(3);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertFalse("should be false",palindrome.isPalindrome("cae", offByN));
        assertFalse("should be false",palindrome.isPalindrome("bacc",offByN));
        assertTrue("should be true",palindrome.isPalindrome("DorG",offByN));
        assertTrue("should be true",palindrome.isPalindrome("DfqargC",offByN));
    }
}