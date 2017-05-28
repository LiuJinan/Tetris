package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.Ctrl;
import rect.*;

public class PanelGame extends JPanel{
	private LayWindow[] lays = null;
	private final Image IMG_TITLE = new ImageIcon("image/string/title.png").getImage();
	private RectType[] rt = null;
	private RectTypeList rtl = new RectTypeList();
	private Thread mt = new Thread(rtl);
	private boolean f = true;
	private Ctrl ctrl = null;

	/**
	 * ��ǰ���䷽������
	 */
	public static int randType ;
	/**
	 * �´����䷽������
	 */
	public static int randNextType;
	/**
	 * ��ǰ���䷽��Ķ��ֱ���
	 */
	public static int index;
	/**
	 * �´����䷽��Ķ��ֱ���
	 */
	public static int indexNext;
	/**
	 * �´����䷽����ɫ
	 */
	public static int colX;
	
	
	public PanelGame(){
		lays = new LayWindow[]{
				new LayGame(50, 60, 300, 450), 		//��Ϸ��
				new LayScore(364, 60, 100, 75),		//������
				new LayNext(364, 180, 100, 100),	//��һ�������
				new LayMelt(364, 320, 100, 75),		//����
				new LayButton(364, 420, 100, 90)
		};
		rt = rtl.rt;
		ctrl = new Ctrl(rt);
		mt.start();
		initFirstDown();
		
		this.addKeyListener(ctrl);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Image IMG_BG = new ImageIcon("image/background/bg01.png").getImage();
		g.drawImage(IMG_BG, 0, 0, 520, 550, null);
		g.drawImage(IMG_TITLE, 50, 0, null);
		
		//ѭ��ˢ����Ϸ����
		for(int i=0; i<lays.length; i++){
			lays[i].paint(g);
		}
		//TODO ��������Ҫ��f��Ϊtrue
		if(f){
			initNextDown();
			f = false;
		}
		
		rt[randType].drawType(g);
		RectList.drawAllLine(g);
		if(!rectDone()){
			f = true;
			RectList.meltLine(g);
			initDown();
		}
		repaint();
		this.requestFocus();
	}
	
	public void initFirstDown(){
		randType = new Random().nextInt(rt.length);
		rtl.setRandType(randType);
		index = new Random().nextInt(4);
		rtl.setIndex(index);
		ctrl.setIndex(index);
		ctrl.selectType(randType);
	}
	
	public void initNextDown(){
		//�´����䷽������
		randNextType = new Random().nextInt(rt.length);
		//�´����䷽����Σ�����4�ֱ���
		indexNext = new Random().nextInt(4);
		//�´����䷽����ɫ
		colX = new Rect().selectColor(Rect.TYPE_DOWN);
	}
	/**
	 * ��ʼ����ǰ���䷽������
	 */
	public void initDown(){
		randType = randNextType;
		index = indexNext;
		rtl.setRandType(randType);
		rtl.setIndex(index);
		ctrl.setIndex(index);
		ctrl.selectType(randType);
		for(int i=0; i<rt.length; i++){
			rt[i].init(RectType.INIT_X, RectType.INIT_Y, colX, rt[i].allChanges.get(i));
		}	
	}
	
	public boolean rectDone(){
		boolean flag = true;
		Rect[] temp = null;
		int randType  = ctrl.getNowType();
		for(int i=0; i<RectType.allChanges.get(randType).size(); i++){
			temp = RectType.allChanges.get(randType).get(i);
			for(int j=0; j<temp.length; j++){
				if(temp[i].getType()==Rect.TYPE_DONE){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
}
