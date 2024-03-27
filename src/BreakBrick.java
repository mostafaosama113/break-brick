import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BreakBrick extends JFrame implements WindowListener{

	public static BreakBrick b;
	public static GamePanel game ;
	private int x =0, y=0;
	
	public static void main(String[] args) {
		b = new BreakBrick();
	}
	
	BreakBrick(){
		setUndecorated(true);
		setResizable(false);
		setOpacity(0);
		addWindowListener(this);
		setSize(700,740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		/////////////////////bar///////////////////////
		{
		JPanel bar = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRect(0, 39, 700, 1);
			}
		};
		bar.setBackground(new Color(0, 0, 204));
		bar.setBounds(0,0,700,40);
		bar.setSize(700,40);
		bar.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			@Override public void mouseReleased(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mouseEntered(MouseEvent arg0) {}
			@Override public void mouseClicked(MouseEvent arg0) {}
		});
		bar.addMouseMotionListener(new MouseMotionListener() {
			@Override public void mouseMoved(MouseEvent arg0) {}
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - x , e.getYOnScreen() - y);
			}
		});
		JLabel exit = new JLabel("X ");
		exit.setForeground(Color.white);
		bar.setLayout(new BorderLayout());
		bar.add(exit, BorderLayout.EAST);
		exit.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {
				exit.setForeground(Color.white);
			}
			@Override public void mouseEntered(MouseEvent arg0) {
				exit.setForeground(Color.gray);
			}
			@Override public void mouseClicked(MouseEvent g) {
				if(SwingUtilities.isLeftMouseButton(g)) {
				for(float i = 1 ; i > 0 ; i -= 0.02f) {
					b.setOpacity(i);
					try {Thread.sleep(1);}catch (Exception e) {}
				}
				System.exit(0);
				}
			}
			@Override public void mousePressed(MouseEvent arg0) {}
		});
		add(bar);
		}
		
		/////////////////////bar///////////////////////
		
		////////////////game panel////////////////////
		game = new GamePanel();
		game.setBounds(0,40,700,700);
		add(game);
		////////////////game panel////////////////////
		setVisible(true);
	}
	
	@Override 
	public void windowClosing(WindowEvent arg0) { 
		for(float i = 1 ; i > 0 ; i -= 0.02f) {
			b.setOpacity(i);
			try {Thread.sleep(1);}catch (Exception e) {}
		}
		System.exit(0);
	}
	
	@Override
	public void windowOpened(WindowEvent arg0) {
		for(float i = 0 ; i < 1 ; i += 0.02f) {
			b.setOpacity(i);
			try {Thread.sleep(1);}catch (Exception e) {}
		}
	}
	
	@Override public void windowActivated(WindowEvent arg0) {}
	@Override public void windowDeactivated(WindowEvent arg0) {}
	@Override public void windowDeiconified(WindowEvent arg0) {}
	@Override public void windowIconified(WindowEvent arg0) {}
	@Override public void windowClosed(WindowEvent arg0) {}
}
