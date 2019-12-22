package softwareConstruction_OOP_IO;

public class UpandDownLine {
	/*算术式的上下限*/
	private static int upLine = 100;
	private static int downLine = 0;
	
	/*上下限的set方法*/
	public void setUpLine(int num) {
		upLine = num;
	}
	public void setDownLine(int num) {
		downLine = num;
	}
	/*上下限的get方法*/
	public int getUpLine() {
		return upLine;
	}
	public int getDownLine() {
		return downLine;
	}
}
