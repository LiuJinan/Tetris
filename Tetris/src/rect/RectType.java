package rect;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import control.Ctrl;

/**
 * ���䷽�����ͽӿ�
 * @author ����
 *
 */
public abstract class RectType{
	/**
	 * ������ʱ��ʼ��������
	 */
	public static final int INIT_X = 182;
	/**
	 * ������ʱ��ʼ��������
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
	 * ���䷽�����
	 * 
	 */
	public abstract void convert();
	/**
	 * ���䷽�����
	 */
	public abstract void up();
	/**
	 * ���䷽��������䷽��
	 */
	public abstract void down();
	/**
	 * ���䷽������
	 */
	public abstract void left();
	/**
	 * ���䷽������
	 */
	public abstract void right();
	
	
	
	/**
	 * ��ʼ����������
	 * @param x
	 * @param y
	 */
	public abstract void init(int x, int y);
	public abstract void init(int x, int y, int colX, List<Rect[]> changes);
	
	public abstract void drawType(Graphics g);
	
	/**
	 * ��ʼ�����䷽����ɫ
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
	 * ����colX���������䷽����ɫ
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
	 * ��Ϸ����
	 * @param rL
	 * @return
	 */
	public   boolean  rule(Rect[] rL, int move){
		for(int i=0; i<rL.length ; i++){
			if(rL[i].getType()==Rect.TYPE_DONE){
				return false;
			}
		}
		//1.����Խ����߽缰�Ѵ��ڵķ���
		if(move==RectType.MOVE_LEFT){
			//��¼�ķ��飨rL������ߵķ����ڶ�ά�����е��±�
			int x = 20, y = 20;
			//����Խ����߽�
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
		
		//2.����Խ���ұ߽缰һ���ڵķ���
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
		
		//3.����Խ���±߽缰����Խ���Ѵ��ڵķ���
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
		
		//4.���κ󲻿ɳ����߽�
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
	 * ��ȡr�ڶ�ά������Ϸ���еĺ��±�
	 * @param r
	 * @return
	 */
	public static int getIndexY(Rect r){
		int y = 0;
		y = ( r.getX() - 57 ) / 25;
		return y;
	}
	/**
	 * ��ȡr�ڶ�ά������Ϸ���е����±�
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
