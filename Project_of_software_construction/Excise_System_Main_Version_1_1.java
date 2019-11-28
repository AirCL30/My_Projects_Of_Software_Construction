package software_construction_OOP_version_1_1;

import java.util.*;

public class Excise_System_Main_Version_1_1 {
	private static int problem_Kind = 0;
	private static int problem_Num = 0;
	private static char ch;
	private static int ColNum = 0;
	private static Scanner cin = new Scanner(System.in);
	
	private static void display_header_1() {
		System.out.println("*********************以下是生成的算术式******************************");
	}
	private static void display_header_2() {
		System.out.println("           --------------以下是算术式的答案--------------");
	}
	private static void normal_display(Binary_Operation_Version_1_1[] BO_array,int EndI,int colNum) {
		display_header_1();
		/*输出随机生成的算术式*/
		for(int i = 0;i < EndI;i ++) {
			System.out.print((i+1) + ": " + BO_array[i].toString() + "\t");
			if((i+1) % colNum == 0) {
				System.out.println();
			}
		}
		System.out.println();
		
		display_header_2();
		/*显示答案*/
		for(int i = 0;i < EndI;i ++) {
			System.out.print((i+1) + ": " + BO_array[i].getResult() + "\t");
			if((i+1) % colNum == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	/*正常的格式化显示，一行显示5列*/
	public static void format_display(Binary_Operation_Version_1_1[] BO_array,int EndI) {
		normal_display(BO_array, EndI, 5);
	}
	
	/*根据用户需求，更改每行的列数*/
	public static void format_display(Binary_Operation_Version_1_1[] BO_array,int EndI,int colNum) {
		normal_display(BO_array, EndI, colNum);
	}
	
	/*用户出题界面*/
	public static void interface_of_problems(int flag) {
		System.out.println("请输入您需要的题目数：");
		problem_Num = cin.nextInt();
		
		if(problem_Num <= 0) {
			System.out.println("输入有误！请不要输入0 或者 负数！");
		}else {
			Excise_Version_1_1 excises = new Excise_Version_1_1();
			System.out.println("您需要自行设定算术式显示格式吗？如果您不需要，将会为您在一行内显示5列（Yy/Nn）");
			ch = cin.next().charAt(0);
			if(ch == 'Y' || ch == 'y') {
				System.out.println("您需要在一行内显示几列？");
				ColNum = cin.nextInt();
				//flag == 1，获取加法算术式，并显示每行ColNum列
				if(flag == 1) {
					format_display(excises.getAddOperations(problem_Num), problem_Num,ColNum);
				}
				//flag == 2，获取减法算术式，并显示每行ColNum列
				else if(flag == 2) {
					format_display(excises.getSubOperations(problem_Num), problem_Num,ColNum);
				}
				//flag == 3，获取加减法混合算术式，并显示每行ColNum列
				else {
					format_display(excises.getSomeNumProblems(problem_Num), problem_Num,ColNum);
				}
			}else if(ch == 'N' || ch == 'n'){
				//flag == 1，获取加法算术式，并显示每行5列
				if(flag == 1) {
					format_display(excises.getAddOperations(problem_Num), problem_Num);
				}
				//flag == 2，获取减法算术式，并显示每行5列
				else if(flag == 2) {
					format_display(excises.getSubOperations(problem_Num), problem_Num);
				}
				//flag == 3，获取加减法混合算术式，并显示每行5列
				else {
					format_display(excises.getSomeNumProblems(problem_Num), problem_Num);
				}
			}else {
				System.out.println("输入格式有误！请重新输入！");
			}
	
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Addition_Operation addOp = new Addition_Operation();
//		System.out.println(addOp + " " + addOp.getResult());
//		
//		SubstractOperation subOp = new SubstractOperation();
//		System.out.println(subOp + " " + subOp.getResult());
		System.out.println("请选择需要的算术式类型\n1--加法算术式\n2--减法算式式\n3--加减法混合算术式\n请输入：");
		problem_Kind = cin.nextInt();
		if(problem_Kind == 1) {
			interface_of_problems(1);
		}else if(problem_Kind == 2) {
			interface_of_problems(2);
		}else if(problem_Kind == 3) {
			interface_of_problems(3);
		}else {
			System.out.println("输入有误，请重新输入!");
		}
	}
}
