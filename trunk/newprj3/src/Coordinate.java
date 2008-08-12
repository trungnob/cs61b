import java.awt.Point;

public class Coordinate{
private Point p;
//private int row;
//private int col;
public Coordinate(){
	p= new Point(0,0);
}
public Coordinate(int row, int col){
 p= new Point(col,row);
}
public void setRow(int value){
	p.y=value;
}
public int  getRow(){
	return p.y;
}

public void setCol(int value){
	 p.x=value;
}
public int  getCol(){
	return p.x;
}
public boolean equals(Object b){
 return this.p.equals(((Coordinate)b).p);
}
public int hashCode(){
	return p.hashCode();
	
}
}
