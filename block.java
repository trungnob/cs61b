
public class block { 

	private int lenandwid;
	private int rowandcol;
	private short widlen;
	private short collen;
	private int ID; // ID is the identification number of block which help to distinguish two different block even though it have same shape. 
	
	public block(int length, int width, int row, int col){
		Integer myint = new Integer(col);
		collen = (short)myint.toString().length();
		rowandcol =col +((int)Math.pow(10, collen)) * row;
		myint = new Integer(width);
		widlen = (short)myint.toString().length();
		lenandwid =width +((int)Math.pow(10, widlen)) * length;
	}
	
	public block(int length, int width, int row, int col, int IDnum){
		Integer myint = new Integer(col);
		collen = (short)myint.toString().length();
		rowandcol =col +((int)Math.pow(10, collen)) * row;
		myint = new Integer(width);
		widlen = (short)myint.toString().length();
		lenandwid =width +((int)Math.pow(10, widlen)) * length;
		ID  = IDnum;
	}
	
	public boolean equals(Object obj){ // two equal blocks only have meaning when they are both on the same board.
		if ( (this.getrow()== ((block)obj).getrow()) && (this.getcol() == ((block)obj).getcol()) 
			 && (this.getlength() == ((block)obj).getlength()) && (this.getwidth() == ((block)obj).getwidth()) )
			return true;
		else
			return false;
	}

	public int getID(){
		return ID;
	}

	public int getlength(){
		return lenandwid/(int)(Math.pow(10, widlen));
	}
	
	public int getwidth(){
		return lenandwid%(int)(Math.pow(10, widlen));
	}
	public int getrow(){
		return rowandcol/(int)(Math.pow(10, collen));
	}
	public int getcol(){
		return rowandcol%(int)(Math.pow(10, collen));
	}
	
}
