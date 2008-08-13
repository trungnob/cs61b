public class Move implements Comparable<Move>{
	
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
	public int compareTo(Move o) {
	
		if (dir== o.dir) return 0;
		else if (dir=='D') return 1;
		else if (dir=='D')return -1;
		else if(dir=='R')return -1;
		else if (dir=='R') return 1;
		else if (dir=='L') return 1;
		else return -1;
	
	}
}