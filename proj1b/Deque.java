public interface Deque<T> {

    default public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void printDeque();

    public int size();

    public T get(int index);

    public void addFirst(T x);

    public void addLast(T x);

    public T removeFirst();

    public T removeLast();
}
