

public class Coordinate{
//private Point p;
private short row;
private short col;
public Coordinate(){

row=0;
col=0;
}
public Coordinate(short nrow, short ncol){
 row=nrow;col=ncol;
}
public void setRow(short value){
	row=value;
}
public short  getRow(){
	return row;
}

public void setCol(short value){
col=value;
}
public short  getCol(){
return col;
}
public boolean equals(Object b){
	Coordinate temp=(Coordinate)b;
 if ((row==temp.row)&&(col==temp.col)) return true; else return false;
}
public int hashCode(){
	return ((row<<8)|col);
}
public boolean isOk(){
	if (row<256 && row >0 && col<256 && col>0) return true;
	else return false;
}
}
