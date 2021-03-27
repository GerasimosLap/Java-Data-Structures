import java.io.*;
import java.util.*; 
public class PQ {
	

	public Song[]heap;

	public int size;

    public int capacity;

	
	private Comparator<Song> cmp;

	public PQ(int capacity) {
        this(capacity, new DefaultComparator());
        this.size = 0;
    }

    public PQ(int capacity, Comparator cmp) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.heap = new Song[capacity+1];
        this.size = 0;
        this.cmp = cmp;
    }


   

    public void insert(Song data) {
        
        if (data == null) throw new IllegalArgumentException();

        if (size == heap.length*3/4){
            heap = this.resize();
        }
        
        
        heap[++size] = data;
        swim(size);
    }

    public Song max(){
        if(!IsEmpty()){
            return heap[1];
        }else{
            throw new IllegalStateException();
        }
    }

    public Song getMax() {
        
        if (IsEmpty()) throw new IllegalStateException();
        
        Song current = heap[1];
        
        if (size > 1) heap[1] = heap[size];
        
        heap[size--] = null;
        
        sink(1);
       
       	return current;
    }

    

    public Song[] resize(){
        return Arrays.copyOf(heap, heap.length * 2);
    }

    public void swim(int i) {
        while (i > 1) {  
            int p = i/2;  
            int result = cmp.compare(heap[i], heap[p]);  
            if (result <= 0) return;    
            swap(i, p);               
            i = p;
        }
    }

    public void sink(int i){
        int left = 2*i;
        int right = left+1;
        int max = left;
       
        while (left <= size) {
            
            if (right <= size) {
                max = (heap[left].compareTo(heap[right])) < 0 ? right : left;
            }
            
            if (heap[i].compareTo(heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }

    public void swap(int i, int j) {
        Song tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    
    public boolean IsEmpty(){
        return size == 0;
    }

    public int size(){
    	return size;
    }


    public void remove(int id){
        int index=-1;
        for(int i=0;i<size;i++){
            if(heap[i].getId()==id){
                index=i;
            }
        }
        heap[index]=new Song(0,null,heap[1].getLikes()+1);
        swim(index);
        getMax();
      
    }

    public void removeMin(int k){
        System.out.println(size);
        
        heap[k]=new Song(0,null,heap[1].getLikes()+1);
        swim(k);
        getMax();

    }

}
