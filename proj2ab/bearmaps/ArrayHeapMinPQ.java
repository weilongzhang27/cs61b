package bearmaps;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private Node[] items;
    private Map<T,Integer> map;
    private int initialCapacity = 8;
    private int capacity;
    private int resizeFactor=2;
    private int size;

    private class Node{
        private T item;
        private double priority;

        Node(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        this.items = (Node[])new ArrayHeapMinPQ<?>.Node[initialCapacity+1];
        this.map=new HashMap<>();
        this.size=0;
        this.capacity=initialCapacity;
    }

    public ArrayHeapMinPQ(int capacity) {
        this.items = (Node[])new ArrayHeapMinPQ<?>.Node[capacity+1];
        this.map=new HashMap<>();
        this.size=0;
        this.capacity=capacity;
    }

    private int parent(int k) {
        return k / 2;
    }

    private int leftChild(int k) {
        return 2 * k;
    }

    private int rightChild(int k) {
        return 2 * k + 1;
    }

    private void swimUp(T item, int k){
        while(parent(k)!=0){
            int parentInt=parent(k);
            if(items[k].getPriority()>=items[parentInt].getPriority()) {
                break;
            }
            Node tempN=items[parentInt];
            map.put(item,parentInt);
            map.put((T) tempN.getItem(),k);
            items[parentInt]=items[k];
            items[k]=tempN;
            k=parentInt;
        }
    }

    private void swimDown(T item, int k){
        while(leftChild(k)<=size){
            int childInt;
            if(rightChild(k)<=size&&items[leftChild(k)].getPriority()>items[rightChild(k)].getPriority()){
                childInt=rightChild(k);
            }else{
                childInt=leftChild(k);
            }
            if(items[k].getPriority()<=items[childInt].getPriority()) {
                break;
            }
            Node tempN=items[childInt];
            map.put(item,childInt);
            map.put((T) tempN.getItem(),k);
            items[childInt]=items[k];
            items[k]=tempN;
            k=childInt;

        }
    }

    private void resize(int cap){
        Node[] temp = (Node[])new ArrayHeapMinPQ<?>.Node[cap+1];
        if (cap < capacity) {
            System.arraycopy(items, 1, temp, 1, cap);
        } else {
            System.arraycopy(items, 1, temp, 1, capacity);
        }
        items=temp;
        capacity=cap;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public void add(T item, double priority) {
        if (map.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        size+=1;
        map.put(item,size);
        items[size]=new Node(item,priority);
        swimUp(item,size);
        if(size==capacity) {
            this.resize(this.capacity * this.resizeFactor);
        }

    }


    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return (T) items[1].getItem();
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        T tempItem= (T) items[1].getItem();

        items[1]=items[size];
        map.put(items[1].getItem(),1);
        size-=1;
        this.swimDown(items[1].getItem(),1);
        if (size < 0.25 * capacity) {
            resize((int) (0.5 * capacity));
        }
        map.remove(tempItem);
        return tempItem;
    }

    @Override
    public void changePriority(T item, double priority) {
        int index = map.get(item);
        double oldPriority = items[index].getPriority();
        items[index].setPriority(priority);
        if (oldPriority > priority){
            swimUp(item, index);
        }else{
            swimDown(item, index);
        }

    }

    public int getCapacity() {
        return capacity;
    }



}
