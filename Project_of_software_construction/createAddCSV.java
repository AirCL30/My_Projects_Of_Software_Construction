package softwareConstruction_OOP_IO;

public class createAddCSV extends CreateCSV {
	//加法习题文件夹的路径
	private String pathAddCSVFolder = "D:\\softwareConstructionExperiment\\exerciseFile\\addCSV";
	//加法习题答案文件夹的路径
	private String pathAddAnswerCSVFolder = "D:\\softwareConstructionExperiment\\answerFile\\addAnswerCSV";
	private String[] addCSVpath = new String[super.getExerciseNum()];
	private String[] addAnswerCSVpath = new String[super.getExerciseNum()];

	public createAddCSV() {
		//初始化加法习题文件夹和加法答案文件夹，检测两种文件夹，无则创建，有则不操作
		initFolder(pathAddCSVFolder);
		initFolder(pathAddAnswerCSVFolder);
		// 为每个加法习题文件和加法答案文件起名
		for (int i = 0; i < super.getExerciseNum(); i++) {
			addCSVpath[i] = "";
			addCSVpath[i] += pathAddCSVFolder + "\\addExercise" + i + ".csv";
			addAnswerCSVpath[i] = "";
			addAnswerCSVpath[i] += pathAddAnswerCSVFolder + "\\addExerciseAnswers" + i + ".csv";
		}
	}

	//返回加法习题文件夹的路径
	public String getFolderPath() {
		return this.pathAddCSVFolder;
	}

	//新建加法习题csv和加法习题答案csv
	public void createCSV() {
		createCSVFile(addCSVpath);
		createCSVFile(addAnswerCSVpath);
	}

	//向每个文件中写入加法算术式和对应的答案
	public void writeAddOperations(int num) {
		for(int i = 0;i <super.getExerciseNum();i ++) {
			Binary_Operation_Version_1_1[] addArray = new Excise_Version_1_1().getAddOperations(num);
			String[] operationsArray = new String[num];
			String[] result = new String[num];
			for(int j = 0;j < num;j ++) {
				new String();
				result[j] = String.valueOf(addArray[j].getResult());
				operationsArray[j] = addArray[j].toString();
			}
			//写入算术式
			writeOperationsOrAnswersToCsv(operationsArray,addCSVpath[i],num,1);
			//写入答案
			writeOperationsOrAnswersToCsv(result,addAnswerCSVpath[i],num,2);
		}
	}
}
