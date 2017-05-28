package rect;

import java.awt.Graphics;

/**
 * 该类用来存储方块框小方块坐标及使用情况
 * @author Administrator
 *
 */
public  class RectList {
	/**
	 * 用二维数组来表示游戏方框
	 */
	public static Rect[][] list = new Rect[18][12];
	/**
	 * 标识行是否填满，用作消行判断，true表示可以消行
	 */
	public static boolean  flagLine[] = new boolean[18];
	/**
	 * 表示消行的行数
	 */
	public static int meltLineNum = 0;
	/**
	 * 游戏得分, 一次消一行得10， 一次消两行20， 三行40， 四行80
	 */
	public static int score = 0;
	private RectList(){};
	
	//静态初始化
	static{
		int x = 57;
		int y = 67;
		for(int i=0; i<18; i++){
			//初始化标识行
			flagLine[i] = false;
			for(int j=0; j<12; j++){
				//实例化，初始化坐标, 小方格状态
				list[i][j] = new Rect(x + j * Rect.RECT_SIZE, y + i * Rect.RECT_SIZE, Rect.TYPE_NULL);
				list[i][j].setColorX(0);
				list[i][j].setColorY(0);
			}
		}
	}
	
	/**
	 * 判断是否可以消行
	 * @return 本次要消行的行数
	 */
	private static int isMelt(){
		int num = 18;
		//初始化
		for(int i=0; i<18; i++){
			flagLine[i] = true;
		}
		//判断flagLine的值
		for(int i=0; i<18; i++){
			for(int j=0; j<12; j++){
				//一行中若出现未被使用的方格，该行不可消
				if(list[i][j].getType() == Rect.TYPE_NULL){
					flagLine[i] = false;
					num --;
					break;
				}
			}
		}
		return num ;
	}
	
	/**
	 * 绘制要消行的方块为灰色
	 * @param g
	 */
	private static void drawMeltLine(Graphics g){
		for(int i=0; i<flagLine.length; i++){
			if(flagLine[i] == true){
				for(int j=0; j<12; j++){
					list[i][j].drawRect(g, Rect.TYPE_MELT);
				}
			}
		}
	}
	/**
	 * 绘制当前游戏框所有方块
	 * @param g
	 */
	public static void drawAllLine(Graphics g){
		for(int i=0; i<18; i++){
			for(int j=0; j<12; j++){
				if(list[i][j].getType() != Rect.TYPE_NULL){
					list[i][j].drawRect(g);
				}
			}
		}
	}
	/**
	 * 消行
	 */
	public static void meltLine(Graphics g){
		//获取本次行行数
		int num = isMelt();
		//执行消行
		if(num>0){
			//总消行行数
			meltLineNum += num;
			//更新分数
			if(num==1){
				score += 10;
			}else if(num==2){
				score += 20;
			}else if(num==3){
				score += 40;
			}else if(num==4){
				score += 80;
			}

			//获取消行后的list状态
			for(int i=17; i>=0; i--){
				if(flagLine[i]==true && i!=0){
					int k = i;
					for(int j=k; j>0; j--){
						for(int m=0; m<12; m++){
							list[j][m].setColorX(list[j-1][m].getColorX());
							list[j][m].setColorY(list[j-1][m].getColorY());
							list[j][m].setType(list[j-1][m].getType());
						}
					}
					for(int j=0; j<12; j++){
						list[0][j].setType(Rect.TYPE_NULL);
					}
					isMelt();
					i++;
				}else if(flagLine[i]==true && i==0){
					for(int j=0; j<12; j++){
						list[0][j].setType(Rect.TYPE_NULL);
					}
					flagLine[0] = false;
				}
			}
			//绘制消行有游戏框状态
			drawAllLine(g);
		}
	}
}
