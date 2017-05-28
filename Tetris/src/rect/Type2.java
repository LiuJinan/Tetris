package rect;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
/**
 * 该类表示一字型方块
 * @author Administrator
 *
 */
public class Type2 extends RectType {
	public List<Rect[]> changes = new ArrayList<Rect[]>();
	public int index = 0;
	private Rect[] tp = null;
	public int colX = new Rect().selectColor(Rect.TYPE_DOWN);
	
	public Rect[] tp1 = new Rect[]{
		new Rect(RectType.INIT_X - Rect.RECT_SIZE, RectType.INIT_Y, Rect.TYPE_DOWN),
		new Rect(RectType.INIT_X, RectType.INIT_Y, Rect.TYPE_DOWN),
		new Rect(RectType.INIT_X + Rect.RECT_SIZE, RectType.INIT_Y, Rect.TYPE_DOWN),
		new Rect(RectType.INIT_X + 2 * Rect.RECT_SIZE, RectType.INIT_Y , Rect.TYPE_DOWN)
	};
	public Rect[] tp2 = new Rect[]{
		new Rect(RectType.INIT_X, RectType.INIT_Y - 3 * Rect.RECT_SIZE, Rect.TYPE_DOWN),
		new Rect(RectType.INIT_X, RectType.INIT_Y - 2 * Rect.RECT_SIZE, Rect.TYPE_DOWN),
		new Rect(RectType.INIT_X, RectType.INIT_Y - Rect.RECT_SIZE, Rect.TYPE_DOWN),
		new Rect(RectType.INIT_X, RectType.INIT_Y , Rect.TYPE_DOWN)
	};
	
	public Type2(){
		changes.add(tp1);
		changes.add(tp2);
		allChanges.add(changes);
		initColor(changes, colX);
	}
	@Override
	public void convert() {
		if(index==0){
			index = 1;
			if(tp1[0].getX()<=INIT_X){
				tp2[0].setX(tp1[1].getX());
				tp2[0].setY(tp1[1].getY() - Rect.RECT_SIZE);
				tp2[1].setX(tp1[1].getX());
				tp2[1].setY(tp1[1].getY());
				tp2[2].setX(tp1[1].getX());
				tp2[2].setY(tp1[1].getY() + Rect.RECT_SIZE);
				tp2[3].setX(tp1[1].getX());
				tp2[3].setY(tp1[1].getY() + 2 * Rect.RECT_SIZE);
			}else {
				tp2[0].setX(tp1[2].getX());
				tp2[0].setY(tp1[2].getY() - Rect.RECT_SIZE);
				tp2[1].setX(tp1[2].getX());
				tp2[1].setY(tp1[2].getY());
				tp2[2].setX(tp1[2].getX());
				tp2[2].setY(tp1[2].getY() + Rect.RECT_SIZE);
				tp2[3].setX(tp1[2].getX());
				tp2[3].setY(tp1[2].getY() + 2 * Rect.RECT_SIZE);
			}
		}else if(index == 1){
			index = 0;
			if(tp2[0].getX()<=INIT_X){
				tp1[0].setX(tp2[1].getX() - Rect.RECT_SIZE);
				tp1[0].setY(tp2[1].getY());
				tp1[1].setX(tp2[1].getX());
				tp1[1].setY(tp2[1].getY());
				tp1[2].setX(tp2[1].getX() + Rect.RECT_SIZE);
				tp1[2].setY(tp2[1].getY());
				tp1[3].setX(tp2[1].getX() + 2 * Rect.RECT_SIZE);
				tp1[3].setY(tp2[1].getY());
			}else{
				tp1[0].setX(tp2[1].getX()-2*Rect.RECT_SIZE);
				tp1[0].setY(tp2[1].getY());
				tp1[1].setX(tp2[1].getX()-Rect.RECT_SIZE);
				tp1[1].setY(tp2[1].getY());
				tp1[2].setX(tp2[1].getX());
				tp1[2].setY(tp2[1].getY());
				tp1[3].setX(tp2[1].getX() + Rect.RECT_SIZE);
				tp1[3].setY(tp2[1].getY());
			}
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
		if(index==2){
			this.index = 0;
		}else if(index == 3){
			this.index = 1;
		}
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
	public void drawType(Graphics g) {
		if(index==2){
			this.index = 0;
		}else if(index == 3){
			this.index = 1;
		}
		tp = changes.get(index);
		for(int i=0; i<4; i++){
			if(tp[i].getY()>=67)
				tp[i].drawRect(g);
		}
	}
	
	@Override
	public void init(int x, int y, int colX , List<Rect[]> changes){
		init(x, y);
		initColor(changes, colX);
	}
	
	@Override
	public void init(int x, int y){
		tp1[0].setX(x - Rect.RECT_SIZE);
		tp1[0].setY(y);
		tp1[1].setX(x);
		tp1[1].setY(y);
		tp1[2].setX(x + Rect.RECT_SIZE);
		tp1[2].setY(y);
		tp1[3].setX(x + 2 * Rect.RECT_SIZE);
		tp1[3].setY(y);
		tp2[0].setX(x);
		tp2[0].setY(y - 3 * Rect.RECT_SIZE);
		tp2[1].setX(x);
		tp2[1].setY(y - 2 * Rect.RECT_SIZE);
		tp2[2].setX(x);
		tp2[2].setY(y - Rect.RECT_SIZE);
		tp2[3].setX(x);
		tp2[3].setY(y);
		for(int i=0; i<4; i++){
			tp1[i].setType(Rect.TYPE_DOWN);
			tp2[i].setType(Rect.TYPE_DOWN);
		}
	}
	
	public void setIndex(int index){
		this.index = index;
	}
}
