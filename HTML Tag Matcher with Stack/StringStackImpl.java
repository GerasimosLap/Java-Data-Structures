import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack {

	private Node head;
	private int size;
	private String item;
	
	private class Node{
		String item;
		Node next;
    }
	
	public StringStackImpl(){//Constructor of StringStackImpl (with Node Head= null and size = 0 because Stack is empty at the beginning)
   	    head = null;
		size = 0;
   	}
	
	public boolean isEmpty(){//checks if Stack is empty(if head is null then Stack is empty)
		if(this.head == null){
			size=0;
			return true;
		}
		return false;
	}

	public void push(String item){//pushes(adds) an item at top of the Stack. Creates a node and setts its item to item and next to head. Increases size by 1
		Node node = new Node();
		node.item = item;
		node.next = head;
		head=node;
		size++;
	}
	
	public String pop(){//pops(removes) and item from the top of the Stack.If Stack is empty a NoSuchElementException is thrown. Decreases size by 1
		if(isEmpty()){
			throw new NoSuchElementException("Empty Stack");
		}
		String itemTemp = head.item;
		head = head.next;
		size--;
		return itemTemp;
		
	}
	
	public String peek(){//peeks(returns) top item of Stack. If it's empty it throws NoSuchElementException
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Stack");
		}
		return head.item;
	}
	
	public void printStack(PrintStream stream){//prints stack
	while(!isEmpty()){
		stream.println(head.item);
		stream.flush();
		head = head.next;
		}
	}
	
	public int size(){//returns size of Stack
		return size;
	}
}
	