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
	 * 当前下落方块类型
	 */
	public static int randType ;
	/**
	 * 下次下落方块类型
	 */
	public static int randNextType;
	/**
	 * 当前下落方块的多种变形
	 */
	public static int index;
	/**
	 * 下次下落方块的多种变形
	 */
	public static int indexNext;
	/**
	 * 下次下落方块颜色
	 */
	public static int colX;
	
	
	public PanelGame(){
		lays = new LayWindow[]{
				new LayGame(50, 60, 300, 450), 		//游戏框
				new LayScore(364, 60, 100, 75),		//分数框
				new LayNext(364, 180, 100, 100),	//下一个方块框
				new LayMelt(364, 320, 100, 75),		//消行
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
		
		//循环刷新游戏窗口
		for(int i=0; i<lays.length; i++){
			lays[i].paint(g);
		}
		//TODO 完成下落后要将f置为true
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
		//下次下落方块类型
		randNextType = new Random().nextInt(rt.length);
		//下次下落方块变形，共有4种变形
		indexNext = new Random().nextInt(4);
		//下次下落方块颜色
		colX = new Rect().selectColor(Rect.TYPE_DOWN);
	}
	/**
	 * 初始化当前下落方块属性
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
