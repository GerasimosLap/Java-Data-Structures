import java.io.*;
import java.util.*;
public class ListForTopK {
		public class Node{
			public Song data;
		    public Node next;
		    public Node(Node next){
		    	this.next=null;
		    }
			public Node(Song data, Node next) {
	       		this.data = data;
	       		this.next = next;
	    	}
		}

		public int size;
	    public Node head;
	    public Node tail;

	    public ListForTopK() {
	        this.head = this.tail = null;
	        this.size = 0;
	    }

	    public boolean isEmpty() {
	        return head ==  null;
	    }

	    public int size() {
    		return size;           
		}

		public void insert(Song s){ 
			Node t = tail;
			tail = new Node(s,null);
			if(isEmpty())
				head = tail;
			else
				t.next = tail;
			size++;
		}

	  

		public Song getmax(){

	    	if(isEmpty()){
	    		return null;
	    	}else if(size == 1){
	    		return head.data;
	    	}
	    	Node max = head;
	    	Node lastMax = null;
	    	Node current = max.next;
	    	Node previous = max;
	    	while(current !=null){
	    		Song CurrentMax = max.data;
	    		Song NextSong = current.data;
	    		if(NextSong.compareTo(CurrentMax)>0){
	    			max = current;
	    			lastMax = previous;
	    		}
	    		previous = current;
	    		current = current.next;
	    	}
	    	if(lastMax!=null){
	    		lastMax.next = max.next;
	    	}else{
	    		head = max.next;
	    	}
	    	size--;
	    	return max.data;
	  	}
		
		

		public Song remove(int x){//removes given element
    		if(isEmpty()){
	        	return null;
	        }
	        Node current = head; 
	        Node deleted;
		        		   
		    while (current != null){ 
		    	if (current.next.data.id == x){
		        	if(current.next!=null){
		        		deleted = current.next;
		        		current.next = current.next.next;
		        		System.out.print("DELETED SONG IS: ");
		        		System.out.println(deleted.data.title);
		          		return deleted.data;
		          	}else{

		          		current=tail;
		        		tail=null;
		        		System.out.print("DELETED SONG IS: ");
		        		System.out.println(current.data.title);
		        		
		          		return current.data;
		          	} 
		        }
		        current = current.next;
		    } 
		    System.out.println("ID not found!");
		    return null;

    	}
				

		public boolean searchID(int x) { //searches id of song
		    Node current = head;    
		    while (current != null){ 
		    	System.out.println(current.data.getId());
		        if (current.data.getId() == x){
		            return true;   
		        }
		        current = current.next;
		    } 
		    return false;    
		} 

		public void printList(Node node,int k){
			if(node==null){
				return;
			}
			int i = 0;
			while((i<k)&&(node.next!=null)){
				System.out.println(node.data);
				node=node.next;
				i++;
			}
		}

		public Node middleNode(Node n){//returns middle node
			if(n ==null)return null;
			Node a = n;//current node
			Node b = n.next;//next of our current node
			while(b!=null &&b.next!=null){
				a=a.next;
				b=b.next.next;
			}
			return a;
		}

		public Node mergeSort(Node node){
			if(node==null||node.next==null)return node;
			Node mid=middleNode(node);//mid is our middle node
			Node nextOfMid = mid.next;//next node of middle node
			mid.next = null;
			return merge(mergeSort(node),mergeSort(nextOfMid));
		}

		public Node merge(Node a,Node b){
			Song s = new Song(0,null,0);
			Node temp = new Node(s,null);
			Node finalnode = temp;
			while(a!=null&&b!=null){//continue until both a and b are null
				if((a.data).compareTo(b.data)>0){
					temp.next=a;
					a=a.next;
				}else{
					temp.next=b;
					b=b.next;
				}
				temp = temp.next;
			}
			if(a==null){
				temp.next = b;
			}else{
				temp.next = a;
			}
			return finalnode.next;
		}
}
