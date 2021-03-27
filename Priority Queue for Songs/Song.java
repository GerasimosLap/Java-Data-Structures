import java.io.*;
import java.util.*;
public class Song implements Comparable<Song>{  
	
    int id;
	String title;
	int likes;
    
    Song(int id,  String title, int likes) { //constructor for song
        this.id=id;
		this.title=title;
		this.likes=likes;
		
    } 

    //getters and setters

    public void setId(int id){
    	this.id=id;
    }
  		
	public int getId() {
		return id;
	}

	public void setLikes(int likes){
    	this.likes=likes;
    }
  		
	public int getLikes() {
		return likes;
	}

	public void setTitle(String title){
    	this.title=title;
    }
  		
	public String getTitle() {
		return title;
	}

	public String toString(){
  		return "ID is: "+id + ", Title is " + title + " and likes are: " + likes;
	}

	public int compareTo(Song a) {//compare to operation for likes and title(if likes are equal)
		if(a.getLikes()>this.getLikes()){
			return -1;
		}else if(a.getLikes()<this.getLikes()){
			return 1;
		}else if(a.getLikes()==this.getLikes()){
			return a.title.compareTo(this.title);
		}
		return 0;//impossible case because if likes are chekced then the title of a song can't be the same
	}
}
