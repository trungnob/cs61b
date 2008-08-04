
public class block {

	private int block_len;
	private int block_wid;
	private int block_row;
	private int block_col;
	
	public block(int length, int width, int row, int col){
		block_len = length;
		block_wid = width;
		block_row = row;
		block_col = col;
	}
	
	public int hashCode ( ) { 
		return (block_len-1)*256^3 + (block_wid-1) * 256^2 + (block_row) * 256 + (block_col);
	}
	
	public boolean equals(Object obj){
		if ( (this.block_row == ((block)obj).get_block_row()) && (this.block_col == ((block)obj).get_block_col()) 
			 && (this.block_len == ((block)obj).get_block_len()) && (this.block_wid == ((block)obj).get_block_wid()) )
			return true;
		else
			return false;
	}
	
	/*public String toString(){
	    return 	numtostring(block_len)+numtostring(block_wid)+numtostring(block_row)+numtostring(block_col);
	}
	
	public String numtostring(int num){
		if (num <10){
			System.out.println(num);
			System.out.println("00"+Integer.toString(num));
			return "00"+Integer.toString(num);}
		else if (num <100)
		    return "0"+Integer.toString(num);
		else
			return ""+Integer.toString(num);
	}*/
	
	public int get_block_row(){
		return block_row;
	}
	
	public int get_block_col(){
		return block_col;
	}
	
	public int get_block_len(){
		return block_len;
	}
	
	public int get_block_wid(){
		return block_wid;
	}
	/*
	public static void main (String [ ] args){
		block b0 = new block(10,1,1,1);
		System.out.println(b0);
	}*/
}
