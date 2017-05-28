package rect;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import control.Ctrl;

/**
 * 下落方块类型接口
 * @author 啊角
 *
 */
public abstract class RectType{
	/**
	 * 刚下落时初始化横坐标
	 */
	public static final int INIT_X = 182;
	/**
	 * 刚下落时初始化纵坐标
	 */
	public static final int INIT_Y = 67;
	public static final int MOVE_LEFT = 0;
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_DOWN = 2;
	public static final int MOVE_UP = 3;
	public static List<List<Rect[]>> allChanges = new ArrayList<List<Rect[]>>();
	public List<Rect[]> changes = null;
	public int index;
	
	/**
	 * 下落方块变形
	 * 
	 */
	public abstract void convert();
	/**
	 * 下落方块变形
	 */
	public abstract void up();
	/**
	 * 下落方块加速下落方法
	 */
	public abstract void down();
	/**
	 * 下落方块左移
	 */
	public abstract void left();
	/**
	 * 下落方块右移
	 */
	public abstract void right();
	
	
	
	/**
	 * 初始化方块坐标
	 * @param x
	 * @param y
	 */
	public abstract void init(int x, int y);
	public abstract void init(int x, int y, int colX, List<Rect[]> changes);
	
	public abstract void drawType(Graphics g);
	
	/**
	 * 初始化下落方块颜色
	 * @param changes
	 */
	public void initColor(List<Rect[]> changes, int colX){
		Rect[] temp = null;
		for(int i=0; i<changes.size(); i++){
			temp = changes.get(i);
			for(int j=0; j<temp.length; j++){
				temp[j].setColorX(colX);
				temp[j].setColorY(0);
			}
		}
	}
	
	/**
	 * 根据colX来设置下落方块颜色
	 * @param tp
	 * @param colX
	 */
	public void setAllColor(Rect[] tp , int colX){
		for(int i=0; i<tp.length; i++){
			tp[i].setColorX(colX);
			tp[i].setColorY(0);
		}
	}
	
	/**
	 * 游戏规则
	 * @param rL
	 * @return
	 */
	public   boolean  rule(Rect[] rL, int move){
		for(int i=0; i<rL.length ; i++){
			if(rL[i].getType()==Rect.TYPE_DONE){
				return false;
			}
		}
		//1.不可越过左边界及已存在的方格
		if(move==RectType.MOVE_LEFT){
			//记录改方块（rL）最左边的方格在二维数组中的下标
			int x = 20, y = 20;
			//不可越过左边界
			for(int i=0; i<rL.length; i++){
				if(rL[i].getX() - Rect.RECT_SIZE< 57){
					return false;
				}
			}
			for(int i=0; i<rL.length; i++){
				x = getIndexX(rL[i]); 
				y = getIndexY(rL[i]);
				if(y > 0 && x > 0){
					if(RectList.list[x][y-1].getType()==Rect.TYPE_DONE){
						return false;
					}
				}
			}
		}
		
		//2.不可越过右边界及一存在的方格
		if(move==RectType.MOVE_RIGHT){
			int x = 0, y = 0;
			for(int i=0; i<rL.length; i++){
				if(rL[i].getX() + Rect.RECT_SIZE >= 350){
					return false;
				}
			}
			for(int i=0; i<rL.length; i++){
				x = getIndexX(rL[i]); 
				y = getIndexY(rL[i]);
				if(x > 0 && y > 0){
					if(RectList.list[x][y+1].getType()==Rect.TYPE_DONE){
						return false;
					}
				}
			}
		}
		
		//3.不可越过下边界及不可越过已存在的方格
		if(move==RectType.MOVE_DOWN){
			int x , y ;
			if(rL[0].getType()==Rect.TYPE_DONE){
				return false;
			}
			for(int i=0; i<rL.length; i++){
				if(rL[i].getY() + Rect.RECT_SIZE >= 517){
					save(rL);
					return false;
				}
			}
			
			for(int i=0; i<rL.length; i++){
				x = getIndexX(rL[i]); 
				y = getIndexY(rL[i]);
				if(y >= 0 && x > 0 && x <18 && y < 12){
					if(RectList.list[x+1][y].getType()==Rect.TYPE_DONE){
						if(rL[0].getType()!=Rect.TYPE_DONE){
							save(rL);
						}
						return false;
					}
				}
			}
		}
		
		//4.变形后不可超出边界
		if(move==RectType.MOVE_UP){
			for(int i=0; i<rL.length; i++){
				if(rL[i].getY() < 67){
					return false;
				}
				if(rL[i].getY()>= 517){
					return false;
				}
				if(rL[i].getX() + Rect.RECT_SIZE > 357){
					return false;
				}
				if(rL[i].getX()< 57){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 获取r在二维数组游戏框中的横下标
	 * @param r
	 * @return
	 */
	public static int getIndexY(Rect r){
		int y = 0;
		y = ( r.getX() - 57 ) / 25;
		return y;
	}
	/**
	 * 获取r在二维数组游戏框中的纵下标
	 * @param r
	 * @return
	 */
	public static int getIndexX(Rect r){
		int x = 0;
		x = ( r.getY() - 67 ) / 25;
		return x;
	}
	
	public abstract void setIndex(int index) ;
	
	public  void save(Rect[] rL){
		int x, y;
		for(int j=0; j<rL.length; j++){
			rL[j].setType(Rect.TYPE_DONE);
			x = getIndexX(rL[j]);
			y = getIndexY(rL[j]);
			RectList.list[x][y].setType(Rect.TYPE_DONE);
		}
	}
}
