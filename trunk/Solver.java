
public class Solver {

	
	
	public static void main (String [ ] args) {
		Solver s0= new Solver();
		InputSource in;
		if (args.length == 2) {
			in = new InputSource (args[0]);
		} else if (args.length == 3){
			in = new InputSource (args[1]);
		} else{
			System.out.println("Wrong number of arguments");
			System.exit (0);
		}
	
	}
}
