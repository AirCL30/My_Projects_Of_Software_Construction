package softwareConstruction_OOP_IO;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;

public class Judger {
	//习题文件夹路径、答案文件夹路径、练习文件夹路径
	private String exerciseFolderPath = "D:\\softwareConstructionExperiment\\exerciseFile";
	private String answerFolderPath = "D:\\softwareConstructionExperiment\\answerFile";
	private String practiceFolderPath = "D:\\softwareConstructionExperiment\\userPracticeFile";
	/*设置三个List
	 * completeFilePathList用来存放已完成的习题的路径
	 * exerciseFilePathList用来存放已完成的习题对应的习题的路径
	 * answerFilePathList用来存放已完成的习题的答案的路径
	 				*/
	private ArrayList<String> completeFilePathList = new ArrayList<String>();
	private ArrayList<String> exerciseFilePathList = new ArrayList<String>();
	private ArrayList<String> answerFilePathList = new ArrayList<String>();
	private Scanner cin = new Scanner(System.in);
	
	//检查此练习文件是否完成
	private boolean checkCompletePractice(String path) {
		ArrayList<String[]> problemList = new ArrayList<String[]>();
		//cvs文件读入部分
		try {
			File userPracticeFile = new File(path);
			if(!userPracticeFile.exists()) {
				return false;
			}
			CsvReader reader = new CsvReader(path,',',Charset.forName("gb2312"));
			while(reader.readRecord()){
				problemList.add(reader.getValues());
			}
			reader.close();
			/*如果csv的头部是“未完成”，则是未完成，返回false
			 * 如果csv的头部是“已完成”，则是未完成，返回true
			 */
			if(problemList.get(0)[0].toString().equals("未完成")) {
				return false;
			}else if(problemList.get(0)[0].toString().equals("已完成")) {
				this.completeFilePathList.add(path);
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}
		//清空list
		problemList.clear();
		return true;
	}
	
	//批阅已完成的练习文件
	private void juderMyPractice(int chose) {
		ArrayList<String[]> problemList = new ArrayList<String[]>();
		ArrayList<String[]> answerList = new ArrayList<String[]>();
		System.out.println("题目如下：");
		//读出练习文件对应的习题集
		new ReadMyCsv().readCsv(exerciseFilePathList.get(chose-1));
		//读练习文件.csv
		try {
			CsvReader reader = new CsvReader(completeFilePathList.get(chose-1).toString(),',',Charset.forName("gb2312"));
			reader.readHeaders();
			while(reader.readRecord()){
				problemList.add(reader.getValues());
			}
			reader.close();
			System.out.println("\n你的答案为：");
			int k = 1;
			//显示自己的答案
			for(int row = 0 ; row < problemList.size(); row ++ ){
				for(int col=0;col<problemList.get(row).length;col++){
				String cell = problemList.get(row)[col];
				if(!cell.equals("")) {
					System.out.print(k++ + " : " + cell + "\t\t");
				}
				}
				System.out.println();
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}
		//读对应的习题答案文件.csv
		try {
			CsvReader reader = new CsvReader(answerFilePathList.get(chose-1).toString(),',',Charset.forName("gb2312"));
			reader.readHeaders();
			while(reader.readRecord()){
				answerList.add(reader.getValues());
			}
			reader.close();
			System.out.println("\n正确答案为：");
			int k = 1;
			//显示正确答案
			for(int row = 0 ; row < answerList.size(); row ++ ){
				for(int col=0;col<answerList.get(row).length;col++){
				String cell = answerList.get(row)[col];
				if(!cell.equals("")) {
					System.out.print(k++ + " : " + cell + "\t\t");
				}
				}
				System.out.println();
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}
		
		int trueFlag = 0;
		int falseFlag = 0;
		boolean noMistake = true;
		int[] falseNum = new int[50]; 
		int countK = 0;
		//对比用户的答案和正确答案
		for(int row = 0 ; row < answerList.size(); row ++ ) {
			for(int col=0;col<answerList.get(row).length;col++) {
				countK++;
				if(problemList.get(row)[col].toString().equals(answerList.get(row)[col].toString())) {
					trueFlag++;
				}
				else {
					falseNum[falseFlag++] = countK;
					noMistake = false;
				}
			}
		}
		//显示批阅结果
		System.out.println("\n正确 : " + trueFlag + "\n错误 : " + falseFlag);
		if(noMistake) {
			//全部正确的情况
			System.out.println("全部正确，干得漂亮！");
		}else {
			//如果出现错误的情况，显示错误的题号
			System.out.println("你出错的题号为：");
			for(int i = 0;i < falseFlag;i ++) {
				System.out.print(falseNum[i]);
				if(i < falseFlag - 1) {
					System.out.print("、");
				}
			}
		}
	}
	
	//向用户显示用户已经完成的习题，让用户选择想要查看的批阅情况
	public void showCompletePractice() {
		System.out.println("以下是你已经完成的习题集：");
		int k = 1;
		//根据路径，从练习文件的0到49，检查是否有已完成的练习，如果有就存入对应的List
		for(int i = 0;i < 50 ;i ++) {
			if(checkCompletePractice(practiceFolderPath + "\\addPractice\\userPracticeAdd" + i + ".csv")) {
				exerciseFilePathList.add(exerciseFolderPath + "\\addCSV\\addExercise" + i + ".csv");
				answerFilePathList.add(answerFolderPath + "\\addAnswerCSV\\addExerciseAnswers" + i + ".csv");
				System.out.println(k++ + " --- addExcise" + i + ".csv");
			}
		}
		for(int i = 0;i < 50 ;i ++) {
			if(checkCompletePractice(practiceFolderPath + "\\subPractice\\userPracticeSub" + i + ".csv")) {
				exerciseFilePathList.add(exerciseFolderPath + "\\subCSV\\subExercise" + i + ".csv");
				answerFilePathList.add(answerFolderPath + "\\subAnswerCSV\\subExerciseAnswers" + i + ".csv");
				System.out.println(k++ + " --- subExcise" + i + ".csv");
			}
		}
		for(int i = 0;i < 50 ;i ++) {
			if(checkCompletePractice(practiceFolderPath + "\\addsubPractice\\userPracticeAddSub" + i + ".csv")) {
				exerciseFilePathList.add(exerciseFolderPath + "\\addsubCSV\\addsubExercise" + i + ".csv");
				answerFilePathList.add(answerFolderPath + "\\addsubAnswerCSV\\addsubExerciseAnswers" + i + ".csv");
				System.out.println(k++ + " --- addsubExcise" + i + ".csv");
			}
		}
		if(k == 1) {
			System.out.println("你暂时没有已经完成的练习！");
		}else {
			System.out.println("\n请选择你想查看的习题：");
			juderMyPractice(cin.nextInt());
		}
	}
}
