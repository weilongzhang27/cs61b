public class ArrayDeque<T> implements Deque<T> {
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

    @Override
    public void printDeque() {
        int currentIndex = nextIndex(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex]);
            System.out.print(" ");
            currentIndex = nextIndex(currentIndex);
        }
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = prevIndex(nextFirst);
        size += 1;
        expand();
    }

    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = nextIndex(nextLast);
        size += 1;
        expand();
    }

    @Override
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

    @Override
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
}