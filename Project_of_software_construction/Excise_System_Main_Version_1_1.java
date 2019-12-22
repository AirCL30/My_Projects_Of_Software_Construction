package softwareConstruction_OOP_IO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

public class Excise_System_Main_Version_1_1 {
	private int problem_Kind = 0;
	private int problem_Num = 0;
//	private char ch;
//	private int ColNum = 0;
	private int upLine = 100;
	private int downLine = 0;
	private Scanner cin = new Scanner(System.in);

	/* 欢迎界面 */
	public void display_welcome() {
		System.out.println(
				"                  Welcome to iCounter !\nIntroduction：\n         Hello! 我是一个会出题的小机器人哦！在接下来的日子里，每天我都会为你出3套题目");
		System.out.println("                ,你可以选择其中的一套进行练习，我会为你的练习进行评分，赶快来试试吧！");
		FirstUse first = new FirstUse();
		// 判断用户是否为第一次使用系统，如果是第一次使用，让用户设置算术式的上下限和每套题目的题目数量
		// 如果不是第一次使用，那么直接进入系统
		if (first.IsFirst()) {
			System.out.println("\n第一次使用我，请为我设置上下限，我会为你限定算术式的范围哦！（以后还可以更改）");
			first.createFirstTxt();
			inputUpDownLine(0);
			display_chose_type();
		} else {
			System.out.println("\n欢迎再次使用我哦！");
			System.out.println(
					"1--出3套题目（按照已设定的上下限出题）\n2--继续之前的练习（可继续所有未完成的练习）\n3--查看批阅情况（查看已经完成的所有习题批阅情况）\n4--刷新题库（注意：重新设置题库题目的上下限，但是之前的记录会全部消失！）\n请输入:");
			int chose = cin.nextInt();
			notFirst(chose);
		}
	}

