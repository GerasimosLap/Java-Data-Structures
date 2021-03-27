import java.io.*;
import java.util.*;
import java.util.StringTokenizer.*;

public class TagMatching {
	
	public static StringStackImpl tagMatcher = new StringStackImpl(); //instance of StringStackImpl
	
	public static void main(String[] args){
		BufferedReader reader = null;
        String line;
		String temp;
		String str;
		File f=null;
		
		
	
        try {
			if (null != args[0] && args[0].endsWith(".html")){//checks if argument from cmd is not null and if it is a html file
				f = new File(args[0]);
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
			boolean error = false;
			while ((line != null )&&(error==false)) {
				StringTokenizer strt = new StringTokenizer(line, ("\\<[^>]*>"),true);//create a String Tokenizer (strt) with tokens < and >. True because we want to count < and > as tokens
				while (strt.hasMoreElements()) {//checks if strt has more tokens
					str = strt.nextToken();//if strt has more tokens proceed to store it to str
					if(str.equals("<")){//if the found token is < then the inside will be the name of the tag
						str = strt.nextToken();
						if(!str.startsWith("/")){//if str starts with / it is closing tag. Else it is openning
							tagMatcher.push(str);//if it is openning tag it is pushed at the top of the stack
						}else {//if it is closing tag
							temp = (str.substring(1)).trim();//temp is closing tag without /(slash)
							if(!tagMatcher.isEmpty()){//if stack is not empty continue
								if(temp.equals(tagMatcher.peek())){//compares top of stack with temp
									tagMatcher.pop();//remove top item of stack
								}else{
									error = true;//if they are not equal that means that there is an error in tags so error becomes true
									break;
								}
							}
								
						}
					}
				}
				line=reader.readLine();
			}
			if(!tagMatcher.isEmpty()) error=true;//if tagMatcher is not Empty there is error because some openning tags were not popped(no closing tags found)
			if(error==false){
				System.out.println("File had no wrong tags! Congratulations!");
			}else{
				System.out.println("ERROR! File had wrong tags! Please check your file!");
			}
			System.out.println("Ending Tag Matching...");
		} catch (IOException e) {
            System.out.println("Error reading line.");
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
	}	
			
}
