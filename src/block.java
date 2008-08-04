
public class block { 

//	private int block_len;
//	private int block_wid;
//	private int block_row;
//	private int block_col;
	private BlockShape block_shape;
	private Coordinate block_pos;
	//private int ID; // ID is the identification number of block which help to distinguish two different block even though it have same shape. 
	public block(){
//		block_len = 0;
//		block_wid = 0;
		block_shape=new BlockShape();
		block_pos=new Coordinate();
	}
	public block(int length, int width, int row, int col){
//		block_len = length;
//		block_wid = width;
//		block_row = row;
//		block_col = col;
		block_shape=new BlockShape(length,width);
		block_pos=new Coordinate(row,col);
	}
	
	public block(int length, int width, int row, int col, int IDnum){
//		block_len = length;
//		block_wid = width;
//		block_row = row;
//		block_col = col;
		block_shape=new BlockShape(length,width);
		block_pos=new Coordinate(row,col);
//		ID=IDnum;
	}
	public int hashCode ( ) { 
		//return (block_len-1)*256^3 + (block_wid-1) * 256^2 + (block_row) * 256 + (block_col);
		return 1 ; //---> I don't know yet 
	}
	
	public boolean equals(Object obj){ // two equal blocks only have meaning when they are both on the same board.
		if ( (this.get_block_row()== ((block)obj).get_block_row()) && (this.get_block_col() == ((block)obj).get_block_col()) 
			 && (this.get_block_len() == ((block)obj).get_block_len()) && (this.get_block_wid() == ((block)obj).get_block_wid()) )
			return true;
		else
			return false;
//		return this.ID==((block)obj).ID;
	}
	
//	public int getID(){
//		return ID;
//	}
	
	public boolean isSameShape(block toCompare){
		return this.block_shape.equals(toCompare.block_shape);
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
		return block_pos.getRow();
	}
	
	public int get_block_col(){
		return block_pos.getCol();
	}
	
	public int get_block_len(){
		return block_shape.getLength();
	}
	
	public int get_block_wid(){
		return block_shape.getWidth();
	}
	/*
	public static void main (String [ ] args){
		block b0 = new block(10,1,1,1);
		System.out.println(b0);
	}*/
}
