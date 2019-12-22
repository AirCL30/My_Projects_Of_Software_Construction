package softwareConstruction_OOP_IO;

public class createAddSubCSV extends CreateCSV {
	//加减法混合习题文件夹的路径
	private String pathAddSubCSVFolder = "D:\\softwareConstructionExperiment\\exerciseFile\\addsubCSV"; 
	//加减法混合习题答案文件夹的路径
	private String pathAddSubAnswerCSVFolder = "D:\\softwareConstructionExperiment\\answerFile\\addsubAnswerCSV"; 
	private String[] addsubCSVpath = new String[super.getExerciseNum()];
	private String[] addsubAnswerCSVpath = new String[super.getExerciseNum()];
	
	public createAddSubCSV() {
		initFolder(pathAddSubCSVFolder);
		initFolder(pathAddSubAnswerCSVFolder);
		//为每个加减法混合习题csv和答案csv起名
		for(int i = 0;i < super.getExerciseNum();i ++) {
			addsubCSVpath[i] = "";
			addsubCSVpath[i] += pathAddSubCSVFolder + "\\addsubExercise" + i + ".csv";
			addsubAnswerCSVpath[i] = "";
			addsubAnswerCSVpath[i] += pathAddSubAnswerCSVFolder + "\\addsubExerciseAnswers" + i + ".csv";
		}
	}
	
	//返回加减法混合习题文件夹的路径
	public String getFolderPath() {
		return this.pathAddSubCSVFolder;
	}
	
	//新建加减法混合习题csv和答案csv
	public void createCSV() {
		createCSVFile(addsubCSVpath);
		createCSVFile(addsubAnswerCSVpath);
	}
	
	public void writeAddSubOperations(int num) {
		for(int i = 0;i <super.getExerciseNum();i ++) {
			Binary_Operation_Version_1_1[] addsubArray = new Excise_Version_1_1().getSomeNumProblems(num);
			String[] operationsArray = new String[num];
			String[] result = new String[num];
			for(int j = 0;j < num;j ++) {
				new String();
				result[j] = String.valueOf(addsubArray[j].getResult());
				operationsArray[j] = addsubArray[j].toString();
			}
			//写入算术式
			writeOperationsOrAnswersToCsv(operationsArray,addsubCSVpath[i],num,1);
			//写入答案
			writeOperationsOrAnswersToCsv(result,addsubAnswerCSVpath[i],num,2);
		}
	}
}
