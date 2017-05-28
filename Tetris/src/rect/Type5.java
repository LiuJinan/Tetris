package rect;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
/**
 * 该类用来Z字形
 * @author Administrator
 *
 */
public class Type5 extends RectType {
	/**
	 * Z形方块四种变形
	 */
	public Rect[] tp1 = new Rect[]{
		new Rect(),new Rect(), new Rect(), new Rect()
	};
	public Rect[] tp2 = new Rect[]{
		new Rect(),new Rect(), new Rect(), new Rect()
	};
	public Rect[] tp3 = new Rect[]{
		new Rect(),new Rect(), new Rect(), new Rect()
	};
	public Rect[] tp4 = new Rect[]{
			new Rect(),new Rect(), new Rect(), new Rect()
	};
	public List<Rect[]> changes = new ArrayList<Rect[]>();
	public int index = 0;
	public Rect[] tp = null;
	public int colX = new Rect().selectColor(Rect.TYPE_DOWN);
	
	
	public Type5(){
		changes.add(tp1);
		changes.add(tp2);
		changes.add(tp3);
		changes.add(tp4);
		init(INIT_X, INIT_Y, colX, changes);
		allChanges.add(changes);
	}
	
	@Override
	public void convert() {
		int x, y;
		tp = changes.get(index);
		if(index == 0){
			index = 1;
			x = tp[0].getX();
			y = tp[0].getY() + Rect.RECT_SIZE;
			init(x, y);
		}else if(index == 1){
			index = 2;
			x = tp[0].getX();
			y = tp[0].getY();
			init(x, y);
		}else if(index == 2){
			index = 3;
			x = tp[0].getX();
			y = tp[0].getY() + Rect.RECT_SIZE;
			init(x, y);
		}else if(index == 3){
			index = 0;
			x = tp[0].getX();
			y = tp[0].getY();
			init(x, y);
		}
	}

	@Override
	public void up() {
		tp = changes.get(index);
		boolean flag = true;
		for(int i=0; i<tp.length; i++){
			if(tp[i].getType()==Rect.TYPE_DONE){
				flag = false;
				break;
			}
		}
		if(flag){
			convert();
			tp = changes.get(index);
			if(!rule(tp, MOVE_UP)){
				for(int i=0; i<3; i++){
					convert();
				}
			}
		}
	}

	@Override
	public  void down() {
		tp = changes.get(index);
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
		tp = changes.get(index);
		if(rule(tp, MOVE_LEFT)){
			for(int i=0; i<tp.length; i++){
				tp[i].setX(tp[i].getX() - Rect.RECT_SIZE);
			}
		}
	}

	@Override
	public void right() {
		tp = changes.get(index);
		if(rule(tp, MOVE_RIGHT)){
			for(int i=0; i<tp.length; i++){
				tp[i].setX(tp[i].getX()+Rect.RECT_SIZE);
			}
		}
	}

	@Override
	public  void init(int x, int y, int colX, List<Rect[]> changes) {
		init(x, y);
		initColor(changes, colX);
	}

	@Override
	public void drawType(Graphics g) {
		tp = changes.get(index);
		for(int i=0; i<4; i++){
			if(tp[i].getY()>=67)
				tp[i].drawRect(g);
		}
	}
	
	@Override
	public void setIndex(int index){
		this.index = index;
	}

	@Override
	public void init(int x, int y) {
		for(int i=0; i<4; i++){
			tp1[i].setType(Rect.TYPE_DOWN);
			tp2[i].setType(Rect.TYPE_DOWN);
			tp3[i].setType(Rect.TYPE_DOWN);
			tp4[i].setType(Rect.TYPE_DOWN);
		} 
		tp1[0].setX(x); tp1[0].setY(y - Rect.RECT_SIZE);
		tp1[1].setX(x + Rect.RECT_SIZE); tp1[1].setY(y - Rect.RECT_SIZE);
		tp1[2].setX(x + Rect.RECT_SIZE); tp1[2].setY(y);
		tp1[3].setX(x + 2 * Rect.RECT_SIZE); tp1[3].setY(y);
		
		tp2[0].setX(x); tp2[0].setY(y);
		tp2[1].setX(x); tp2[1].setY(y - Rect.RECT_SIZE);
		tp2[2].setX(x + Rect.RECT_SIZE); tp2[2].setY(y - Rect.RECT_SIZE);
		tp2[3].setX(x + Rect.RECT_SIZE); tp2[3].setY(y - 2 * Rect.RECT_SIZE);
		
		tp3[0].setX(x); tp3[0].setY(y - Rect.RECT_SIZE);
		tp3[1].setX(x + Rect.RECT_SIZE); tp3[1].setY(y - Rect.RECT_SIZE);
		tp3[2].setX(x + Rect.RECT_SIZE); tp3[2].setY(y);
		tp3[3].setX(x + 2 * Rect.RECT_SIZE); tp3[3].setY(y);
		
		tp4[0].setX(x); tp4[0].setY(y);
		tp4[1].setX(x); tp4[1].setY(y - Rect.RECT_SIZE);
		tp4[2].setX(x + Rect.RECT_SIZE); tp4[2].setY(y - Rect.RECT_SIZE);
		tp4[3].setX(x + Rect.RECT_SIZE); tp4[3].setY(y - 2 * Rect.RECT_SIZE);
	}
}
