package softwareConstruction_OOP_IO;

import java.io.File;

public class FirstUse {
	private String path = "D:\\softwareConstructionExperiment\\judgeIfFirst";
	
	//利用文件judgeIfFirstUse.txt是否存在，判断用户是否是第一次进入系统
	public boolean IsFirst() {
		File file = new File(path + "\\judgeIfFirstUse.txt");
		if(file.exists()) {
			return false;
		}else return true;
	}
	
	//如果是第一次进入系统，那么新建judgeIfFirstUse.txt来标识用户不是第一次进入系统
	public void createFirstTxt() {
		File fileFolder = new File(path);
		File file = new File(path + "\\judgeIfFirstUse.txt");
		
		//新建文件夹JudgeIFFirst
		if(fileFolder.exists()) {

		}else {
			try {
				fileFolder.mkdirs();
			}catch(Exception e) {
				System.out.println("异常：" + e.toString());
			}
		}
		//新建文件judge.txt
		if(file.exists()) {
			
		}else {
			try {
				file.createNewFile();
				System.out.println("创建:：judgeIfFirstUse.txt");
			}catch(Exception e) {
				System.out.println("异常：" + e.toString());
			}
		}
	}
}
