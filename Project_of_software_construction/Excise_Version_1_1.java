package softwareConstruction_OOP_IO;

import java.util.Random;

public class Excise_Version_1_1 {
//	private int MAX_Num = 1000;
//	Addition_Operation[] addOP_array = new Addition_Operation[MAX_Num];
//	SubstractOperation[] subOP_array = new SubstractOperation[MAX_Num];
	
	/*检查是否存在相同的算术式，存在相同的返回true，没有相同的返回false*/
	private boolean checkIfSame(Binary_Operation_Version_1_1[] BO_array,int EndI,Binary_Operation_Version_1_1 BO_Now) {
		for(int i = 0;i < EndI ;i ++) {
			if(BO_array[i].toString().equals(BO_Now.toString())) {
				return true;
			}
		}
		return false;
	}
	
	/*产生一定数量num的习题，因为没有指明产生什么类型的习题，所以这里随机生成习题，加减法都会生成*/
	public Binary_Operation_Version_1_1[] getSomeNumProblems(int num) {
		Binary_Operation_Version_1_1[] BO_random_array = new Binary_Operation_Version_1_1[num];
		Random random = new Random();
		
		for(int i = 0;i < num;i ++) {
			/*随机生成加减法算式，0代表加法，1代表减法*/
			int flag = random.nextInt(2);
			do {
				if(flag == 0) {
					BO_random_array[i] = new additionOperation();
				}
				else BO_random_array[i] = new SubstractOperation();
			}while(checkIfSame(BO_random_array, i, BO_random_array[i]));
		}	
		return BO_random_array;
	}
	
	/*产生加法算术式*/
	public Binary_Operation_Version_1_1[] getAddOperations(int num) {
		additionOperation[] ADD_random_array = new additionOperation[num];
		for(int i = 0;i < num ;i ++) {
			do {
				ADD_random_array[i] = new additionOperation();
			}while(checkIfSame(ADD_random_array, i, ADD_random_array[i]));
		}
		return ADD_random_array;
	}
	
	/*产生减法算术式*/
	public Binary_Operation_Version_1_1[] getSubOperations(int num) {
		SubstractOperation[] SUB_random_array = new SubstractOperation[num];
		for(int i = 0;i < num ;i ++) {
			do {
				SUB_random_array[i] = new SubstractOperation();
			}while(checkIfSame(SUB_random_array, i, SUB_random_array[i]));
		}
		return SUB_random_array;
	}
}
