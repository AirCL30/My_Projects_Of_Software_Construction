package softwareConstruction_OOP_IO;

public class createSubCSV extends CreateCSV {
	//减法习题文件夹的路径
	private String pathSubCSVFolder = "D:\\softwareConstructionExperiment\\exerciseFile\\subCSV";
	//减法习题答案文件夹的路径
	private String pathSubAnswerCSVFolder = "D:\\softwareConstructionExperiment\\answerFile\\subAnswerCSV"; 
	private String[] subCSVpath = new String[super.getExerciseNum()];
	private String[] subAnswerCSVpath = new String[super.getExerciseNum()];
	
	public createSubCSV() {
		initFolder(pathSubCSVFolder);
		initFolder(pathSubAnswerCSVFolder);
		//为每个减法习题csv和答案csv起名
		for(int i = 0;i < super.getExerciseNum();i ++) {
			subCSVpath[i] = "";
			subCSVpath[i] += pathSubCSVFolder + "\\subExercise" + i + ".csv";
			subAnswerCSVpath[i] = "";
			subAnswerCSVpath[i] += pathSubAnswerCSVFolder + "\\subExerciseAnswers" + i + ".csv";
		}
	}
	
	//返回减法习题文件夹的路径
	public String getFolderPath() {
		return this.pathSubCSVFolder;
	}
	
	//新建减法习题csv和答案csv
	public void createCSV() {
		createCSVFile(subCSVpath);
		createCSVFile(subAnswerCSVpath);
	}
	
	//向文件中写入减法习题和对应的答案
	public void writeSubOperations(int num) {
		for(int i = 0;i <super.getExerciseNum();i ++) {
			Binary_Operation_Version_1_1[] subArray = new Excise_Version_1_1().getSubOperations(num);
			String[] operationsArray = new String[num];
			String[] result = new String[num];
			for(int j = 0;j < num;j ++) {
				new String();
				result[j] = String.valueOf(subArray[j].getResult());
				operationsArray[j] = subArray[j].toString();
			}
			//写入减法习题算术式
			writeOperationsOrAnswersToCsv(operationsArray,subCSVpath[i],num,1);
			//写入答案
			writeOperationsOrAnswersToCsv(result,subAnswerCSVpath[i],num,2);
		}
	}
}
