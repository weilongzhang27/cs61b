public class LinkedListDeque<T>{
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

    public boolean isEmpty(){
        if (size==0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p=sentinel;
        while (p.next!=sentinel){
            System.out.print(p.next.item);
            System.out.print(" ");
            p=p.next;
        }
        System.out.println();
    }

    public void addFirst(T item){
        size+=1;
        sentinel.next=new Node(sentinel,item,sentinel.next);
        sentinel.next.next.prev=sentinel.next;
    }

    public void addLast(T item){
        size+=1;
        sentinel.prev=new Node(sentinel.prev,item,sentinel);
        sentinel.prev.prev.next=sentinel.prev;
    }

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

    public static void main(String[] args){
        LinkedListDeque<Integer> temp= new LinkedListDeque<>();
        temp.addFirst(2);
        temp.addLast(3);
        temp.addFirst(1);
        temp.addLast(4);
        temp.addFirst(0);
        temp.addLast(5);
        temp.printDeque();
        System.out.println(temp.removeFirst());
        temp.printDeque();
        System.out.println(temp.removeLast());
        temp.printDeque();
        System.out.println(temp.removeFirst());
        temp.printDeque();
        System.out.println(temp.removeLast());
        temp.printDeque();
        System.out.println(temp.removeFirst());
        temp.printDeque();
        System.out.println(temp.removeFirst());
        temp.printDeque();
        temp.addFirst(2);
        temp.addLast(3);
        temp.addFirst(1);
        temp.printDeque();
    }

}