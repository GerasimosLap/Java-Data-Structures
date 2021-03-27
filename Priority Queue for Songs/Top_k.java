import java.io.*;
import java.util.*;
public class Top_k {
	public static ListForTopK test = new ListForTopK(); 
	public static void main(String[] args){
			BufferedReader reader = null;
	        String line;
			int id;
			String title;
			int likes;
			File f=null;
			int k=0;
			int songs=0;
				
	        try {
				if (null !=args[0]&& null != args[1] && args[1].endsWith(".txt")){//1st argument of cmd is k number of songs to display and 2nd argument is name of file.txt 
					f = new File(args[1]);
					k=Integer.parseInt(args[0]);
				}
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
					//getting the 3 arguments from each file using substring and indexOf 
					id=Integer.parseInt(line.substring(0,line.indexOf(' ')));//id of song
					title=line.substring(line.indexOf(' ')+1,line.lastIndexOf(' '));//title of song
					likes=Integer.parseInt(line.substring(line.lastIndexOf(' ')+1));//likes of song
					if((id>=1)&&(id<=9999)&&(title.length()<=80)){//checking bounds of id and title and creating the object song
						test.insert(new Song(id,title,likes));
						songs++;
					}else if((id<1)||(id>9999)){//error message if id is out of bounds
						System.out.println("Song: "+title+" not added! ID is out of bounds!");
					}else if((title.length()>80)){//error message if title is over 80 chars
						System.out.println("Song: "+title+" not added! Title is over 80 characters!");
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

	        if(k>=songs){//error message for k input
	        	System.out.println("K must be lesser than total number of songs!");
	        }else{
		        System.out.println("Top " +k+ " songs will be displayed");
		       	test.printList(test.mergeSort(test.head),k);
		    }
	        System.out.println("Ending Top_k...");
		}	
	}