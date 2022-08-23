import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        boolean res1=offByOne.equalChars('c','b');
        assertTrue("should be true",res1);
        boolean res2=offByOne.equalChars('a','c');
        assertFalse("should be false",res2);
    }
}