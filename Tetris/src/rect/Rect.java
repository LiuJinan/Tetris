package rect;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * ������������С����
 * 
 * @author ����
 *
 */
public class Rect {
	/**
	 * С����ĺ�����
	 */
	private int x;
	/**
	 * С�����������
	 */
	private int y;
	/**
	 * С�������ɫ�����꣬����ȡ����rect.png ͼƬ
	 */
	private int colorX;
	/**
	 * С�������ɫ�����꣬����ȡ����rect.png ͼƬ
	 */
	private int colorY;
	/**
	 * ��ʾ��ǰ�����״̬
	 */
	private int type;
	/**
	 * ����ͼƬ
	 */
	private final Image IMG_R = new ImageIcon("image/game/rect.png").getImage();
	/**
	 * TYPE_DOWN����ʾ�������ѡ����������ķ�����ɫ����ǰ����Ϊ����״̬
	 */
	public static final int TYPE_DOWN = 0;
	/**
	 * TYPE_MELT:��ʾ����ѡ��Ҫ���еķ�����ɫ����ǰ����Ϊ����״̬,����ɫͳһ
	 */
	public static final int	TYPE_MELT = 1;
	/**
	 * TYPE_DONE:��ʾѡ���Ѿ��������ķ�����ɫ����ǰ����Ϊ���������״̬������ɫͳһ
	 */
	public static final int TYPE_DONE = 2;
	/**
	 * TYPE_NULL:��ʾ��ǰ����δ��ʹ��״̬
	 */
	public static final int TYPE_NULL = -1;
	/**
	 * RECT_SIZE:С����Ĵ�С
	 */
	public static final int RECT_SIZE = 25;
	
	
	public Rect(){
		
	}
	public Rect(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type  = type;
	}
	
	/**
	 * ���ܣ�С����ѡ����ɫ
	 * 		type = 0   ��������ķ���ѡ����ɫ������ɫ���ѡ��
	 * 		type = 1  ��Ҫ���еķ���ѡ����ɫ������ɫͳһ
	 * 		type = 2  �Ѿ�����ķ���ѡ����ɫ������ɫͳһ
	 * @param type
	 */
	public int selectColor(int type){
		int x = 0;
		//ѡ���������еķ�����ɫ
		if(type==Rect.TYPE_MELT){
			x = 200;
			this.setColorX(x);
			this.setColorY(0);
		}
		//ѡ�������䷽����ɫ
		else if(type==Rect.TYPE_DONE){
			x = 175;
			this.setColorX(x);
			this.setColorY(0);
		}
		//���ѡ����������ķ�����ɫ
		else if(type==Rect.TYPE_DOWN){
			x = new Random().nextInt(7) + 1;
			x *= 25;
			this.setColorX(x);
			this.setColorY(0);
		}
		
		return x;
	}
	
	/**
	 * ����С����
	 * @param g
	 */
	public void drawRect(Graphics g){
		g.drawImage(IMG_R, this.getX(), this.getY(), this.getX()+Rect.RECT_SIZE, this.getY()+Rect.RECT_SIZE, 
				this.getColorX(), this.getColorY(), this.getColorX()+Rect.RECT_SIZE, this.getColorY()+Rect.RECT_SIZE, null);
	}
	/**
	 * ����type����С����
	 * @param g
	 * @param type
	 */
	public void drawRect(Graphics g, int type){
		this.selectColor(type);
		g.drawImage(IMG_R, this.getX(), this.getY(), this.getX()+Rect.RECT_SIZE, this.getY()+Rect.RECT_SIZE, 
				this.getColorX(), this.getColorY(), this.getColorX()+Rect.RECT_SIZE, this.getColorY()+Rect.RECT_SIZE, null);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getColorX() {
		return colorX;
	}
	public void setColorX(int colorX) {
		this.colorX = colorX;
	}
	public int getColorY() {
		return colorY;
	}
	public void setColorY(int colorY) {
		this.colorY = colorY;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type = type;
	}
}
