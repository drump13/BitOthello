package graphic;

import main.BitBoard;
import main.OperateBitBoard;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;

public class DrawBoard extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SIZE_OF_BREADTH = 600;
	public static final int SIZE_OF_HEIGHT = 620;
	BitBoard b;
	Graphics g;	
	
	
	public DrawBoard(BitBoard b){
		this.b = b;
		this.setTitle("BitOthello");
		this.setSize(SIZE_OF_BREADTH,SIZE_OF_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.g = this.getGraphics();
	}
	
	public void paint(Graphics g){
		this.setSize(SIZE_OF_BREADTH,SIZE_OF_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.g = this.getGraphics();
		setBackground(Color.GREEN);
		Dimension size = getSize();
		Image back = createImage(size.width,size.height);
		Graphics2D buffer = (Graphics2D)back.getGraphics();
		super.paint(buffer);
		
		buffer.setStroke(new BasicStroke(3.0f));
		buffer.setColor(Color.BLACK);
		
		//buffer.setStroke(new BasicStroke(3.0f));
		//buffer.setColor(Color.BLACK);
		
		
		for(int count = 0;count < 8;count++){
			Line2D line = new Line2D.Double(count*(SIZE_OF_BREADTH/8),0,count*(SIZE_OF_BREADTH/8),SIZE_OF_HEIGHT);
			//g2.draw(line);
			buffer.draw(line);
		}
		for(int count = 0;count < 8;count++){
			Line2D line = new Line2D.Double(0,count*(SIZE_OF_HEIGHT/8)+10,SIZE_OF_BREADTH,count*(SIZE_OF_HEIGHT/8)+10);
			//g2.draw(line);
			buffer.draw(line);
		}
		
		for(int x = 0;x < 64;x++){
			String s = Long.toBinaryString(b.black);
			s = OperateBitBoard.complimentZero(s);
			if(s.charAt(x)=='1'){
				buffer.setColor(Color.BLACK);
				Ellipse2D ellipse = new Ellipse2D.Double((x%8)*(SIZE_OF_BREADTH/8),(x/8)*(SIZE_OF_HEIGHT/8)+10,SIZE_OF_BREADTH/8,SIZE_OF_HEIGHT/8);
				buffer.fill(ellipse);

			}
		}

		for(int y = 0;y < 64;y++){
			String s = Long.toBinaryString(b.white);
			s = OperateBitBoard.complimentZero(s);
			if(s.charAt(y)=='1'){
				buffer.setColor(Color.WHITE);
				Ellipse2D ellipse = new Ellipse2D.Double((y%8)*(SIZE_OF_BREADTH/8),(y/8)*(SIZE_OF_HEIGHT/8)+10,SIZE_OF_BREADTH/8,SIZE_OF_HEIGHT/8);
				buffer.fill(ellipse);

			}
		}

		
/*		for(int x = 0;x < 8;x++){
			for(int y = 0;y < 8;y++){
				if(b.returnBoard(x, y) == Stone.BLACK){
					buffer.setColor(Color.BLACK);
					Ellipse2D ellipse = new Ellipse2D.Double(x*(SIZE_OF_BREADTH/8),y*(SIZE_OF_HEIGHT/8)+10,SIZE_OF_BREADTH/8,SIZE_OF_HEIGHT/8);
					buffer.fill(ellipse);
				}else if(b.returnBoard(x, y) == Stone.WHITE){
					buffer.setColor(Color.WHITE);
					Ellipse2D ellipse = new Ellipse2D.Double(x*(SIZE_OF_BREADTH/8),y*(SIZE_OF_HEIGHT/8)+10,SIZE_OF_BREADTH/8,SIZE_OF_HEIGHT/8);
					buffer.fill(ellipse);
				}
			}
		}*/

		
		
		g.drawImage(back,0,0,this);

		
	}

	@Override
	public void run() {
		paint(g);
		MouseClick m = new MouseClick();
		while(true){
			try {
				Thread.sleep(30);
				repaint();
				this.addMouseListener(m);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}


