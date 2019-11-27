package software_construction_OOP;

import java.util.*;

public class Binary_Operation {
	private int Left_Num;
	private int Right_Num;
	private char Operator;
	private int Result;
	Random random = new Random();
	
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
	
	/*构造函数*/
	public Binary_Operation() {
		makeOperator();
	}
	
	/*Step1：生成操作符*/
	private void makeOperator() {
		{
			int flag;
			flag = random.nextInt(2);
			/*随机生成操作符，0 代表 “+” ，1 代表 “-” */
			if(flag == 0) {
				this.Operator = '+';
			}
			else this.Operator = '-';
		}
		makeOperationNum();
	}
	
	
	/*Step2：生成左右操作数*/
	private void makeOperationNum(){
		{
			/*先生成左操作数Left_Num*/
			this.Left_Num = random.nextInt(101);
			/*再根据计算结果 Result 在 0~100 范围内，生成右操作数 Right_Num*/
			int RS;
			do {
				this.Right_Num = random.nextInt(101);
				if(this.Operator == '+') {
					RS = this.Left_Num + this.Right_Num;
				}
				else RS = this.Left_Num - this.Right_Num;
			}while(RS > 100 || RS < 0);
			
			/*Step3：记录计算结果 Result*/
			this.Result = RS;
		}
	}
	
	/*Step4：返回运算式*/
		//返回算术式
	@Override
	public String toString(){
		return (this.Left_Num + " " + this.Operator + " " + this.Right_Num + " = ");
	}
		//返回算术式 + 答案
	public String toStringWithResult() {
		return (this.Left_Num + " " + this.Operator + " " + this.Right_Num + " = " + this.Result);
	}
}
