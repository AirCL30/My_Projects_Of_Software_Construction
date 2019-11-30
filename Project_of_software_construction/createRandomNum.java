package software_construction_OOP_version_1_1;

import java.util.Random;

public class createRandomNum {
	private int ramdomNum;
	
	public int getRandomNum() {
		Random random = new Random();
		//通过算术式的上下限来产生随机数
		UpandDownLine upDown = new UpandDownLine();
		this.ramdomNum = random.nextInt(upDown.getUpLine()-upDown.getDownLine())+upDown.getDownLine();
		return this.ramdomNum;
	}
}
