package softwareConstruction_OOP_IO;

public abstract class Binary_Operation_Version_1_1 {
	private int Left_Num;
	private int Right_Num;
	private char Operator;
	private int Result;
	
	/*算式类中属性Left_num、Right_num、Operator、Result的get方法*/
	public int getLeft_num() {
		return this.Left_Num;
	}
	public int getRight_num() {
		return this.Right_Num;
	}
	public char getOperator() {
		return this.Operator;
	}
	public int getResult() {
		return this.Result;
	}
	
	/*计算运算式的值*/
	public abstract int calculate(int left_num,int right_num);
	/*判断运算式的值是否合法，返回true合法，返回false不合法*/
	public abstract boolean checkcalculate(int Calculate_Num);
	
	/*构建算术式*/
	public void Construct_Operation(char ch) {
		createRandomNum create_random_num = new createRandomNum();
		int RS;
		/*生成右操作数：不停的生成数值，直到满足条件的那个为止*/
		do {
			/*生成左右操作数*/
			this.Left_Num = create_random_num.getRandomNum();
			this.Right_Num = create_random_num.getRandomNum();
			RS = calculate(this.Left_Num, this.Right_Num);
		}while(!checkcalculate(RS));
		/*记录结果Result和操作符ch*/
		this.Result = RS;
		this.Operator = ch;
	}
}
