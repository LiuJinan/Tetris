package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import rect.Rect;
import rect.RectType;
import rect.RectTypeList;
import rect.Type1;
import rect.Type2;
import rect.Type3;
import rect.Type4;
import rect.Type5;
import rect.Type6;
import rect.Type7;

public class LayNext extends LayWindow {
	private final Image IMG_R = new ImageIcon("image/game/rect.png").getImage();
	private int randNextType;
	private int indexNext;
	private int colXNext;
	private RectType[] rt = RectTypeList.rt;
	private Type1 rt1 = null;
	private Type2 rt2 = null;
	private Type3 rt3 = null;
	private Type4 rt4 = null;
	private Type5 rt5 = null;
	private Type6 rt6 = null;
	private Type7 rt7 = null;
	private Rect[] tp = new Rect[]{
		new Rect(), new Rect(), new Rect(), new Rect()	
	};
	private Rect[] temp = null;
	
	public LayNext(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.drawWindow(g);
		this.drawNext(g);
	}
	
	public void drawNext(Graphics g){
		randNextType = PanelGame.randNextType;
		indexNext = PanelGame.indexNext;
		colXNext = PanelGame.colX;
		if(randNextType==0){
			rt1 = new Type1();	
			rt1.init(x + 32, y + 57, colXNext, rt1.changes);
			rt1.setIndex(indexNext);
			rt1.drawType(g);
		}else if(randNextType==1){
			rt2 = new Type2();
			if(indexNext==1 || indexNext == 3){
				rt2.init(x + 32, y + 82, colXNext, rt2.changes);
			}else {
				rt2.init(x + 32, y + 57, colXNext, rt2.changes);
			}
			rt2.setIndex(indexNext);
			rt2.drawType(g);
		}else if(randNextType==2){
			rt3 = new Type3();
			rt3.init(x + 32, y + 57, colXNext, rt3.changes);
			rt3.setIndex(indexNext);
			rt3.drawType(g);
		}else if(randNextType==3){
			rt4 = new Type4();
			rt4.init(x + 32, y + 57, colXNext, rt4.changes);
			rt4.setIndex(indexNext);
			rt4.drawType(g);
		}else if(randNextType==4){
			rt5 = new Type5();
			rt5.init(x + 32, y + 57, colXNext, rt5.changes);
			rt5.setIndex(indexNext);
			rt5.drawType(g);
		}else if(randNextType==5){
			rt6 = new Type6();
			rt6.init(x + 32, y + 57, colXNext, rt6.changes);
			rt6.setIndex(indexNext);
			rt6.drawType(g);
		}else if(randNextType==6){
			rt7 = new Type7();
			rt7.init(x + 32, y + 57, colXNext, rt7.changes);
			rt7.setIndex(indexNext);
			rt7.drawType(g);
		}
		RectType.allChanges.remove(RectType.allChanges.size()-1);
	}
}
