package Project_of_software_construction;

import java.util.Random;

public class My_math_problem {
	static int[] Problem_array = new int[101]; // 记录生成的100个数
	static int Problem_array_id = 1;
	static char[] ch_array = new char[51];     //记录每个算式的符号
	static int ch_array_id = 1;

	//检查是否存在相同的算术式
	public static boolean Check(int a,int b,char str,int ProblemEndId) {
		for(int i = 1;i <= ProblemEndId;i += 2) {
			if((a == Problem_array[i]) && (b == Problem_array[i+1]) && (str == ch_array[(i+1)/2])) {
				return false;
			}
		}
		return true;
	}
	
	// 生成50道题目
	public static void RandomArithmetic() {
		System.out.println("以下是随机生成的50道加减法算术题：");
		Random random = new Random();
		int problem_num = 1; // 用来计算出题数目
		int x1, x2; // a,b代表两个数值
		int k = 0; // k用来标志随机生成的 "+" 和 "-"，0代表 "-" ，1代表 "+"
		char ch; // 记录运算符

		// 出题数目小于50,继续出题
		while (problem_num <= 50) {

			k = random.nextInt(2); // 先生成运算符的随机数
			if (k == 1) {
				ch = '+';
			} else
				ch = '-';
			
			ch_array[ch_array_id++] = ch;
			
			// 开始生成算术式
			while (true) {
				x1 = random.nextInt(101);
				x2 = random.nextInt(101);
				
				//检查生成的算术式是否已经出现过
				if(Check(x1, x2, ch, Problem_array_id-1)) {
					
				}
				else {
					continue;
				}
				
				if (ch == '+') {
					if (x1 + x2 <= 100) {
						System.out.print(problem_num + ":" + x1 + " + " + x2 + " = " + "\t");
						Problem_array[Problem_array_id++] = x1;
						Problem_array[Problem_array_id++] = x2;
						break;
					}
				} else {
					if (x1 - x2 >= 0) {
						System.out.print(problem_num + ":" + x1 + " - " + x2 + " = " + "\t");
						Problem_array[Problem_array_id++] = x1;
						Problem_array[Problem_array_id++] = x2;
						break;
					}
				}
			}
			if(problem_num % 5 == 0) {
				System.out.println();
			}
			problem_num++;
		}
}

	//输出答案
	public static void printAnswer() {
		System.out.println("这50道算术题的答案是：");
		for(int i=1,j=1;i <= 50 ;i++) {
			if(ch_array[i] == '+') {
				System.out.print(i + ":" + (Problem_array[j++] + Problem_array[j++]) + "\t");
			}
			else System.out.print(i + ":" + (Problem_array[j++] - Problem_array[j++]) + "\t");
			if(i % 5 == 0) {
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {

		RandomArithmetic();
		printAnswer();
	}
}
