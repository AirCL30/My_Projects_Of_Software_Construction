package softwareConstruction_OOP_IO;

import java.io.File;
import java.nio.charset.Charset;

import com.csvreader.CsvWriter;

public class CreateCSV {
	//默认习题集套数 50
	private static int exerciseNum = 50;

	//修改习题集套数exerciseNum
	public void setExerciseNum(int num) {
		exerciseNum = num;
	}
	//获取习题集套数exerciseNum
	public int getExerciseNum() {
		return exerciseNum;
	}

	//初始化习题集所在的文件夹，若其不存在，则新建
	public void initFolder(String folderPath) {
		// 判断pathCSV文件夹是否存在，如若不存在则创建一个新的文件夹
		File file = new File(folderPath);
		if (file.exists()) {

		} else {
			try {
				file.mkdirs();
				System.out.println("检测到文件夹" + folderPath + "不存在，已为您新建！");
			} catch (Exception e) {
				System.out.println("异常：" + e.toString());
			}
		}
	}

	// 添加exerciseNum套习题集文件.csv
	protected void createCSVFile(String[] path) {
		File csvFile;
		for (int i = 0; i < exerciseNum; i++) {
			csvFile = new File(path[i]);
			if (csvFile.exists()) {
			} else {
				try {
					csvFile.createNewFile();
//					System.out.println("已创建:" + path[i]);
				} catch (Exception e) {
					System.out.println("异常：" + e.toString());
				}
			}

		}
	}
	
	//向每一个csv中写入习题
	public void writeOperationsOrAnswersToCsv(String[] stringArray, String csvPath, int n, int flag) {
		// cvs文件写入部分
		try {
			String[] header = new String[5];
			//写入答案和写入算术式复用此方法，用flag来标识，1代表习题集，2代表答案
			//不同的类型写入不同的首部
			if(flag == 1) {
				header[0] = "习题集";
			}else {
				header[0] = "习题答案";
			}
			String[][] strArray = new String[n][5];
			int k = 0,i = 0;
			for (; i < n; i++) {
				if(k > n-1) {
					break;
				}
				for (int j = 0; j < 5; j++) {
					if(k > n-1) {
						break;
					}
					strArray[i][j] = stringArray[k++];
//					System.out.println("k:" + k + " "+ strArray[i][j]);
				}
			}
			CsvWriter csvWriter = new CsvWriter(csvPath, ',', Charset.forName("gb2312"));
			csvWriter.writeRecord(header);
			for (int otheri = 0; otheri < i; otheri++) {
				String[] csvContent = strArray[otheri];
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
//			System.out.println(csvPath + "--------------------已完成写入操作--------------");
		} catch (Exception e) {
			System.out.println("异常：" + e.toString());
		}
	}
}
