
public class BlockShape {
	private byte length;
	private byte width;
	public BlockShape(){
		length=0;
		width=0;
	}
	public BlockShape(byte l, byte w){
		length=l;
		width=w;
	}
	public boolean equals(Object o){
		return (((BlockShape)o).length==this.length && ((BlockShape)o).width==this.width); 
		
	}
	public byte getLength(){
	return length;	
	}
	public byte getWidth(){
		return width;
		
	}
}
