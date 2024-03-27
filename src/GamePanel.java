import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener , KeyListener{
	
	private int pannelDir , ballX , ballY;
	private int ballXdir , ballYdir;
	private boolean play , over;
	private Timer t;
	private Blocks blocks;
	
	GamePanel(){
		setSize(700,700);
		setBackground(Color.black);
		pannelDir = 275;
		ballX = 340;
		ballY = 500;
		ballXdir = 0;
		ballYdir = 5;
		blocks = new Blocks(5);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		t = new Timer(15, this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pannelDir += 15;
			if(pannelDir > 548)
				pannelDir = 548;
			play = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			pannelDir -= 15;
			if(pannelDir < 1)
				pannelDir = 1;
			play = true;
		}
		over = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			play = !play;
		//else
			//play = true;
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(play) {
		ballX += ballXdir;
		ballY += ballYdir;
		if(ballY < 0)
			ballYdir = -ballYdir;
		if(ballX < 0)
			ballXdir = -ballXdir;
		if(ballX > 680)
			ballXdir = -ballXdir;
		
		if(new Rectangle(pannelDir, 640, 150, 10).intersects(new Rectangle(ballX, ballY, 20, 20))) {
			//ballYdir = -ballYdir;
			ballYdir = -5;
			if(ballX - pannelDir < 0)
				pannelDir =  ballX - 1;
			if(ballX - pannelDir >= 0 && ballX - pannelDir < 10)
				ballXdir = -4 ;
			else if(ballX - pannelDir > 10 && ballX - pannelDir < 40)
				ballXdir = -3 ;
			else if(ballX - pannelDir > 40 && ballX - pannelDir < 60)
				ballXdir = -2 ;
			else if(ballX - pannelDir > 60 && ballX - pannelDir < 90)
				ballXdir = ballXdir ;
			else if(ballX - pannelDir > 90 && ballX - pannelDir < 120)
				ballXdir = 2 ;
			else if(ballX - pannelDir > 120 && ballX - pannelDir < 130)
				ballXdir = 3 ;
			else
				ballXdir = 4 ;
		}
		for(int i = 0 ; i < 11 * blocks.getL(); i++) {
			if(new Rectangle(blocks.ReturnX()[i], blocks.ReturnY()[i], 55, 55).intersects(new Rectangle(ballX, ballY, 20, 20))){
				blocks.remove(i);
				ballYdir = -ballYdir;
			}
		}
		if(blocks.getCount() == 0) {
			blocks.reset();
			play = false;
			pannelDir = 275;
			ballX = 340;
			ballY = 500;
			ballXdir = 0;
			ballYdir = 5;
		}
		repaint();
		if(ballY > 700) {
			play = false;
			pannelDir = 275;
			ballX = 340;
			ballY = 500;
			ballXdir = 0;
			ballYdir = 5;
			blocks.reset();
			over = true;
		}
			
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0, 0, 204));
		g.fillRoundRect(pannelDir, 645, 150, 10, 5, 5);
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
		
		for(int i = 0 ; i < 11 * blocks.getL(); i++) {
			g.setColor(Color.white);
			g.fillRect(blocks.ReturnX()[i], blocks.ReturnY()[i], 55, 55);
			
		}
		
		if(over) {
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans Ms" , Font.PLAIN , 65));
			g.drawString("Game Over", 180, 100);
		}
	}
	
	@Override public void keyReleased(KeyEvent arg0) {}
	@Override public void keyTyped(KeyEvent arg0) {}
	
}