	/* 用户不是第一次进入系统时的操作 */
	public void notFirst(int chose) {
		switch (chose) {
		// 为用户出3套题，让其从中选择一套进行练习
		case 1:
			display_chose_type();
			break;
		// 用户选择进入之前为完成的练习
		case 2:
			goOnPractice();
			break;
		// 用户查看之前完成的练习的批阅情况
		case 3:
			Judger juder = new Judger();
			juder.showCompletePractice();
			try {
				clear();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		// 刷新题库，所有记录会完全消失
		case 4:
//			deleteDir("D:\\softwareConstructionExperiment\\userPracticeFile");
			deleteFiles(new File("D:\\softwareConstructionExperiment\\userPracticeFile"));
			inputUpDownLine(1);
			break;
		case 0:break;
		}
	}

	/* 输入上下限 */
	private void inputUpDownLine(int flagShowNot) {
		do {
			System.out.println("请输入：\n       上限：");
			this.upLine = cin.nextInt();
			if (this.upLine < 0) {
				System.out.println("上限不可以为负数哦！请重新输入一遍吧！");
			} else
				break;
		} while (true);
		do {
			System.out.println("       下限：");
			this.downLine = cin.nextInt();
			if (this.downLine >= this.upLine) {
				System.out.println("别闹！下限应该比上限小才是！再输一遍吧");
			} else
				break;
		} while (true);
		// 设置上下限
		UpandDownLine updown = new UpandDownLine();
		updown.setUpLine(upLine);
		updown.setDownLine(downLine);
		System.out.println("好的，上下限设置好啦！");
		if (flagShowNot == 0) {
			giveProblems(0);
		} else {
			giveProblems(1);
		}
	}

	// 后台生成练习题集
	public void giveProblems(int flagShowNot) {
		// 输入每套习题的题目数量
		System.out.println("那么下面请输入您需要的题目数：");
		while (true) {
			problem_Num = cin.nextInt();
			if (problem_Num <= 0) {
				System.out.println("题目数量必须大于等于0！请重新输入：");
			} else if (problem_Num % 5 != 0) {
				System.out.println("题目数量必须为5的倍数！请重新输入：");
			} else {
				break;
			}
		}
		// flagShowNot用来标识是第一次使用题目出题还是刷新题库
		if (flagShowNot == 0) {
			System.out.println("出题中......");
		} else {
			System.out.println("刷新中......");
		}
		// 根据设定的上下限和题目数量出题
		createAddCSV add = new createAddCSV();
		createSubCSV sub = new createSubCSV();
		createAddSubCSV add_and_sub = new createAddSubCSV();
		add.createCSV();
		sub.createCSV();
		add_and_sub.createCSV();
		add.writeAddOperations(problem_Num);
		sub.writeSubOperations(problem_Num);
		add_and_sub.writeAddSubOperations(problem_Num);
		// flagShowNot用来标识是第一次使用题目出题还是刷新题库
		if (flagShowNot == 0) {
			System.out.println("出题完成！");
		} else {
			System.out.println("刷新完成！");
		}
		try {
			clear();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 根据已经出好的题目选择题目类型 */
	private void display_chose_type() {
		System.out.println("\n我已经按照你给定的上下限和题目数量为你准备好了3套题目，那么接下来，请选择一套来练习吧！\n1--加法算术套题\n2--减法算术套题\n3--加减法混合算术套题\n请输入：");
		problem_Kind = cin.nextInt();
		if (problem_Kind == 1) {
			// 选择加法题目
			interface_of_problems(1);
		} else if (problem_Kind == 2) {
			// 选择减法题目
			interface_of_problems(2);
		} else if (problem_Kind == 3) {
			// 选择加减混合题目
			interface_of_problems(3);
		} else {
			System.out.println("输入有误，请重新输入!");
		}
	}

	/* 题目显示界面 */
	public void interface_of_problems(int flag) {
		Practice p = new Practice();
		System.out.println("获取题目中......\n");
		System.out.println("获取完成!\n");
		if (flag == 1) {
			// 显示加法题目
			p.readAddCsv();
			p.goPractice(1);
		} else if (flag == 2) {
			// 显示减法题目
			p.readSubCsv();
			p.goPractice(2);
		} else if (flag == 3) {
			// 显示加减法混合题目
			p.readAddSubCsv();
			p.goPractice(3);
		}
		try {
			clear();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户选择继续之前的练习
	public void goOnPractice() {
		Practice p = new Practice();
		// 检查是否存在未完成的练习
		// 如存在，打开未完成的题目
		// 若不存在，告知用户
		if (p.checkPracticeFile()) {
			p.showNotComplete();
		} else {
			System.out.println("你暂时还没有过练习，赶快选择一套题目试试吧！");
		}
		try {
			clear();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 删除用户的全部练习文件
	// 原因：在用户选择刷新题库时，系统会将之前的练习文件全部删除。由于重新出题会覆盖习题文件和答案文件
	private void deleteDir(String dirPath) {
		File file = new File(dirPath);
		System.out.println("删除所有练习文件");
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			if (files == null) {
				file.delete();
			} else {
				for (int i = 0; i < files.length; i++) {
					deleteDir(files[i].getAbsolutePath());
				}
				file.delete();
			}
		}
	}
 	private void deleteFiles(File srcFile) {
 		System.out.println("删除文件中......");
 		//因为虚拟机可能短时间内没有释放文件资源，所以在这里利用三重循环来做延时，在延时时间内尽量让虚拟机释放资源
 		//file.delete才会有效
		for(int i = 0;i < 99999;i ++) {
			for(int j = 0;j < 99999;j ++) {
				for(int k = 0;k < 99999;k ++) {
				}
			}
		}
		//通知虚拟机，进行垃圾回收
 		for(int i = 0;i < 10;i ++) {
 			System.gc();
 		}
 		System.out.println("已删除所有练习文件");
        if (srcFile.exists()) {

            //存放文件夹
            LinkedList<File> directories = new LinkedList<>();
            ArrayList<File> directoryList = new ArrayList<>();

            do {
                File[] files = directories.size() > 0 ? directories.removeFirst().listFiles() : new File[]{srcFile};
                if (files != null) {
                    for (File f : files) {
                        if (f.isDirectory()) {
                            directories.add(f);
                            directoryList.add(f);
                        } else {
                            f.delete();
                        }
                    }
                }
            } while (directories.size() > 0);

            //这时所有非文件夹都删光了，可以直接删文件夹了(注意从后往前遍历)
            for (int i = directoryList.size() - 1; i >= 0; i--) {
                directoryList.get(i).delete();
            }
        }
    }


	// 清空控制台
	private void clear() throws AWTException {
		System.out.println("\n\n1 --- 回到主界面\n请输入：");
		int backNum = 1;
		backNum = cin.nextInt();
		while (backNum != 1) {
			System.out.println("请再次输入：");
			backNum = cin.nextInt();
		}
		//模拟鼠标和键盘事件来完成清空控制台
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON3_MASK); // 按下鼠标右键
		r.mouseRelease(InputEvent.BUTTON3_MASK); // 释放鼠标右键
		r.keyPress(KeyEvent.VK_CONTROL); // 按下Ctrl键
		r.keyPress(KeyEvent.VK_R); // 按下R键
		r.keyRelease(KeyEvent.VK_R); // 释放R键
		r.keyRelease(KeyEvent.VK_CONTROL); // 释放Ctrl键
		r.delay(100);
		display_welcome();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Excise_System_Main_Version_1_1 mainSystem = new Excise_System_Main_Version_1_1();
		mainSystem.display_welcome();
	}
}
