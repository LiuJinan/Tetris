package rect;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类为田字形方块
 * @author Administrator
 *
 */
public class Type1 extends RectType {
	
	public Rect[] tp = new Rect[]{
		new Rect(RectType.INIT_X, RectType.INIT_Y - Rect.RECT_SIZE, Rect.TYPE_DOWN),	
		new Rect(RectType.INIT_X + Rect.RECT_SIZE, RectType.INIT_Y - Rect.RECT_SIZE, Rect.TYPE_DOWN),	
		new Rect(RectType.INIT_X, RectType.INIT_Y , Rect.TYPE_DOWN),	
		new Rect(RectType.INIT_X + Rect.RECT_SIZE, RectType.INIT_Y , Rect.TYPE_DOWN),	
	};
	/**
	 * 方块变化
	 */
	public List<Rect[]> changes = new ArrayList<Rect[]>();
	public int index = 0;
	public int colX = new Rect().selectColor(Rect.TYPE_DOWN);
	
	public Type1(){
		//初始化颜色
		changes.add(tp);
		allChanges.add(changes);
		initColor(changes, colX);
	}
	
	@Override
	public void convert() {}

	@Override
	public void up() {}

	@Override
	public  void down() {
		if(tp[0].getType()!=Rect.TYPE_DONE){
			if(rule(tp, MOVE_DOWN)){
				for(int i=0; i<tp.length; i++){
					tp[i].setY(tp[i].getY() + Rect.RECT_SIZE);
				}
			}
		}
	}

	@Override
	public void left() {
		if(rule(tp, MOVE_LEFT)){
			for(int i=0; i<tp.length; i++){
				tp[i].setX(tp[i].getX() - Rect.RECT_SIZE);
			}
		}
	}

	@Override
	public void right() {
		if(rule(tp, MOVE_RIGHT)){
			for(int i=0; i<tp.length; i++){
				tp[i].setX(tp[i].getX()+Rect.RECT_SIZE);
			}
		}
	}

	@Override
	public void drawType(Graphics g) {
		for(int i=0; i<4; i++){
			if(tp[i].getY()>=67)
				tp[i].drawRect(g);
		}
	}
	
	@Override
	public void init(int x, int y, int colX, List<Rect[]> changes){
		init(x, y);
		initColor(changes, colX);
	}
	
	@Override
	public void init(int x, int y){
		tp[0].setX(x);
		tp[0].setY(y - Rect.RECT_SIZE);
		tp[1].setX(x + Rect.RECT_SIZE);
		tp[1].setY(y - Rect.RECT_SIZE);
		tp[2].setX(x);
		tp[2].setY(y);
		tp[3].setX(x + Rect.RECT_SIZE);
		tp[3].setY(y);
		for(int i=0; i<tp.length; i++){
			tp[i].setType(Rect.TYPE_DOWN);
		}
	}
	
	public void setIndex(int index){
		this.index = index;
	}
}
