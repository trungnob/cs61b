import java.util.HashSet;

public class hashsolve {
	/*Trying to see if tow boards are equal
	by checking every block value
	*/
	private HashSet myboards = new HashSet();
	public boolean equals(Object o){							
		for (int i = 0; i < col.length; i++){
			for (int j = 0; j < col.length[0]; j++){
				if (((Solver)o).block[i][j] != this.block[i][j])
					return false;
			}
		}
	
	return true;}
	/*generating hashcode from row0 and column0 only
	there will be collision and we will probably 
	change this hashcode but for now it's fine
	this method is kinda bad since it's gonna take O(n^2)
	 */
	public int hashcode(){
		int number = 0;
		for (int i = 0; i < col.length; i++){
			for (int j = 0; j < col.length[0]; j++){
				if ((i >= 0) && (j >= 0))
					continue;
				number = number + this.block[i][j];
				}		
			}
	
	return number;}
	/* this should be a mutually recursive function with the generatemove
	 * method so generate move calls trymove and if board exists it gonna 
	 * return if not it will add the board to the hashtable and generate another
	 */
	public boolean trymove(Solver board){
	if (myboards.contains(board))
		return false;
	myboards.add(board);
	generatemove(board);
	return true;}
	
	/* adds board to hashset
	 * 
	 */
	public add(Solver board){
		myboards.add(board);
	}
	
	
}
