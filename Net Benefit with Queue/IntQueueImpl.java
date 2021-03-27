import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue {
	
	public Node head;
	private Node tail;
	private int size;
	public int item;
	
	public class Node{
		int item;
		Node next;
    }
	
	public IntQueueImpl(){//constructor of IntQueueImpl with head=tail=null and size=0 because Queue is empty at the beginning
   	    head = null;
		tail = null;
		size = 0;
   	}
	
	public boolean isEmpty(){//checks if Queue is empty(if head is null then Queue is empty)
		if(head == null){
			size=0;
			return true;
		}
		return false;
	}
	
	public void put(int item){//puts(adds) item to the Queue increases size of Queue by 1
		Node oldTail = tail;
		tail = new Node();
		tail.item = item;
		tail.next = null;
		if (isEmpty()){
		  head = tail;
		}
		else{
		  oldTail.next = tail;
		}
		size++;
	}
	
	public int get(){//gets(removes) the oldest item(head) and decreases size of Queue by 1
		if(isEmpty()) {
			tail=null;
			throw new NoSuchElementException("Empty Queue");
		}
		int itemTemp = head.item;
		head = head.next;
		size--;
		return itemTemp;
	}
	
	public int peek(){//peeks(returns) top item of Queue. If it's empty it throws NoSuchElementException
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Queue");
		}
		return head.item;
		

	}
	
	public void printQueue(PrintStream stream){//prints Queue

		while(!isEmpty()){
			stream.println(head.item);
			stream.flush();
			head = head.next;
		}
	}
	
	public int size(){//returns size of Queue
		return size;
	}
}
	