package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import rect.RectType;

public class Ctrl implements KeyListener {
	RectType[] rt = null;
	private int index = 1;
	private int randType;
	public Ctrl(RectType[] rt){
		this.rt = rt;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			rt[randType].up();
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			rt[randType].down();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			rt[randType].left();
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rt[randType].right();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public int getIndex(){
		return this.index;
	}
	/**
	 * 可以通过index来选择下落方块类型
	 * @param index
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	public void selectType(int rand){
		this.randType = rand;
		rt[randType].setIndex(index); 
	}
	
	public int getNowType(){
		return randType;
	}

}
