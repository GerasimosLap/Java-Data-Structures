import java.util.* ;
import java.io.*;

class ST{
	private class TreeNode{
		WordFreq item;//WordFreq in node
		TreeNode l;// pointer to left subtree
		TreeNode r;// pointer to right subtree
		int number;//number of nodes in the subtree starting at this node 

		TreeNode(WordFreq item){
			this.item=item;
		}
	}
	private TreeNode head; 
	private int distinctwords=0; //number of distinct words
	private int totalwords=0; //total words
	private LinkedList stop=new LinkedList();
	private int maxFreq;//max Frequency
	private TreeNode maxFreqNode;//treenode with max frequency
	 

	public ST(){
		maxFreq=0;
	}

	public void insert(WordFreq item){//simple insertion
       	update(item.key());
	} 

	public void update(String w){//searches if there is a node with key w. If it exists its freq is raised by one, else it is added.
		TreeNode n = head;
       	TreeNode p = null;
		int result = 0;
		totalwords++;
        while (n != null) {
			result = w.compareTo(n.item.key());//result stores the outcome of compareTo (-1,0,1)
            if (result == 0){
				n.item.setFreq();//raises freq by one
            	if(maxFreq<n.item.getFreq()) {//updating max freq
            		maxFreq=n.item.getFreq();
            		maxFreqNode=n;
            	}
            	return;
            }
			p = n;
			p.number++;
            n = result < 0 ? n.l : n.r;//if w is lesser than n's word go to left node else on right node
        }
        //if the w doesn't exist we insert it as leaf
        TreeNode node = new TreeNode(new WordFreq(w));
        //inserting new node in appropriate position of bts
        if (result < 0) {
            p.l = node;
        }
        else if (result > 0) {
            p.r = node;
        }
        else {
            head = node;
		}
		++distinctwords;//increasing number of distinct words
	}

	private WordFreq searchR(TreeNode h, String v) {//recursive  search
		if (h == null) return null;
		if (v.equals(h.item.key())){
			System.out.println("Word"+h.item.key() +"found!");
			return h.item;
		}
		if (v.compareTo(h.item.key())<0){
			return searchR(h.l, v);//continue searching in left subtree
		}else{
			return searchR(h.r, v); //continue searching in right subtree
		}
	}


	public WordFreq search(String w) { 
		WordFreq n= searchR(head, w); 
		if(n==null){
			System.out.println("Word"+n.key() +"not found!"); 
			return null;
		}
		if(n.getFreq()>getMeanFrequency()) insertRoot(n);//insert node n at root if its freq is greater that meanfreq
		return n;
	}
	

	private TreeNode insertR(TreeNode h, WordFreq x) {//recursive root insertion
		if (h == null) return new TreeNode(x);
		if (x.key().compareTo(h.item.key())<0) {
			h.l = insertR(h.l, x);
			h = rotR(h); } 
		else {
			h.r = insertR(h.r, x);
			h = rotL(h); 
		}
		return h; 
	}

	public void insertRoot(WordFreq x) {
		head = insertR(head, x); 
	}

	private TreeNode rotR(TreeNode h) {//right rotation
		TreeNode x = h.l; h.l = x.r; x.r = h; return x; 
	}

	private TreeNode rotL(TreeNode h) {//left rotation
		TreeNode x = h.r; h.r = x.l; x.l = h; return x; 
	}

	private int countR(TreeNode h){
		if(h==null) return 0;
		return 1+countR(h.l)+countR(h.r);
	}

	public void count(TreeNode h){//returns number of nodes that are under treenode h
		
		h.number=countR(h);
	}

	public int getFrequency(String w){//return freq of a word if it exists
		WordFreq word= search(w);
		if(word==null)return 0;
		return word.getFreq();
	} 

	public WordFreq getMaximumFrequency(){//returns max freq node
        return maxFreqNode.item;
    } 

	public void addStopWord(String w){//add stop word to linkedlist 
		stop.insert(w.toLowerCase());
	} 
	public void removeStopWord(String w){//remove stop word from linkedlist
		stop.delete(w.toLowerCase());
	}

	public int getDistinctWords(){ return distinctwords;}//return number of distinctwords words

	public int getTotalWords() { return totalwords; }//returns total words

	public void load(String filename){//load a file
		BufferedReader reader = null;
        String line;
		File f=null;
			
		try{
			f = new File(filename);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }
		
        try {
            reader = new BufferedReader(new FileReader(f));
	    } catch (FileNotFoundException e) {
	        System.err.println("Error opening file!");
	    }
	    try {
	    	line = reader.readLine();
	    	while ((line != null )) {
	    			line = line.replaceAll("'", "").trim();//removes all apostrophes
	        		line=line.replaceAll("[0-9]","").trim();//removes all numbers
	        		line=line.replaceAll("\\p{Punct}","").trim(); //removes all punctuations
	    		    String[]currentLine = line.split("\\W+");
    			        for(String s:currentLine){
    			            if((stop.search(s))==false){
    			            	insert(new WordFreq(s.toLowerCase()));
							}
    			   		 }
    			
    				line = reader.readLine();
	    	}
	    
		} catch (IOException e) {
	        System.out.println("Error reading line.");
	    }
	    try {
	        reader.close();
	    } catch (IOException e) {
	        System.err.println("Error closing file.");
	    }	
	} 


 	
	public void remove(String w) {//remove element
    TreeNode removed = removeR(head, w);
	}


	private TreeNode removeR(TreeNode n, String w) {
		if (n == null) {
			return null;
		}
		else if (n.item.key().compareTo(w) < 0){
			n.r = removeR(n.r, w);
		}
		else if (n.item.key().compareTo(w) > 0){
			n.l = removeR(n.l, w);
		}
		else{
			if (n.r != null){
            WordFreq temp = leftLeaf(n.r).item;
            n.item = temp;
            n.r = removeR(n.r, temp.key()); 
			}
			else{
				n = n.l;
			}
		}
		return n;
	}
	
	private TreeNode leftLeaf(TreeNode n) {//go to far left leaf
		if (n.l == null){
			return n;
		}
		else{
			return leftLeaf(n.l);
		}
	}

	
	public int getMeanFrequency(){//returns mean frequency
		if(distinctwords==0){
			System.out.println("No distinct words");
			return 0;
		}
		return totalwords/distinctwords;
	}

	public void printAlphabetically(PrintStream stream){//prints nodes alphabetically
		printAlphabeticallyR(head,stream);
	}

	public void printAlphabeticallyR(TreeNode node, PrintStream stream) {//prints in order
    	if(node != null) {
        	if(node.l != null) {
        	    printAlphabeticallyR(node.l,stream);
        	}
        	stream.print(node.item+"\n");
        	if(node.r != null) {
        	    printAlphabeticallyR(node.r,stream);
        	}
    	}
	}

    public static void main(String args[]){
    	//ST bst = new ST();
    	//bst.load("Example.txt");
    	//bst.load(args[0]);
    	//bst.printAlphabetically(System.out);
    }
}