public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int initialCapacity = 8;
    private int anchorCapacity = 16;
    private int resizeFactor = 2;
    private double targetRatio = 0.25;
    private int nextFirst;
    private int nextLast;

    private int prevIndex(int currentIndex) {
        if (currentIndex == 0) {
            return items.length - 1;
        } else {
            return currentIndex - 1;
        }
    }

    private int nextIndex(int currentIndex) {
        if (currentIndex == items.length - 1) {
            return 0;
        } else {
            return currentIndex + 1;
        }
    }

    public ArrayDeque() {
        items = (T[]) new Object[initialCapacity];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    private void resize(int capacity) {
        T[] p = (T[]) new Object[capacity];
        int firstIndex = nextIndex(nextFirst);
        int lastIndex = prevIndex(nextLast);
        if (firstIndex <= lastIndex) {
            System.arraycopy(items, firstIndex, p, 0, size);
            items = p;
        } else {
            int copyFirst = items.length - firstIndex;
            System.arraycopy(items, firstIndex, p, 0, copyFirst);
            System.arraycopy(items, 0, p, copyFirst, size - copyFirst);
            items = p;
        }
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void expand() {
        if (size == items.length) {
            resize(items.length * resizeFactor);
        }
    }

    private void contract() {
        double currentRatio = (double) size / items.length;
        if (items.length > anchorCapacity & currentRatio < targetRatio) {
            resize(items.length / resizeFactor);
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void printDeque() {
        int currentIndex = nextIndex(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex]);
            System.out.print(" ");
            currentIndex = nextIndex(currentIndex);
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        int firstIndex = nextIndex(nextFirst);
        int targetIndex = firstIndex + index - 1;
        if (targetIndex <= items.length - 1) {
            return items[targetIndex];
        } else {
            return items[targetIndex - items.length];
        }
    }

    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = prevIndex(nextFirst);
        size += 1;
        expand();
    }

    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = nextIndex(nextLast);
        size += 1;
        expand();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int firstIndex = nextIndex(nextFirst);
        T res = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size -= 1;
        contract();
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int lastIndex = prevIndex(nextLast);
        T res = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;
        contract();
        return res;
    }

    public ArrayDeque(ArrayDeque<T> other){
        items = (T[]) new Object[other.size()+1];
        size = other.size();
        for(int i=0;i<other.size();i+=1){
            items[i]=other.get(i);
        }
        nextFirst = items.length - 1;
        nextLast = items.length - 1;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
        temp.addFirst(2);
        temp.addFirst(1);
        temp.addFirst(0);
        temp.addLast(3);
        temp.addLast(4);
        temp.addLast(5);
        temp.addLast(6);
        temp.addFirst(7);
        temp.addLast(11);
        temp.addFirst(12);
        temp.printDeque();
        System.out.println(temp.size());
        System.out.println(temp.get(1));
        System.out.println(temp.get(10));
        System.out.println(temp.removeFirst());
        System.out.println(temp.removeLast());
        System.out.println(temp.removeFirst());
        System.out.println(temp.removeLast());
        System.out.println(temp.removeFirst());
        System.out.println(temp.removeLast());
        System.out.println(temp.removeFirst());
        System.out.println(temp.removeLast());
        System.out.println(temp.removeFirst());
        System.out.println(temp.removeFirst());
        temp.printDeque();
        temp.addFirst(2);
        temp.addFirst(1);
        temp.addLast(0);
        temp.printDeque();
    }
}