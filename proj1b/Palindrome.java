public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<Character>();
        char[] temp = word.toCharArray();
        for (int i = 0; i < word.length(); i += 1) {
            res.addLast(temp[i]);
        }
        return res;
    }

    public boolean isPalindrome(String word){
        Deque tempDeque=wordToDeque(word);
        return recHelper(tempDeque);
    }

    private boolean recHelper(Deque<Character> word){
        if(word.size()==0 || word.size()==1){
            return true;
        }
        if(word.removeFirst()!=word.removeLast()){
            return false;
        }else{
            return recHelper(word);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque tempDeque=wordToDeque(word);
        return recHelper(tempDeque,cc);
    }

    private boolean recHelper(Deque<Character> word, CharacterComparator cc){
        if(word.size()==0 || word.size()==1){
            return true;
        }
        if(cc.equalChars(word.removeFirst(), word.removeLast())){
            return recHelper(word,cc);
        }else{
            return false;
        }
    }
}