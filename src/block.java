import java.util.ArrayList;
import java.util.Stack;
public class block { 

	private BlockShape block_shape;
	private Coordinate block_pos;
	private int ID; // ID is the identification number of block which help to distinguish two different block even though it have same shape.
	private Stack <Move> my_move_stack= new Stack<Move>();
	public String toString(){
		String temp= block_pos.getRow()+" "+ block_pos.getCol()+ " " + block_shape.getLength()+" "+block_shape.getWidth();
		return temp;
	}
	public block(){
		Move myMove = new Move();
		my_move_stack.push(myMove);
		block_shape=new BlockShape();
		block_pos=new Coordinate();
	}
	public block(int length, int width, int row, int col){
		Move myMove = new Move();
		my_move_stack.push(myMove);
		block_shape=new BlockShape(length,width);
		block_pos=new Coordinate(row,col);
	}
	
	public block(int length, int width, int row, int col, int IDnum){
		Move myMove = new Move();
		my_move_stack.push(myMove);
		block_shape=new BlockShape(length,width);
		block_pos=new Coordinate(row,col);
    	ID=IDnum;
	}
	public int hashCode ( ) { 
		return (get_block_len()-1)*256^3 + (get_block_wid()-1) * 256^2 + (get_block_row()) * 256 + (get_block_col());
		//return 1 ; //---> I don't know yet 
	}
	
	public boolean equals(Object obj){ // two equal blocks only have meaning when they are both on the same board.
//		if ( (this.get_block_row()== ((block)obj).get_block_row()) && (this.get_block_col() == ((block)obj).get_block_col()) 
//			 && (this.get_block_len() == ((block)obj).get_block_len()) && (this.get_block_wid() == ((block)obj).get_block_wid()) )
//			return true;
//		else
//			return false;
	return this.toString().equals(((block)obj).toString());
//		return this.ID==((block)obj).ID;
	}
	
	public int getID(){
		return ID;
	}
	
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
	
	public BlockShape getShape(){
		return block_shape;
	}
	
	public Coordinate getCoor(){
		return block_pos;
	}
	
	
	
	public void MoveTo(Coordinate To){
		block_pos.setCol(To.getCol());
		block_pos.setRow(To.getRow());
	}
	public void MoveTo(int Row, int Col){
		block_pos.setCol(Col);
		block_pos.setRow(Row);
	}
	public void MoveLeft(){
	MoveTo(block_pos.getRow(),block_pos.getCol()-1);
	}
	public void MoveRight(){
		MoveTo(block_pos.getRow(),block_pos.getCol()+1);
		}
	public void MoveUp(){
		MoveTo(block_pos.getRow()-1,block_pos.getCol());
		}
	public void MoveDown(){
		MoveTo(block_pos.getRow()+1,block_pos.getCol());
		}
	public void SetMove(Move NewMove){
		my_move_stack.peek().setMove(NewMove);
	}
	public void AddMove(Move NewMove){
		my_move_stack.push(NewMove);
	}
	
	public void ApplyNextMove(){
		if (my_move_stack.isEmpty()) System.out.println("No move to make");
		else 
		ApplyMove(my_move_stack.peek());
		
	}
	
	public void ApplyMove(Move thisMove){
		switch (thisMove.getDir()){
		case 'S' :break;
		case 'L' : MoveLeft();break;
		case 'R' : MoveRight();break ;
		case 'U' : MoveUp(); break;
		case 'D' :MoveDown();break;
		default : System.out.println("Dude! Some thing wrong with your move in block class!!!");
		}
	}
	public Move PeekPrevMove(){
		Move saveCurrentMove;
		Move PrevMove;
		if (my_move_stack.isEmpty()){ System.out.println("No move to make"); return null;} 
		else saveCurrentMove=my_move_stack.pop();
		if (my_move_stack.isEmpty()) {System.out.println("No Previous Move dude!");return null;}
		else {
				PrevMove= my_move_stack.peek();
				my_move_stack.push(saveCurrentMove);
				return PrevMove;
			}
	}
	public Move peekMove(){
		if (my_move_stack.isEmpty()) {System.out.println("No move to make");return null;}
		else 
		return my_move_stack.peek();
	}
	/*
	public static void main (String [ ] args){
		block b0 = new block(10,1,1,1);
		System.out.println(b0);
	}*/
}
