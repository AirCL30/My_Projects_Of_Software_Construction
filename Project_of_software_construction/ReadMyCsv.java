package softwareConstruction_OOP_IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import com.csvreader.CsvReader;

public class ReadMyCsv {
	private int randomOne = new Random().nextInt(50);
	private String addPath = new createAddCSV().getFolderPath() + "\\addExercise";
	private String subPath = new createSubCSV().getFolderPath() + "\\subExercise";
	private String subaddPath = new createAddSubCSV().getFolderPath() + "\\addsubExercise";
	private ArrayList<String[]> problemList = new ArrayList<String[]>();
	private int k = 0;
	
	//读取未完成练习文件的当前练习题目题号，继续完成练习将会从此题号的下一题进行
	public int getCount(String rootPath) {
			int count = 0;
			problemList.clear();
			CsvReader reader = null;
			try {
				reader = new CsvReader((rootPath),',',Charset.forName("gb2312"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				reader.readHeaders();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(reader.readRecord()){
					//System.out.println(reader.getRawRecord());
					problemList.add(reader.getValues());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			reader.close();			
			for(int row = 0 ; row < problemList.size(); row ++ ){
				for(int col=0;col<problemList.get(row).length;col++){
					String cell = problemList.get(row)[col];
					if(cell.equals("")) {
						continue;
					}
					count++;
				}
			}
			return count;	
	}

	//根据路径读出CSV
	public void readCsv(String rootPath) {
		try {
			problemList.clear();
			CsvReader reader = new CsvReader((rootPath), ',', Charset.forName("gb2312"));
			reader.readHeaders();
			while (reader.readRecord()) {
				// System.out.println(reader.getRawRecord());
				problemList.add(reader.getValues());
			}

			reader.close();
			for (int row = 0; row < problemList.size(); row++) {
				for (int col = 0; col < problemList.get(row).length; col++) {
					String cell = problemList.get(row)[col];
					if (cell.equals("")) {
						continue;
					}
					System.out.print(++k + ": " + cell + "\t\t");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获取RandomOne
	public int getRandomOne() {
		return randomOne;
	}

	public int getK() {
		return k;
	}

	public void readAddCsv() {
		System.out.println("文件名：" + ("addExercise" + randomOne + ".csv"));
		readCsv(addPath + randomOne + ".csv");
	}

	public void readSubCsv() {
		System.out.println("文件名：" + ("subExercise" + randomOne + ".csv"));
		readCsv(subPath + randomOne + ".csv");
	}

	public void readAddSubCsv() {
		System.out.println("文件名：" + ("addsubExercise" + randomOne + ".csv"));
		readCsv(subaddPath + randomOne + ".csv");
	}
}
