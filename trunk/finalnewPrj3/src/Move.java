//Contain direction which is either 'S'(stay or don't move) , 'L' (Left) , 'R'(Right), 'U'(up), 'D'(Down);
public class Move implements Comparable<Move>{
	
	private char dir;
	private  block b;
	public Move(){
		dir='S'; //don't move
	}
	public Move(block mb, char newdir){
		b=mb;
		dir=newdir;
		
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
	public void doMove(){
		b.ApplyMove(dir);
	}
	public char getDir(){
		return dir;
	}
	public block getBlock(){
		return b;
	

	}

	
	public int compareTo(Move o) {
		int result = b.compareTo(o.b);
		if (result ==0){
		if (dir== o.dir) return 0;
		else if (dir=='D') return 1;
		else if (dir=='D')return -1;
		else if(dir=='R')return -1;
		else if (dir=='R') return 1;
		else if (dir=='L') return 1;
		else return -1;
		}else return result;
	}
	public String toString(){
		String temp="";
		return temp+=dir;
	}
	public int hashCode(){
		return b.hashCode();
	}
	public boolean isOk(){
		return b.isOk() && (dir == 'S'|| dir=='L'||dir=='U'||dir=='D');
	}
}
