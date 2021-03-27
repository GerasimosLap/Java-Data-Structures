import java.util.* ;
import java.io.*;

public class WordFreq{ 

private String word;
private int FreqOfWord;
	WordFreq(String w){
		this.word=w;
		this.FreqOfWord=1;//when a new word is inserted, its frequency is 1
	}

	public String key() { 
		return word; 
	}

	public int getFreq() { 
		return this.FreqOfWord; 
	}

	public void setFreq() { //when a freq is inserted, we raise its freq by one
		this.FreqOfWord++;
	}	

	public String toString(){
		return "Word: " + key() + " with word frequency:" + getFreq();
	}
}
