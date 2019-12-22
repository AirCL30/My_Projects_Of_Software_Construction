package softwareConstruction_OOP_IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Practice extends ReadMyCsv {
	private String practiceAddFolder = "D:\\softwareConstructionExperiment\\userPracticeFile\\addPractice";
	private String practiceSubFolder = "D:\\softwareConstructionExperiment\\userPracticeFile\\subPractice";
	private String practiceAddSubFolder = "D:\\softwareConstructionExperiment\\userPracticeFile\\addsubPractice";
	private String practiceFileName = "userPractice";
	private String oneFilePath = "D:\\softwareConstructionExperiment\\exerciseFile\\addCSV\\addExercise0.csv";
	private String[] header;
	private String[][] strUserAnswerArray;
	private Scanner cin = new Scanner(System.in);

	//检查用户是否存在练习文件
	public boolean checkPracticeFile() {
		//如果加法练习文件夹、减法练习文件夹和加减法混合练习文件夹都不存在，说明用户还未进行过练习，那么直接返回false
		if ((!new File(practiceAddFolder).exists()) && (!new File(practiceSubFolder).exists())
				&& (!new File(practiceAddSubFolder).exists())) {
			return false;
		}
		//逐个习题文件检查，若存在则表示有练习文件，返回true
		for (int i = 0; i < 50; i++) {
			if (new File(practiceAddFolder + "\\" + practiceFileName + "Add" + i + ".csv").exists()) {
				return true;
			}
		}
		for (int i = 0; i < 50; i++) {
			if (new File(practiceSubFolder + "\\" + practiceFileName + "Sub" + i + ".csv").exists()) {
				return true;
			}
		}
		for (int i = 0; i < 50; i++) {
			if (new File(practiceAddSubFolder + "\\" + practiceFileName + "AddSub" + i + ".csv").exists()) {
				return true;
			}
		}
		return false;
	}

	//新建练习文件夹
	private void createFolder(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	//新建练习文件
	private void createFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//用户输入答案
	private void userWriteAnswer(String csvPath) {
		//用header来标识用户是否已经写完
		header = new String[2];
		header[0] = "已完成";
		strUserAnswerArray = new String[super.getK()][5];
		System.out.println("\n请输入答案（若您想中途退出，请输入end）：");
		String out;
		int h = 0, l = 0;
		for (int i = 0; i < super.getK(); i++) {
			System.out.print((i + 1) + ": ");
			out = cin.nextLine();
			//如果用户想中途退出，输入end即可退出，之前的内容会全部保存
			if (out.equals("end")) {
				//如若中途退出，header将会为“未完成”
				header[0] = "未完成";
				new String();
				header[1] = String.valueOf(super.getRandomOne());
				break;
			} else {
				if (l == 5) {
					l = 0;
					h++;
				}
				strUserAnswerArray[h][l++] = out;
			}
		}
		try {
			CsvWriter csvWriter = new CsvWriter(csvPath, ',', Charset.forName("gb2312"));
			csvWriter.writeRecord(header);
			if(h == 0 && l == 0) {
				
			}else {
				//将用户写的答案存入对应的练习文件中
				for (int otheri = 0; otheri < h + 1; otheri++) {
					String[] csvContent = strUserAnswerArray[otheri];
					csvWriter.writeRecord(csvContent);
				}
			}
			csvWriter.close();
			System.out.println(csvPath + "--------------------已完成写入操作--------------");
		} catch (Exception e) {
			System.out.println("异常：" + e.toString());
		}
	}

	//开始练习
	public void goPractice(int flag) {
		String filePath;
		if (flag == 1) {
			/*写习题
			 * 创建加法练习文件夹
			 * 创建对应的练习文件
			 * 写入答案
			 * */
			filePath = practiceAddFolder + "\\" + practiceFileName + "Add" + super.getRandomOne() + ".csv";
			createFolder(practiceAddFolder);
			createFile(filePath);
			userWriteAnswer(filePath);
		} else if (flag == 2) {
			filePath = practiceSubFolder + "\\" + practiceFileName + "Sub" + super.getRandomOne() + ".csv";
			createFolder(practiceSubFolder);
			createFile(filePath);
			userWriteAnswer(filePath);
		} else if (flag == 3) {
			filePath = practiceAddSubFolder + "\\" + practiceFileName + "AddSub" + super.getRandomOne() + ".csv";
			createFolder(practiceAddSubFolder);
			createFile(filePath);
			userWriteAnswer(filePath);
		}
	}

	//检查练习文件是否为已完成文件
	private boolean isNotComplete(String filePath) {
		ArrayList<String[]> flagList = new ArrayList<String[]>();
		CsvReader reader = null;
		try {
			reader = new CsvReader(filePath, ',', Charset.forName("gb2312"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (reader.readRecord()) {
				// System.out.println(reader.getRawRecord());
				flagList.add(reader.getValues());
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//比对练习文件的第一行，若为“未完成”，返回true，否则返回false
		if ((flagList.get(0)[0].toString()).equals("未完成")) {
			flagList.clear();
			return true;
		} else {
			flagList.clear();
			return false;
		}
	}

	//打开一个未完成的习题文件来继续练习
	private void openNotComplete(String path,String exercisePath) {
		System.out.println("获取题目中......\n\n获取完成！");
		//读出未完成练习对应的习题
		new ReadMyCsv().readCsv(exercisePath);
		System.out.println("\n已完成：");
		//读出未完成的练习文件写到哪道题了，接下来要从下一道往下继续
		super.readCsv(path);
		System.out.println("\n继续完成：");
		int k = super.getK();
		int count = super.getCount(oneFilePath);
		ArrayList<String[]> goOnList = new ArrayList<String[]>();
		int col = 0;
		try {
			CsvReader reader = new CsvReader(path,',',Charset.forName("gb2312"));
			reader.readHeaders();
			while(reader.readRecord()){
				goOnList.add(reader.getValues());
			}
			reader.close();
		} catch (IOException  e) {
			e.printStackTrace();
		}
		String[] oneStrArray = new String[5];
		boolean flagIsComplete = true;
		//判断当前是否为5的倍数
		if(k % 5 == 0) {
			int flag = k + 1;
			int flagNum = 0;
			String out;
			while(true) {
				if(flag > count) {
					break;	
				}
				System.out.print(flag + " :");
				out  = cin.next();
				out  += cin.nextLine();
				if(out.equals("end")) {
					flagIsComplete = false;
					if(flag % 5 != 1) {
						goOnList.add(oneStrArray);
					}
					break;
				}
				oneStrArray[flagNum++] = out;
				flag++;
				if(flagNum == 5) {
					goOnList.add(oneStrArray);
					oneStrArray = new String[5];
					flagNum = 0;
				}
			}
		}
		else {
			int nowRow = 10000;
			int nowCol = 10000;
			String out = "";
			for(int row = 0;row < goOnList.size();row ++) {
				for(col=0;col<goOnList.get(row).length;col++){
					if(goOnList.get(row)[col].toString().equals("")) {
						nowRow = row;
						nowCol = col;
						break;
					}
				}
			}
			int countK = super.getK();
			for(int i = 0;i < nowCol;i ++) {
				oneStrArray[i] = goOnList.get(nowRow)[i].toString();
			}
			for(int i = nowCol;i < 5;i ++) {
				System.out.print((countK++ + 1) + " :");
				out = cin.next();
				out += cin.nextLine();
				if(out.equals("end")) {
					flagIsComplete = false;
					break;
				}
				oneStrArray[i] = out;
			}
			goOnList.remove(nowRow);
			goOnList.add(oneStrArray);
			int flag = (nowRow + 1) * 5 + 1;
			int flagNum = 0;
			oneStrArray = new String[5];
			while(true) {
				if(out.equals("end")) {
					flagIsComplete = false;
					break;
				}
				if(flag > count) {
					break;	
				}
				System.out.print(flag + " :");
				out = cin.next();
				out += cin.nextLine();
				if(out.equals("end")) {
					flagIsComplete = false;
					break;
				}
				oneStrArray[flagNum++] = out;
				flag++;
				if(flagNum == 5) {
					goOnList.add(oneStrArray);
					oneStrArray = new String[5];
					flagNum = 0;
				}
			}
		}
		//cvs文件写入部分
		try{
			header = new String[2];
			if(flagIsComplete) {
				header[0] = "已完成";
			}else {
				header[0] = "未完成";
			}
			CsvWriter csvWriter = new CsvWriter(path,',',Charset.forName("gb2312"));
			csvWriter.writeRecord(header);
			for(int i=0;i<goOnList.size();i++){
				String[] csvContent = goOnList.get(i);
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
			System.out.println("--------------------已完成写入操作--------------");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		new ReadMyCsv().readCsv(path);
	}
	
	//显示未完成的练习
	public void showNotComplete() {
		String filePath;
		boolean flag = true;
		ArrayList<String> notCompleteFilePathList = new ArrayList<String>();
		ArrayList<String> notCompleteExerciseFilePathList = new ArrayList<String>();
		//逐个检查，每个练习文件是否存在，若存在，检查头部是否为“已完成”，如果为“已完成”，将其路径存入对应的List
		for (int i = 0; i < 50; i++) {
			filePath = practiceAddFolder + "\\" + practiceFileName + "Add" + i + ".csv";
			if (new File(filePath).exists()) {
				if(isNotComplete(filePath)) {
					notCompleteFilePathList.add(filePath);
					notCompleteExerciseFilePathList.add("D:\\softwareConstructionExperiment\\exerciseFile\\addCSV\\addExercise" + i + ".csv");
					System.out.println(notCompleteFilePathList.size() + ": 未完成：加法习题集 -- " + i);
					flag = false;
				}
			}
		}
		for (int i = 0; i < 50; i++) {
			filePath = practiceSubFolder + "\\" + practiceFileName + "Sub" + i + ".csv";
			if (new File(filePath).exists()) {
				if(isNotComplete(filePath)) {
					notCompleteFilePathList.add(filePath);
					notCompleteExerciseFilePathList.add("D:\\softwareConstructionExperiment\\exerciseFile\\subCSV\\subExercise" + i + ".csv");
					System.out.println(notCompleteFilePathList.size() + ": 未完成：减法习题集 -- " + i);
					flag = false;
				}
			}
		}
		for (int i = 0; i < 50; i++) {
			filePath = practiceAddSubFolder + "\\" + practiceFileName + "AddSub" + i + ".csv";
			if (new File(filePath).exists()) {
				if(isNotComplete(filePath)) {
					notCompleteFilePathList.add(filePath);
					notCompleteExerciseFilePathList.add("D:\\softwareConstructionExperiment\\exerciseFile\\addsubCSV\\addsubExercise" + i + ".csv");
					System.out.println(notCompleteFilePathList.size() + ": 未完成：加减法混合习题集 -- " + i);
					flag = false;
				}
			}
		}
		if(flag) {
			System.out.println("\n你暂时没有未完成的练习");
		}else {
			System.out.println("\n请选择你想继续完成的习题集：");
			int chose = cin.nextInt();
			openNotComplete(notCompleteFilePathList.get(chose-1).toString(),notCompleteExerciseFilePathList.get(chose-1).toString());
		}
	}
}