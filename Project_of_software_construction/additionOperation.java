package softwareConstruction_OOP_IO;

public class additionOperation extends Binary_Operation_Version_1_1{
	/*加法算术式的构造方法，当new加法算术式对象时，自动调用继承抽象类的Construction_Opreation方法*/
	public additionOperation() {
		// TODO Auto-generated constructor stub
		this.Construct_Operation('+');
	}
	
	/*重写计算结果方法，返回相加值*/
	@Override
	public int calculate(int left_num, int right_num) {
		// TODO Auto-generated method stub
		return left_num + right_num;
	}

	/*检查计算结果是否合法，合法则返回true，不合法返回false*/
	@Override
	public boolean checkcalculate(int Calculate_Num) {
		// TODO Auto-generated method stub
		//这里使用算术式上下限类进行判断
		UpandDownLine updown = new UpandDownLine();
		if(Calculate_Num > updown.getUpLine() || Calculate_Num < updown.getDownLine()) {
			return false;
		}
		else return true;
	}
	
	/*重写toString()，返回生成的算术式*/
	@Override
	public String toString() {
		if(this.getLeft_num() < 0 && this.getRight_num() < 0) {
			return ("(" + this.getLeft_num() + ") " + this.getOperator() + " (" + this.getRight_num() + ") =");
		}else if(this.getLeft_num() < 0) {
			return ("(" + this.getLeft_num() + ") " + this.getOperator() + " " + this.getRight_num() + " =");
		}else if(this.getRight_num() < 0) {
			return (this.getLeft_num() + " " + this.getOperator() + " (" + this.getRight_num() + ") =");
		}else return (this.getLeft_num() + " " + this.getOperator() + " " + this.getRight_num() + " =");
	}
}
