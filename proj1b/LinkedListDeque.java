public class LinkedListDeque<T> implements Deque<T> {
    private class Node{
        Node prev;
        T item;
        Node next;
        Node(Node n_prev,T i, Node n_next){
            item=i;
            prev=n_prev;
            next=n_next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
        size=0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node p=sentinel;
        while (p.next!=sentinel){
            System.out.print(p.next.item);
            System.out.print(" ");
            p=p.next;
        }
        System.out.println();
    }

    @Override
    public void addFirst(T item){
        size+=1;
        sentinel.next=new Node(sentinel,item,sentinel.next);
        sentinel.next.next.prev=sentinel.next;
    }

    @Override
    public void addLast(T item){
        size+=1;
        sentinel.prev=new Node(sentinel.prev,item,sentinel);
        sentinel.prev.prev.next=sentinel.prev;
    }

    @Override
    public T removeFirst(){
        if(size==0){
            return null;
        }else{
            size-=1;
            T p=sentinel.next.item;
            sentinel.next=sentinel.next.next;
            sentinel.next.prev=sentinel;
            return p;
        }
    }

    @Override
    public T removeLast(){
        if(size==0){
            return null;
        }else{
            size-=1;
            T p=sentinel.prev.item;
            sentinel.prev=sentinel.prev.prev;
            sentinel.prev.next=sentinel;
            return p;
        }
    }

    @Override
    public T get(int index){
        if(index>size){
            return null;
        }else{
            Node p=sentinel;
            for(int i=1;i<=index;i+=1){
                p=p.next;
            }
            return p.item;
        }
    }

    private T getHelper(Node n,int index){
        if(index==1){
            return n.item;
        }else{
            return getHelper(n.next,index-1);
        }
    }

    public T getRecursive(int index){
        if(index>size){
            return null;
        }else{
            Node p=sentinel.next;
            return getHelper(p,index);
        }
    }

    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new Node(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
        size=0;
        Node p=other.sentinel;
        for(int i=1;i<=other.size();i+=1){
            addLast(p.next.item);
            p=p.next;
        }
    }
}