
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
	
	
	public int compare(block o1, block o2){
		if (o1.getrow() < o2.getrow() || ((o1.getrow() == o2.getrow() && o1.getcol() < o2.getcol())))
			return -1;
		else if (o1.getrow() == o2.getrow() && o1.getcol() == o2.getcol())
			return 0;
		else 
			return 1;
	}
	
    public boolean equals(Object o){
    	if (this.getcol() == ((block) o).getcol() && this.getrow() == ((block) o).getrow() && this.getlength() == ((block) o).getlength() && 
    			this.getID() == ((block) o).getID() && this.getwidth() == ((block) o).getwidth())
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
