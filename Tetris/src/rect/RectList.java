package rect;

import java.awt.Graphics;

/**
 * ���������洢�����С�������꼰ʹ�����
 * @author Administrator
 *
 */
public  class RectList {
	/**
	 * �ö�ά��������ʾ��Ϸ����
	 */
	public static Rect[][] list = new Rect[18][12];
	/**
	 * ��ʶ���Ƿ����������������жϣ�true��ʾ��������
	 */
	public static boolean  flagLine[] = new boolean[18];
	/**
	 * ��ʾ���е�����
	 */
	public static int meltLineNum = 0;
	/**
	 * ��Ϸ�÷�, һ����һ�е�10�� һ��������20�� ����40�� ����80
	 */
	public static int score = 0;
	private RectList(){};
	
	//��̬��ʼ��
	static{
		int x = 57;
		int y = 67;
		for(int i=0; i<18; i++){
			//��ʼ����ʶ��
			flagLine[i] = false;
			for(int j=0; j<12; j++){
				//ʵ��������ʼ������, С����״̬
				list[i][j] = new Rect(x + j * Rect.RECT_SIZE, y + i * Rect.RECT_SIZE, Rect.TYPE_NULL);
				list[i][j].setColorX(0);
				list[i][j].setColorY(0);
			}
		}
	}
	
	/**
	 * �ж��Ƿ��������
	 * @return ����Ҫ���е�����
	 */
	private static int isMelt(){
		int num = 18;
		//��ʼ��
		for(int i=0; i<18; i++){
			flagLine[i] = true;
		}
		//�ж�flagLine��ֵ
		for(int i=0; i<18; i++){
			for(int j=0; j<12; j++){
				//һ����������δ��ʹ�õķ��񣬸��в�����
				if(list[i][j].getType() == Rect.TYPE_NULL){
					flagLine[i] = false;
					num --;
					break;
				}
			}
		}
		return num ;
	}
	
	/**
	 * ����Ҫ���еķ���Ϊ��ɫ
	 * @param g
	 */
	private static void drawMeltLine(Graphics g){
		for(int i=0; i<flagLine.length; i++){
			if(flagLine[i] == true){
				for(int j=0; j<12; j++){
					list[i][j].drawRect(g, Rect.TYPE_MELT);
				}
			}
		}
	}
	/**
	 * ���Ƶ�ǰ��Ϸ�����з���
	 * @param g
	 */
	public static void drawAllLine(Graphics g){
		for(int i=0; i<18; i++){
			for(int j=0; j<12; j++){
				if(list[i][j].getType() != Rect.TYPE_NULL){
					list[i][j].drawRect(g);
				}
			}
		}
	}
	/**
	 * ����
	 */
	public static void meltLine(Graphics g){
		//��ȡ����������
		int num = isMelt();
		//ִ������
		if(num>0){
			//����������
			meltLineNum += num;
			//���·���
			if(num==1){
				score += 10;
			}else if(num==2){
				score += 20;
			}else if(num==3){
				score += 40;
			}else if(num==4){
				score += 80;
			}

			//��ȡ���к��list״̬
			for(int i=17; i>=0; i--){
				if(flagLine[i]==true && i!=0){
					int k = i;
					for(int j=k; j>0; j--){
						for(int m=0; m<12; m++){
							list[j][m].setColorX(list[j-1][m].getColorX());
							list[j][m].setColorY(list[j-1][m].getColorY());
							list[j][m].setType(list[j-1][m].getType());
						}
					}
					for(int j=0; j<12; j++){
						list[0][j].setType(Rect.TYPE_NULL);
					}
					isMelt();
					i++;
				}else if(flagLine[i]==true && i==0){
					for(int j=0; j<12; j++){
						list[0][j].setType(Rect.TYPE_NULL);
					}
					flagLine[0] = false;
				}
			}
			//������������Ϸ��״̬
			drawAllLine(g);
		}
	}
}
