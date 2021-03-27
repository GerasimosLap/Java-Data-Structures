import java.io.*;
import java.util.*;
import java.util.StringTokenizer.*;


public class NetBenefit {

	public static IntQueueImpl numberofstocks = new IntQueueImpl(); //instance of IntQueueImpl
	public static IntQueueImpl priceofstocks = new IntQueueImpl();//instance of IntQueueImpl
	public static boolean errorFlag = false;
	public static int revenue=0;
	


	public static void main(String[] args){
		File f = null;
		BufferedReader reader = null;
		String line;
		

       try {
			if (null != args[0] && args[0].endsWith(".txt")){//checks if argument from cmd is not null and if it is a txt file
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
			int totalstocks=0;//Total number of stocks
			int sellprice=0;//variable to store the sell price of current sale
			int sellpieces=0;////variable to store the pieces of current sale
			int dif;
			while (line != null) {
				if (line.contains("buy")){
					numberofstocks.put(Integer.parseInt(line.substring(line.indexOf("y")+2,line.indexOf("p")-1).trim()));//puts the number between "buy " and " price" in numberofstocks as an integer
					totalstocks = totalstocks + (Integer.parseInt(line.substring(line.indexOf("y")+2,line.indexOf("p")-1).trim()));//puts the number between "buy " and " price"  in totalstocks as an integer
					priceofstocks.put(Integer.parseInt(line.substring(line.indexOf("e")+2).trim()));//puts the number after" price" in priceofstocks as an integer
				}else if(line.contains("sell")){
					if ((Integer.parseInt(line.substring(line.indexOf(("l"),2)+2,line.indexOf("p")-1).trim()))<=totalstocks){//gets the number between "sell " and " price" and and checks if it is smaller than our totalstocks
						sellpieces  = (Integer.parseInt(line.substring(line.indexOf(("l"),2)+2,line.indexOf("p")-1).trim()));//stores the number between "sell " and " price" in sellpieces
						sellprice = (Integer.parseInt(line.substring(line.indexOf("e",2)+1).trim()));//stores the number after " price" in sellprice
						while((sellpieces>0)&&(!numberofstocks.isEmpty())){
							int topitem = numberofstocks.peek();
							int topitemPrice = priceofstocks.peek();
							if(sellpieces<topitem){//the remaining pieces to be sold are less than topitem we must keep the remaining pieces and price at stack
								revenue = revenue + sellpieces*(sellprice-topitemPrice);//use sellpieces in the formula instead of topitem because we don't want the whole item from top of list
								dif = numberofstocks.peek() - sellpieces;//the reamining pieces. Also we don't pop the price
								totalstocks = totalstocks - sellpieces;
								numberofstocks.head.item = dif;//set the remaining as head to use in next sale
								sellpieces = 0;
								break;
							}else{
								revenue = revenue + topitem*(sellprice-topitemPrice);
								numberofstocks.get();//get after the formula is used 
								priceofstocks.get();//get after the formula is used 
								totalstocks = totalstocks-topitem;
								sellpieces = sellpieces - topitem;
							}
							if(sellpieces==0) break;
							
						}
						
					}else {
						System.out.println("ERROR, more stocks tried to be sold than we currently have!");
						errorFlag = true;
						break;
					}
				}
				line = reader.readLine();
			}
			
			
		} catch (IOException e) {
            System.out.println("Error reading line ");
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
		 
    
		if (errorFlag == false){
			if(revenue >=0){
				System.out.println("Final revenue is: " + revenue);
			}else{
				System.out.println("Final loss is: " + revenue);
			}
		}else{
			System.out.println("Ending Net Benefit...");
		}
		
	}
}		