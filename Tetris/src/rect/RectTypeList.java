package rect;

public class RectTypeList implements Runnable {
	public static RectType[] rt = {
		new Type1(), new Type2(), new Type3(),
		new Type4(), new Type5(), new Type6(),
		new Type7()	
	};
	private int randType ;
	private int index;
	public static int time = 400;
	public static int rank = 0;
	public  int flag = 0;
	
	
	@Override
	public void run() {
		while(true){
			
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rt[randType].down();
		}
	}


	public int getRandType() {
		return randType;
	}


	public void setRandType(int randType) {
		this.randType = randType;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}
	
	

}
