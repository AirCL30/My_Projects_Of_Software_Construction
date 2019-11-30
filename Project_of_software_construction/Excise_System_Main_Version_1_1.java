package software_construction_OOP_version_1_1;

import java.util.*;

public class Excise_System_Main_Version_1_1 {
	private int problem_Kind = 0;
	private int problem_Num = 0;
	private char ch;
	private int ColNum = 0;
	private int upLine = 100;
	private int downLine = 0;
	private Scanner cin = new Scanner(System.in);
	
	/*欢迎界面*/
	public void display_welcome() {
		System.out.println("                  Welcome to iCounter !\nIntroduction：我是一个会出题的小机器人哦！请试着对我进行操作吧！");
		System.out.println("                         目前我只会出加减法题目，请为我设定好题目算数式的上下限，剩下的就交给我吧！");
		inputUpDownLine();
	}
	
	/*输入上下限*/
	private void inputUpDownLine() {
		System.out.println("请输入：\n       上限：");
		this.upLine = cin.nextInt();
		do {
			System.out.println("       下限：");
			this.downLine = cin.nextInt();
			if(this.downLine >= this.upLine) {
				System.out.println("别闹！下限应该比上限小才是！再输一遍吧");
			}else break;
		}while(true);
		//设置上下限
		UpandDownLine updown = new UpandDownLine();
		updown.setUpLine(upLine);
		updown.setDownLine(downLine);
		System.out.println("好的，上下限设置好啦！");
		display_chose_type();
	}
	
	/*选择出题类型*/
	private void display_chose_type() {
		System.out.println("\n那么接下来，请选择需要的算术式类型\n1--加法算术式\n2--减法算式式\n3--加减法混合算术式\n请输入：");
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
	
	/*用户出题界面*/
	public void interface_of_problems(int flag) {
		System.out.println("请输入您需要的题目数：");
		problem_Num = cin.nextInt();
		
		if(problem_Num <= 0) {
			System.out.println("输入有误！请不要输入0 或者 负数！");
		}else {
			System.out.println("出题中......\n");
			Excise_Version_1_1 excises = new Excise_Version_1_1();
			System.out.println("出题完成!\n");
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
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Excise_System_Main_Version_1_1 mainSystem = new Excise_System_Main_Version_1_1();
		mainSystem.display_welcome();
	}
}
