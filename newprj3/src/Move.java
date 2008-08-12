//Contain direction which is either 'S'(stay or don't move) , 'L' (Left) , 'R'(Right), 'U'(up), 'D'(Down);
public class Move {
	
	private char dir;
	public Move(){
		dir='S'; //don't move
	}
	public Move(char set_dir){
		dir=set_dir;
	}

	public void setDir(char new_dir){
		dir=new_dir;
	}
	public void setMove(Move newMove){
		dir=newMove.getDir();
	}
	public void setMove(char new_dir){
		dir=new_dir;
	}
	public Move getMove(){
		return this;
	}
	
	public char getDir(){
		return dir;
	}
	
}
