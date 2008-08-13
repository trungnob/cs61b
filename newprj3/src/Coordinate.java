import java.awt.Point;

public class Coordinate{
//private Point p;
private byte row;
private byte col;
public Coordinate(){

row=0;
col=0;
}
public Coordinate(byte nrow, byte ncol){
 row=nrow;col=ncol;
}
public void setRow(byte value){
	row=value;
}
public byte  getRow(){
	return row;
}

public void setCol(byte value){
col=value;
}
public byte  getCol(){
return col;
}
public boolean equals(Object b){
	Coordinate temp=(Coordinate)b;
 if ((row==temp.row)&&(col==temp.col)) return true; else return false;
}
public int hashCode(){
	return ((row<<8)|col);
}
}
