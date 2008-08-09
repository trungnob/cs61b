
public class Coordinate {
private int row;
private int col;
public Coordinate(){
	row=0;
	col=0;
}
public Coordinate(int a, int b){
	row=a;
	col=b;
}
public void setRow(int value){
	row=value;
}
public int  getRow(){
	return row;
}

public void setCol(int value){
	col=value;
}
public int  getCol(){
	return col;
}
public boolean equals(Object b){
	return (row==((Coordinate)b).getRow())&&(col==((Coordinate)b).getCol());
}
}
