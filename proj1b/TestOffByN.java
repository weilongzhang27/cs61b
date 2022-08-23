import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testEqualChars(){
        boolean res1=offByN.equalChars('a','f');
        assertTrue("should be true",res1);
        boolean res2=offByN.equalChars('f','h');
        assertFalse("should be false",res2);
    }

}
