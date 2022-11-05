public class MinHeap {
    
    private int num_items;
    private int capacity;
    private int[] heap;
    
    public MinHeap() {
    
        this.capacity = 50;
        this.num_items = 0;
        this.heap = new int[capacity + 1];
    
    }
    
    public MinHeap(int capacity) {
    
        this.capacity = capacity;
        this.num_items = 0;
        this.heap = new int[capacity + 1];
    
    }

    public boolean isEmpty() {
        
        return this.num_items == 0;

    }

    public int capacity() {

        return this.heap.length - 1;

    }

    public boolean isFull() {

        return this.num_items == this.capacity();

    }

    public int size() {

        return this.num_items;

    }

    public int getParentsIndex(int i) {

        return i / 2;

    }

    public int getLeftChildIndex(int i) {

        return 2 * i;

    }

    public int getRightChildIndex(int i) {

        return 2 * i + 1;

    }

    public int parent(int i) {

        return this.heap[this.getParentsIndex(i)];

    }

    public int leftChild(int i) {

        return this.heap[this.getLeftChildIndex(i)];

    }

    public int rightChild(int i) {

        return this.heap[this.getRightChildIndex(i)];

    }

    public int minChild(int i) {

        if (this.getRightChildIndex(i) > this.capacity()) {
            return this.getLeftChildIndex(i);
        }
        else {
            if (this.leftChild(i) < this.rightChild(i)) {
                return this.getLeftChildIndex(i);
            }
            else {
                return this.getRightChildIndex(i);
            }
        }

    }

    public void swap(int index1, int index2) {

        int temp = this.heap[index1];
        this.heap[index1] = this.heap[index2];
        this.heap[index2] = temp;

    }
    
    public void enqueue(int item) {
    
        if (this.num_items + 1 > this.capacity) {
            throw new IndexOutOfBoundsException("Index Error");
        }
        num_items++;
        this.heap[this.num_items] = item;
        this.percUp(this.num_items);
    
    }

    public int peek() {

        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("Index Error");
        }
        return this.heap[1];

    }

    public int dequeue() {

        if (this.isEmpty()) {

            throw new IndexOutOfBoundsException("Index Error");

        }
        int data = this.heap[1];
        this.heap[1] = this.heap[this.num_items];
        this.num_items--;
        this.percDown(1);
        return data;

    }

    public int[] contents() {

        int[] contents = new int [this.capacity];
        int con_index = 0;
        if (this.isEmpty()) {
            return contents;
        }
        for (int i = 1; i < this.num_items + 1; i++) {
            contents[con_index] = this.heap[i];
            con_index++;
        }
        return contents;

    }

    public void buildHeap(Integer[] alist) {

        this.heap = new int[alist.length];
        this.num_items = alist.length;
        int i = (alist.length - 1) / 2;
        while (i > 0) {
            this.percDown(i);
            i--;
        }

    }

    public void percDown(int i) {

        while (this.getLeftChildIndex(i) <= this.size()) {
            int smallest = this.minChild(i);
            if (this.heap[i] > this.heap[smallest]) {
                this.swap(i, smallest);
            }
            i = smallest;
        }

    }
    
    public void percUp(int i) {
        while (this.getParentsIndex(i) > 0) {
            if (this.parent(i) > this.heap[i]) {
                this.swap(this.getParentsIndex(i), i);
            }
            i = i / 2;
        }
    }

}