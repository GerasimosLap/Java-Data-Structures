import java.util.* ;
import java.io.*;
class LinkedList{
    private static Node head;
    private static int stopwords;

    class Node{
        private Node next;
        private String word;
        
        public Node(String word)
        {
            this.word = word;
        }
        
        public String getWord()
        {
            return word;
        }
    }
    
    public LinkedList(){
        head = null;
    }
    
    public void insert(String word){
        System.out.println("ok");
        Node temp = new Node(word);
        if (head==null){
            head = temp;
            return;
        }
        temp.next = null;
        Node p = head;
        while(p.next!=null){
            p = p.next;
        }
        p.next=temp;
        stopwords++;
    }
    
    
    public void delete(String s){
        Node temp = head;
        Node prev=null;
        int index = 0;
        if((temp!=null)&&(temp.getWord()).equals(s)){
            head=temp.next;
            return;
        }

        while(temp!=null&&temp.getWord()!=s){
            prev=temp;
            temp=temp.next;
        }

        if(temp==null){
            System.out.println(s+"stopword not found!");
            return;
        }
        System.out.println(s+" stopword removed!");
        prev.next = temp.next;
        stopwords--;
        return;

    }
       
    public static boolean search(String w){
        Node t = head;
        while(t!=null){
            if((t.getWord()).equals(w)){return true;}
                t=t.next;
        }
        return false;
    }
    public static void printList(){
        Node temp = head;
        while(temp != null)
        {
            System.out.println(temp.word);
            temp = temp.next;
        }
    }
    
    public static int getSize(){
        return stopwords;
    }
    
}