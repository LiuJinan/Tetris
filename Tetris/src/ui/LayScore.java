package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import rect.RectList;
import rect.RectTypeList;

public class LayScore extends LayWindow {
	private final Image IMG_SCORE = new ImageIcon("image/string/score.png").getImage();
	private final Image[] IMG_NUM = {
			new ImageIcon("image/string/num0.png").getImage(),
			new ImageIcon("image/string/num1.png").getImage(),
			new ImageIcon("image/string/num2.png").getImage(),
			new ImageIcon("image/string/num3.png").getImage(),
			new ImageIcon("image/string/num4.png").getImage(),
			new ImageIcon("image/string/num5.png").getImage(),
			new ImageIcon("image/string/num6.png").getImage(),
			new ImageIcon("image/string/num7.png").getImage(),
			new ImageIcon("image/string/num8.png").getImage(),
			new ImageIcon("image/string/num9.png").getImage()
	};
	private List<Image> tempL =  new ArrayList<Image>();
	
	public LayScore(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.drawWindow(g);
		g.drawImage(IMG_SCORE, this.x + 10, this.y + 10, null);
		this.drawScore(g);
	}
	
	public void drawScore(Graphics g){
		int score = RectList.score;
//		RectTypeList.rank = RectTypeList.rank - (score / 100) * 50;
		while(tempL.size()!=0){
			tempL.remove(0);
		}
		int num ;
		do{
			num = score % 10;
			score /= 10;
			tempL.add(0, IMG_NUM[num]);
		}while(score!=0);
		for(int i=0; i<tempL.size(); i++){
			g.drawImage(tempL.get(i), this.x + 25 + i*20, this.y + 45, null);
		}
	}
}
