package software_construction_OOP;

public class Excise {
	private int Max_Index = 50;
	private Binary_Operation[] operation_array = new Binary_Operation[Max_Index];
	
	/*向算术式数组填充算术式*/
	public void setOperations() {
		for(int i = 0;i < 50 ;i++) {
			do {
				operation_array[i] = new Binary_Operation();
			}while(checkIfSame(i, operation_array[i]));
		}
	}
	
	/*检查是否存在相同的算术式*/
	private boolean checkIfSame(int EndI,Binary_Operation Now_Operation) {
		for(int i = 0;i < EndI;i ++) {
			if(operation_array[i].getLeft_num() == Now_Operation.getLeft_num() && operation_array[i].getOperator() == Now_Operation.getOperator() && operation_array[i].getRight_num() == Now_Operation.getRight_num()) {
				return true;
			}
		}
		return false;
	}
	
	/*给出50道算术式*/
	public void getOperations() {
		System.out.println("****************************以下是给出的50道算术式*******************************");
		for(int i = 0;i < Max_Index;i ++) {
			System.out.print((i+1) + ": " + operation_array[i].toString() + "\t");
			if((i+1) % 5 == 0) {
				System.out.println();
			}
		}
	}
	
	/*给出算术式的答案*/
	public void getAnswers() {
		System.out.println("                -----------------下面是答案---------------------");
		for(int i = 0;i < Max_Index;i ++) {
			System.out.print((i+1) + ": " + operation_array[i].getResult() + "\t");
			if((i+1) % 5 == 0) {
				System.out.println();
			}
		}
	}
}
